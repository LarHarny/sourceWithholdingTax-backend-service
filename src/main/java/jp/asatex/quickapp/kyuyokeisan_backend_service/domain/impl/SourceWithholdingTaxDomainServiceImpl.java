package jp.asatex.quickapp.kyuyokeisan_backend_service.domain.impl;

import jp.asatex.quickapp.kyuyokeisan_backend_service.bean.entity.SourceWithholdingTax;
import jp.asatex.quickapp.kyuyokeisan_backend_service.domain.SourceWithholdingTaxDomainService;
import jp.asatex.quickapp.kyuyokeisan_backend_service.domain.dto.SourceWithholdingTaxDomainDto;
import jp.asatex.quickapp.kyuyokeisan_backend_service.repository.SourceWithholdingTaxRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 源泉征收税 Domain Service 实现类
 * 提供源泉征收税业务逻辑处理和流式编程风格的操作方法
 */
@Service
public class SourceWithholdingTaxDomainServiceImpl implements SourceWithholdingTaxDomainService {

    // 常量：扶养亲族等数量超过7人时，每超过1人减1,610円
    private static final Integer DEPENDENTS_DEDUCTION_PER_PERSON = 1610;
    private static final Integer MAX_DEPENDENTS_IN_TABLE = 7;

    private final SourceWithholdingTaxRepository sourceWithholdingTaxRepository;

    public SourceWithholdingTaxDomainServiceImpl(SourceWithholdingTaxRepository sourceWithholdingTaxRepository) {
        this.sourceWithholdingTaxRepository = sourceWithholdingTaxRepository;
    }

    @Override
    public Mono<SourceWithholdingTaxDomainDto> sourceWithholdingTaxQuery(Integer amount, String taxType) {
        return sourceWithholdingTaxQuery(amount, taxType, null);
    }

    @Override
    public Mono<SourceWithholdingTaxDomainDto> sourceWithholdingTaxQuery(
            Integer amount, String taxType, Integer dependentsCount) {
        String queryTaxType = taxType != null && !taxType.isEmpty() ? taxType : "乙";
        
        // 对于乙类型，dependentsCount必须为null
        // 对于甲类型，如果dependentsCount为null，默认为0
        Integer queryDependentsCount = null;
        Integer actualDependentsCount = dependentsCount;
        
        if ("甲".equals(queryTaxType)) {
            if (actualDependentsCount == null) {
                actualDependentsCount = 0;
            }
            // 如果超过7人，先按7人查询，后续会减额
            queryDependentsCount = Math.min(actualDependentsCount, MAX_DEPENDENTS_IN_TABLE);
        }
        
        final Integer finalQueryDependentsCount = queryDependentsCount;
        final Integer finalActualDependentsCount = actualDependentsCount;
        
        return Mono.just(amount)
                .flatMap(amt -> sourceWithholdingTaxRepository.findSourceWithholdingTaxByAmountAndTaxTypeAndDependents(
                        amt, queryTaxType, finalQueryDependentsCount))
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        String.format("未找到金额 %d、类型 %s、扶养亲族等数量 %s 对应的源泉征收税额", 
                                amount, queryTaxType, 
                                finalQueryDependentsCount != null ? finalQueryDependentsCount : "NULL"))))
                .flatMap(tax -> calculateSourceWithholdingTax(tax, amount, finalActualDependentsCount, queryTaxType));
    }

    private Mono<SourceWithholdingTaxDomainDto> calculateSourceWithholdingTax(
            SourceWithholdingTax tax, Integer amount, Integer actualDependentsCount, String taxType) {
        return Mono.just(tax)
                .map(t -> {
                    Integer taxAmount = t.getTaxAmount();
                    Integer baseTaxAmount = t.getBaseTaxAmount();
                    BigDecimal excessTaxRate = t.getExcessTaxRate();
                    String calculationFormula = t.getCalculationFormula();

                    // 判断是否需要公式计算
                    boolean requiresFormula = (taxAmount == null && baseTaxAmount != null && excessTaxRate != null);

                    // 如果需要公式计算，计算实际税额
                    if (requiresFormula && baseTaxAmount != null && excessTaxRate != null) {
                        // 根据PDF，公式计算有两种情况：
                        // 1. 一般公式：基准税额 + (超过金额 × 税率)
                        //    - 超过金额 = 实际金额 - min_amount
                        // 2. 特殊公式（乙类型3,500,000円以上）：基准税额 + (超过1,700,000円的金额 × 65.945%)
                        //    - 根据PDF：651,900円に、その月の社会保険料等控除後の給与等の金額のうち1,700,000円を超える金額の65.945％に相当する金額を加算した金額
                        
                        Integer excessAmount;
                        // 检查是否是乙类型3,500,000円以上的特殊情况
                        // 根据PDF，3,500,000円的乙类型基准税额是651,900円，税率是65.945%
                        if ("乙".equals(taxType) && amount >= 3500000 && 
                            baseTaxAmount == 651900 && 
                            excessTaxRate.compareTo(new BigDecimal("0.65945")) == 0) {
                            // 乙类型3,500,000円以上的特殊公式：超过1,700,000円的金额
                            excessAmount = Math.max(0, amount - 1700000);
                        } else {
                            // 一般公式：超过最小金额的金额
                            // 注意：对于max_amount为NULL的情况，使用min_amount作为基准
                            excessAmount = Math.max(0, amount - t.getMinAmount());
                        }
                        
                        // 实际税额 = 基础税额 + (超过金额 × 税率)
                        BigDecimal excessTax = new BigDecimal(excessAmount)
                                .multiply(excessTaxRate)
                                .setScale(0, RoundingMode.HALF_UP);
                        taxAmount = baseTaxAmount != null ? baseTaxAmount + excessTax.intValue() : excessTax.intValue();
                    }

                    // 对于甲类型，如果扶养亲族等数量超过7人，需要减额
                    // 根据PDF：扶養親族等の数が7人を超える場合には、扶養親族等の数が7人の場合の税額から、
                    // その7人を超える1人ごとに1,610円を控除した金額
                    if ("甲".equals(taxType) && actualDependentsCount != null && actualDependentsCount > MAX_DEPENDENTS_IN_TABLE) {
                        int excessDependents = actualDependentsCount - MAX_DEPENDENTS_IN_TABLE;
                        int deduction = excessDependents * DEPENDENTS_DEDUCTION_PER_PERSON;
                        taxAmount = Math.max(0, taxAmount - deduction);
                    }

                    // 使用 Builder 模式构建 DTO
                    return SourceWithholdingTaxDomainDto.builder()
                            .taxAmount(taxAmount)
                            .baseTaxAmount(baseTaxAmount)
                            .excessTaxRate(excessTaxRate)
                            .calculationFormula(calculationFormula)
                            .requiresFormula(requiresFormula)
                            .build();
                });
    }
}

