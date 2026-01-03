package jp.asatex.quickapp.kyuyokeisan_backend_service.bean.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 源泉征收税额实体类
 * 对应 source_withholding_tax 表
 * 用于2025年度源泉征收税额表（月额表），用于所得税源泉征收的计算
 */
@Table("source_withholding_tax")
public class SourceWithholdingTax {

    @Id
    private Long id;

    @Column("min_amount")
    private Integer minAmount;

    @Column("max_amount")
    private Integer maxAmount;

    @Column("tax_type")
    private String taxType;

    @Column("dependents_count")
    private Integer dependentsCount;

    @Column("base_tax_amount")
    private Integer baseTaxAmount;

    @Column("tax_amount")
    private Integer taxAmount;

    @Column("excess_tax_rate")
    private BigDecimal excessTaxRate;

    @Column("calculation_formula")
    private String calculationFormula;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;

    // 默认构造函数
    public SourceWithholdingTax() {
    }

    // 全参构造函数（用于流式构建）
    public SourceWithholdingTax(Long id, Integer minAmount, Integer maxAmount, String taxType,
                                Integer dependentsCount, Integer baseTaxAmount, Integer taxAmount, 
                                BigDecimal excessTaxRate, String calculationFormula, 
                                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.taxType = taxType;
        this.dependentsCount = dependentsCount;
        this.baseTaxAmount = baseTaxAmount;
        this.taxAmount = taxAmount;
        this.excessTaxRate = excessTaxRate;
        this.calculationFormula = calculationFormula;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter 和 Setter 方法（流式编程风格，返回自身）
    public Long getId() {
        return id;
    }

    public SourceWithholdingTax setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public SourceWithholdingTax setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
        return this;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public SourceWithholdingTax setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
        return this;
    }

    public String getTaxType() {
        return taxType;
    }

    public SourceWithholdingTax setTaxType(String taxType) {
        this.taxType = taxType;
        return this;
    }

    public Integer getDependentsCount() {
        return dependentsCount;
    }

    public SourceWithholdingTax setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
        return this;
    }

    public Integer getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public SourceWithholdingTax setBaseTaxAmount(Integer baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
        return this;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public SourceWithholdingTax setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public BigDecimal getExcessTaxRate() {
        return excessTaxRate;
    }

    public SourceWithholdingTax setExcessTaxRate(BigDecimal excessTaxRate) {
        this.excessTaxRate = excessTaxRate;
        return this;
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public SourceWithholdingTax setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public SourceWithholdingTax setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public SourceWithholdingTax setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // 流式编程风格的 with 方法，返回新实例（不可变风格）
    public SourceWithholdingTax withId(Long id) {
        return new SourceWithholdingTax(id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withMinAmount(Integer minAmount) {
        return new SourceWithholdingTax(this.id, minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withMaxAmount(Integer maxAmount) {
        return new SourceWithholdingTax(this.id, this.minAmount, maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withTaxType(String taxType) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withDependentsCount(Integer dependentsCount) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withBaseTaxAmount(Integer baseTaxAmount) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withTaxAmount(Integer taxAmount) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withExcessTaxRate(BigDecimal excessTaxRate) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, excessTaxRate, 
                this.calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withCalculationFormula(String calculationFormula) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                calculationFormula, this.createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withCreatedAt(LocalDateTime createdAt) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, createdAt, this.updatedAt);
    }

    public SourceWithholdingTax withUpdatedAt(LocalDateTime updatedAt) {
        return new SourceWithholdingTax(this.id, this.minAmount, this.maxAmount, this.taxType,
                this.dependentsCount, this.baseTaxAmount, this.taxAmount, this.excessTaxRate, 
                this.calculationFormula, this.createdAt, updatedAt);
    }

    // 流式编程风格的 Builder 模式
    public static SourceWithholdingTaxBuilder builder() {
        return new SourceWithholdingTaxBuilder();
    }

    public static class SourceWithholdingTaxBuilder {
        private Long id;
        private Integer minAmount;
        private Integer maxAmount;
        private String taxType;
        private Integer dependentsCount;
        private Integer baseTaxAmount;
        private Integer taxAmount;
        private BigDecimal excessTaxRate;
        private String calculationFormula;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public SourceWithholdingTaxBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SourceWithholdingTaxBuilder minAmount(Integer minAmount) {
            this.minAmount = minAmount;
            return this;
        }

        public SourceWithholdingTaxBuilder maxAmount(Integer maxAmount) {
            this.maxAmount = maxAmount;
            return this;
        }

        public SourceWithholdingTaxBuilder taxType(String taxType) {
            this.taxType = taxType;
            return this;
        }

        public SourceWithholdingTaxBuilder dependentsCount(Integer dependentsCount) {
            this.dependentsCount = dependentsCount;
            return this;
        }

        public SourceWithholdingTaxBuilder baseTaxAmount(Integer baseTaxAmount) {
            this.baseTaxAmount = baseTaxAmount;
            return this;
        }

        public SourceWithholdingTaxBuilder taxAmount(Integer taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public SourceWithholdingTaxBuilder excessTaxRate(BigDecimal excessTaxRate) {
            this.excessTaxRate = excessTaxRate;
            return this;
        }

        public SourceWithholdingTaxBuilder calculationFormula(String calculationFormula) {
            this.calculationFormula = calculationFormula;
            return this;
        }

        public SourceWithholdingTaxBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SourceWithholdingTaxBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public SourceWithholdingTax build() {
            return new SourceWithholdingTax(id, minAmount, maxAmount, taxType, dependentsCount, 
                    baseTaxAmount, taxAmount, excessTaxRate, calculationFormula, createdAt, updatedAt);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceWithholdingTax that = (SourceWithholdingTax) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(minAmount, that.minAmount) &&
                Objects.equals(maxAmount, that.maxAmount) &&
                Objects.equals(taxType, that.taxType) &&
                Objects.equals(dependentsCount, that.dependentsCount) &&
                Objects.equals(baseTaxAmount, that.baseTaxAmount) &&
                Objects.equals(taxAmount, that.taxAmount) &&
                Objects.equals(excessTaxRate, that.excessTaxRate) &&
                Objects.equals(calculationFormula, that.calculationFormula) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minAmount, maxAmount, taxType, dependentsCount, baseTaxAmount, 
                taxAmount, excessTaxRate, calculationFormula, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "SourceWithholdingTax{" +
                "id=" + id +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", taxType='" + taxType + '\'' +
                ", dependentsCount=" + dependentsCount +
                ", baseTaxAmount=" + baseTaxAmount +
                ", taxAmount=" + taxAmount +
                ", excessTaxRate=" + excessTaxRate +
                ", calculationFormula='" + calculationFormula + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

