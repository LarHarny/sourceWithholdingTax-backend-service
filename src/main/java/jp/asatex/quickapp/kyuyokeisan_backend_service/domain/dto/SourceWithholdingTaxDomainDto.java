package jp.asatex.quickapp.kyuyokeisan_backend_service.domain.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 源泉征收税额查询结果 DTO
 */
public class SourceWithholdingTaxDomainDto {

    /**
     * 税额
     */
    private Integer taxAmount;

    /**
     * 基础税额（用于公式计算）
     */
    private Integer baseTaxAmount;

    /**
     * 超过部分的税率（用于公式计算）
     */
    private BigDecimal excessTaxRate;

    /**
     * 计算公式
     */
    private String calculationFormula;

    /**
     * 是否需要公式计算
     */
    private Boolean requiresFormula;

    // 默认构造函数
    public SourceWithholdingTaxDomainDto() {
    }

    // 全参构造函数
    public SourceWithholdingTaxDomainDto(Integer taxAmount, Integer baseTaxAmount, BigDecimal excessTaxRate,
                                         String calculationFormula, Boolean requiresFormula) {
        this.taxAmount = taxAmount;
        this.baseTaxAmount = baseTaxAmount;
        this.excessTaxRate = excessTaxRate;
        this.calculationFormula = calculationFormula;
        this.requiresFormula = requiresFormula;
    }

    // Getter 和 Setter 方法（流式风格）
    public Integer getTaxAmount() {
        return taxAmount;
    }

    public SourceWithholdingTaxDomainDto setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public Integer getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public SourceWithholdingTaxDomainDto setBaseTaxAmount(Integer baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
        return this;
    }

    public BigDecimal getExcessTaxRate() {
        return excessTaxRate;
    }

    public SourceWithholdingTaxDomainDto setExcessTaxRate(BigDecimal excessTaxRate) {
        this.excessTaxRate = excessTaxRate;
        return this;
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public SourceWithholdingTaxDomainDto setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula;
        return this;
    }

    public Boolean getRequiresFormula() {
        return requiresFormula;
    }

    public SourceWithholdingTaxDomainDto setRequiresFormula(Boolean requiresFormula) {
        this.requiresFormula = requiresFormula;
        return this;
    }

    // 流式编程风格的 with 方法，返回新实例（不可变风格）
    public SourceWithholdingTaxDomainDto withTaxAmount(Integer taxAmount) {
        return new SourceWithholdingTaxDomainDto(taxAmount, this.baseTaxAmount, this.excessTaxRate,
                this.calculationFormula, this.requiresFormula);
    }

    public SourceWithholdingTaxDomainDto withBaseTaxAmount(Integer baseTaxAmount) {
        return new SourceWithholdingTaxDomainDto(this.taxAmount, baseTaxAmount, this.excessTaxRate,
                this.calculationFormula, this.requiresFormula);
    }

    public SourceWithholdingTaxDomainDto withExcessTaxRate(BigDecimal excessTaxRate) {
        return new SourceWithholdingTaxDomainDto(this.taxAmount, this.baseTaxAmount, excessTaxRate,
                this.calculationFormula, this.requiresFormula);
    }

    public SourceWithholdingTaxDomainDto withCalculationFormula(String calculationFormula) {
        return new SourceWithholdingTaxDomainDto(this.taxAmount, this.baseTaxAmount, this.excessTaxRate,
                calculationFormula, this.requiresFormula);
    }

    public SourceWithholdingTaxDomainDto withRequiresFormula(Boolean requiresFormula) {
        return new SourceWithholdingTaxDomainDto(this.taxAmount, this.baseTaxAmount, this.excessTaxRate,
                this.calculationFormula, requiresFormula);
    }

    // 流式编程风格的 builder 方法
    public static SourceWithholdingTaxDomainDtoBuilder builder() {
        return new SourceWithholdingTaxDomainDtoBuilder();
    }

    public static class SourceWithholdingTaxDomainDtoBuilder {
        private Integer taxAmount;
        private Integer baseTaxAmount;
        private BigDecimal excessTaxRate;
        private String calculationFormula;
        private Boolean requiresFormula;

        public SourceWithholdingTaxDomainDtoBuilder taxAmount(Integer taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public SourceWithholdingTaxDomainDtoBuilder baseTaxAmount(Integer baseTaxAmount) {
            this.baseTaxAmount = baseTaxAmount;
            return this;
        }

        public SourceWithholdingTaxDomainDtoBuilder excessTaxRate(BigDecimal excessTaxRate) {
            this.excessTaxRate = excessTaxRate;
            return this;
        }

        public SourceWithholdingTaxDomainDtoBuilder calculationFormula(String calculationFormula) {
            this.calculationFormula = calculationFormula;
            return this;
        }

        public SourceWithholdingTaxDomainDtoBuilder requiresFormula(Boolean requiresFormula) {
            this.requiresFormula = requiresFormula;
            return this;
        }

        public SourceWithholdingTaxDomainDto build() {
            return new SourceWithholdingTaxDomainDto(taxAmount, baseTaxAmount, excessTaxRate,
                    calculationFormula, requiresFormula);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceWithholdingTaxDomainDto that = (SourceWithholdingTaxDomainDto) o;
        return Objects.equals(taxAmount, that.taxAmount) &&
                Objects.equals(baseTaxAmount, that.baseTaxAmount) &&
                Objects.equals(excessTaxRate, that.excessTaxRate) &&
                Objects.equals(calculationFormula, that.calculationFormula) &&
                Objects.equals(requiresFormula, that.requiresFormula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxAmount, baseTaxAmount, excessTaxRate, calculationFormula, requiresFormula);
    }

    @Override
    public String toString() {
        return "SourceWithholdingTaxDomainDto{" +
                "taxAmount=" + taxAmount +
                ", baseTaxAmount=" + baseTaxAmount +
                ", excessTaxRate=" + excessTaxRate +
                ", calculationFormula='" + calculationFormula + '\'' +
                ", requiresFormula=" + requiresFormula +
                '}';
    }
}

