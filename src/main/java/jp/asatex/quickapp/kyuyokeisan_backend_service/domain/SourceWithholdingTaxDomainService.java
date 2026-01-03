package jp.asatex.quickapp.kyuyokeisan_backend_service.domain;

import jp.asatex.quickapp.kyuyokeisan_backend_service.domain.dto.SourceWithholdingTaxDomainDto;
import reactor.core.publisher.Mono;

/**
 * 源泉征收税 Domain Service 接口
 * 提供源泉征收税业务逻辑处理的接口定义
 */
public interface SourceWithholdingTaxDomainService {

    /**
     * 查询源泉征收税额
     * 根据金额和类型计算源泉征收税额
     *
     * @param amount 金额（扣除社会保险费等后的工资等金额）
     * @param taxType 类型（'甲' 或 '乙'，可选，默认'乙'）
     * @return Mono<SourceWithholdingTaxDomainDto> 源泉征收税额DTO
     */
    Mono<SourceWithholdingTaxDomainDto> sourceWithholdingTaxQuery(Integer amount, String taxType);

    /**
     * 查询源泉征收税额（支持扶养亲族等数量）
     * 根据金额、类型和扶养亲族等数量计算源泉征收税额
     *
     * @param amount 金额（扣除社会保险费等后的工资等金额）
     * @param taxType 类型（'甲' 或 '乙'，可选，默认'乙'）
     * @param dependentsCount 扶养亲族等数量（仅适用于甲类型，0-7人；超过7人时按7人查询后减额；乙类型传null）
     * @return Mono<SourceWithholdingTaxDomainDto> 源泉征收税额DTO
     */
    Mono<SourceWithholdingTaxDomainDto> sourceWithholdingTaxQuery(Integer amount, String taxType, Integer dependentsCount);
}

