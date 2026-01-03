package jp.asatex.quickapp.kyuyokeisan_backend_service.controller;

import jp.asatex.quickapp.kyuyokeisan_backend_service.application.SourceWithholdingTaxApplicationService;
import jp.asatex.quickapp.kyuyokeisan_backend_service.application.dto.SourceWithholdingTaxApplicationDto;
import jp.asatex.quickapp.kyuyokeisan_backend_service.controller.dto.SourceWithholdingTaxDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 源泉征收税 Controller
 * 提供 RESTful API 接口，采用 WebFlux 响应式编程风格
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class SourceWithholdingTaxController {

    private final SourceWithholdingTaxApplicationService sourceWithholdingTaxApplicationService;

    public SourceWithholdingTaxController(SourceWithholdingTaxApplicationService sourceWithholdingTaxApplicationService) {
        this.sourceWithholdingTaxApplicationService = sourceWithholdingTaxApplicationService;
    }

    /**
     * 查询源泉征收税额
     * GET /sourceWithholdingTaxQuery?amount=100000&taxType=乙&dependentsCount=0
     *
     * @param amount 金额（扣除社会保险费等后的工资等金额，0円～10,000,000円）
     * @param taxType 类型（'甲' 或 '乙'，可选，默认'乙'）
     * @param dependentsCount 扶养亲族等数量（仅适用于甲类型，0-7人；乙类型时忽略此参数，可选）
     * @return Mono<SourceWithholdingTaxDto> 源泉征收税额 DTO
     */
    @GetMapping("/sourceWithholdingTaxQuery")
    public Mono<SourceWithholdingTaxDto> sourceWithholdingTaxQuery(
            @RequestParam("amount") 
            @NotNull(message = "金额不能为空")
            @Min(value = 0, message = "金额不能小于0")
            @Max(value = 10000000, message = "金额不能超过10,000,000円")
            Integer amount,
            @RequestParam(value = "taxType", required = false, defaultValue = "乙") String taxType,
            @RequestParam(value = "dependentsCount", required = false) Integer dependentsCount) {
        // 额外验证：确保参数在合理范围内
        if (amount == null || amount < 0 || amount > 10000000) {
            return Mono.error(new IllegalArgumentException("金额必须在0円到10,000,000円之间"));
        }
        if (taxType != null && !taxType.equals("甲") && !taxType.equals("乙")) {
            return Mono.error(new IllegalArgumentException("taxType必须是'甲'或'乙'"));
        }
        // 对于甲类型，验证dependentsCount范围（0-7人，但超过7人也可以，会在后端处理）
        if ("甲".equals(taxType) && dependentsCount != null && dependentsCount < 0) {
            return Mono.error(new IllegalArgumentException("扶养亲族等数量不能小于0"));
        }
        // 对于乙类型，dependentsCount应该为null，但为了兼容性，如果传了也会被忽略
        String finalTaxType = taxType != null ? taxType : "乙";
        Integer finalDependentsCount = "甲".equals(finalTaxType) ? dependentsCount : null;
        return sourceWithholdingTaxApplicationService.sourceWithholdingTaxQuery(amount, finalTaxType, finalDependentsCount)
                .map(this::convertSourceWithholdingTaxToDto);
    }

    /**
     * 将 Application DTO 转换为 Controller DTO（源泉征收税额）
     * 采用流式编程风格进行数据转换
     *
     * @param applicationDto Application DTO
     * @return Controller DTO
     */
    private SourceWithholdingTaxDto convertSourceWithholdingTaxToDto(SourceWithholdingTaxApplicationDto applicationDto) {
        return SourceWithholdingTaxDto.builder()
                .taxAmount(applicationDto.getTaxAmount())
                .baseTaxAmount(applicationDto.getBaseTaxAmount())
                .excessTaxRate(applicationDto.getExcessTaxRate())
                .calculationFormula(applicationDto.getCalculationFormula())
                .requiresFormula(applicationDto.getRequiresFormula())
                .build();
    }
}

