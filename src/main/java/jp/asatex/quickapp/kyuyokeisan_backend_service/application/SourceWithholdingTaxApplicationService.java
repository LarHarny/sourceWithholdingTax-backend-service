package jp.asatex.quickapp.kyuyokeisan_backend_service.application;

import jp.asatex.quickapp.kyuyokeisan_backend_service.application.dto.SourceWithholdingTaxApplicationDto;
import jp.asatex.quickapp.kyuyokeisan_backend_service.domain.SourceWithholdingTaxDomainService;
import jp.asatex.quickapp.kyuyokeisan_backend_service.domain.dto.SourceWithholdingTaxDomainDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 源泉征收税 Application Service
 * 提供应用层服务，调用 Domain 层进行业务处理
 * 采用流式编程风格
 */
@Service
public class SourceWithholdingTaxApplicationService {

    private final SourceWithholdingTaxDomainService sourceWithholdingTaxDomainService;

    public SourceWithholdingTaxApplicationService(SourceWithholdingTaxDomainService sourceWithholdingTaxDomainService) {
        this.sourceWithholdingTaxDomainService = sourceWithholdingTaxDomainService;
    }

    /**
     * 查询源泉征收税额
     * 调用 Domain 层的同名方法获取数据，并转换为 Application DTO
     *
     * @param amount 金额（扣除社会保险费等后的工资等金额）
     * @param taxType 类型（'甲' 或 '乙'，可选，默认'乙'）
     * @return Mono<SourceWithholdingTaxApplicationDto> 源泉征收税额 Application DTO
     */
    public Mono<SourceWithholdingTaxApplicationDto> sourceWithholdingTaxQuery(Integer amount, String taxType) {
        return sourceWithholdingTaxQuery(amount, taxType, null);
    }

    /**
     * 查询源泉征收税额（支持扶养亲族等数量）
     * 调用 Domain 层的同名方法获取数据，并转换为 Application DTO
     *
     * @param amount 金额（扣除社会保险费等后的工资等金额）
     * @param taxType 类型（'甲' 或 '乙'，可选，默认'乙'）
     * @param dependentsCount 扶养亲族等数量（仅适用于甲类型，0-7人；超过7人时按7人查询后减额；乙类型传null）
     * @return Mono<SourceWithholdingTaxApplicationDto> 源泉征收税额 Application DTO
     */
    public Mono<SourceWithholdingTaxApplicationDto> sourceWithholdingTaxQuery(
            Integer amount, String taxType, Integer dependentsCount) {
        return sourceWithholdingTaxDomainService.sourceWithholdingTaxQuery(amount, taxType, dependentsCount)
                .map(this::convertSourceWithholdingTaxToApplicationDto);
    }

    /**
     * 将 Domain DTO 转换为 Application DTO（源泉征收税额）
     * 采用流式编程风格进行数据转换
     *
     * @param domainDto Domain DTO
     * @return Application DTO
     */
    private SourceWithholdingTaxApplicationDto convertSourceWithholdingTaxToApplicationDto(SourceWithholdingTaxDomainDto domainDto) {
        return SourceWithholdingTaxApplicationDto.builder()
                .taxAmount(domainDto.getTaxAmount())
                .baseTaxAmount(domainDto.getBaseTaxAmount())
                .excessTaxRate(domainDto.getExcessTaxRate())
                .calculationFormula(domainDto.getCalculationFormula())
                .requiresFormula(domainDto.getRequiresFormula())
                .build();
    }
}

