package jp.asatex.quickapp.kyuyokeisan_backend_service.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 源泉征收税额查询结果 Controller DTO
 */
public class SourceWithholdingTaxDto {

    private Integer taxAmount;
    private Integer baseTaxAmount;
    private BigDecimal excessTaxRate;
    private String calculationFormula;
    private Boolean requiresFormula;

    public SourceWithholdingTaxDto() {
    }

    public SourceWithholdingTaxDto(Integer taxAmount, Integer baseTaxAmount, BigDecimal excessTaxRate,
                                    String calculationFormula, Boolean requiresFormula) {
        this.taxAmount = taxAmount;
        this.baseTaxAmount = baseTaxAmount;
        this.excessTaxRate = excessTaxRate;
        this.calculationFormula = calculationFormula;
        this.requiresFormula = requiresFormula;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public SourceWithholdingTaxDto setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public Integer getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public SourceWithholdingTaxDto setBaseTaxAmount(Integer baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
        return this;
    }

    public BigDecimal getExcessTaxRate() {
        return excessTaxRate;
    }

    public SourceWithholdingTaxDto setExcessTaxRate(BigDecimal excessTaxRate) {
        this.excessTaxRate = excessTaxRate;
        return this;
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public SourceWithholdingTaxDto setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula;
        return this;
    }

    public Boolean getRequiresFormula() {
        return requiresFormula;
    }

    public SourceWithholdingTaxDto setRequiresFormula(Boolean requiresFormula) {
        this.requiresFormula = requiresFormula;
        return this;
    }

    public static SourceWithholdingTaxDtoBuilder builder() {
        return new SourceWithholdingTaxDtoBuilder();
    }

    public static class SourceWithholdingTaxDtoBuilder {
        private Integer taxAmount;
        private Integer baseTaxAmount;
        private BigDecimal excessTaxRate;
        private String calculationFormula;
        private Boolean requiresFormula;

        public SourceWithholdingTaxDtoBuilder taxAmount(Integer taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public SourceWithholdingTaxDtoBuilder baseTaxAmount(Integer baseTaxAmount) {
            this.baseTaxAmount = baseTaxAmount;
            return this;
        }

        public SourceWithholdingTaxDtoBuilder excessTaxRate(BigDecimal excessTaxRate) {
            this.excessTaxRate = excessTaxRate;
            return this;
        }

        public SourceWithholdingTaxDtoBuilder calculationFormula(String calculationFormula) {
            this.calculationFormula = calculationFormula;
            return this;
        }

        public SourceWithholdingTaxDtoBuilder requiresFormula(Boolean requiresFormula) {
            this.requiresFormula = requiresFormula;
            return this;
        }

        public SourceWithholdingTaxDto build() {
            return new SourceWithholdingTaxDto(taxAmount, baseTaxAmount, excessTaxRate,
                    calculationFormula, requiresFormula);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceWithholdingTaxDto that = (SourceWithholdingTaxDto) o;
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
        return "SourceWithholdingTaxDto{" +
                "taxAmount=" + taxAmount +
                ", baseTaxAmount=" + baseTaxAmount +
                ", excessTaxRate=" + excessTaxRate +
                ", calculationFormula='" + calculationFormula + '\'' +
                ", requiresFormula=" + requiresFormula +
                '}';
    }
}

