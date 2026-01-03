package jp.asatex.quickapp.kyuyokeisan_backend_service.repository;

import jp.asatex.quickapp.kyuyokeisan_backend_service.bean.entity.SourceWithholdingTax;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 源泉征收税 Repository 接口
 * 用于查询源泉征收税额信息
 * 使用 R2DBC 响应式编程风格
 */
@Repository
public interface SourceWithholdingTaxRepository extends ReactiveCrudRepository<SourceWithholdingTax, Long> {

    /**
     * 根据金额范围查找对应的源泉征收税额
     * 查找 min_amount <= amount < max_amount 的记录
     * 如果 max_amount 为 NULL，则查找 min_amount <= amount 的记录
     * 对于甲类型，需要匹配dependents_count；对于乙类型，dependents_count为NULL
     *
     * @param amount 金额
     * @param taxType 类型（'甲' 或 '乙'）
     * @param dependentsCount 扶养亲族等数量（仅适用于甲类型，0-7人；乙类型传null）
     * @return Mono<SourceWithholdingTax> 源泉征收税额信息
     */
    @Query("SELECT * FROM source_withholding_tax WHERE tax_type = $2 AND " +
           "($3 IS NULL AND dependents_count IS NULL OR $3 IS NOT NULL AND dependents_count = $3) AND " +
           "$1 >= min_amount AND ($1 < max_amount OR max_amount IS NULL) " +
           "ORDER BY min_amount DESC LIMIT 1")
    Mono<SourceWithholdingTax> findSourceWithholdingTaxByAmountAndTaxTypeAndDependents(
            Integer amount, String taxType, Integer dependentsCount);
}

