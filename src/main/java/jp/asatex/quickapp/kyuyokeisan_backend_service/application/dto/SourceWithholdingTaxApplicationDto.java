package jp.asatex.quickapp.kyuyokeisan_backend_service.application.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 源泉征收税额查询结果 Application DTO
 */
public class SourceWithholdingTaxApplicationDto {

    private Integer taxAmount;
    private Integer baseTaxAmount;
    private BigDecimal excessTaxRate;
    private String calculationFormula;
    private Boolean requiresFormula;

    public SourceWithholdingTaxApplicationDto() {
    }

    public SourceWithholdingTaxApplicationDto(Integer taxAmount, Integer baseTaxAmount, BigDecimal excessTaxRate,
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

    public SourceWithholdingTaxApplicationDto setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public Integer getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public SourceWithholdingTaxApplicationDto setBaseTaxAmount(Integer baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
        return this;
    }

    public BigDecimal getExcessTaxRate() {
        return excessTaxRate;
    }

    public SourceWithholdingTaxApplicationDto setExcessTaxRate(BigDecimal excessTaxRate) {
        this.excessTaxRate = excessTaxRate;
        return this;
    }

    public String getCalculationFormula() {
        return calculationFormula;
    }

    public SourceWithholdingTaxApplicationDto setCalculationFormula(String calculationFormula) {
        this.calculationFormula = calculationFormula;
        return this;
    }

    public Boolean getRequiresFormula() {
        return requiresFormula;
    }

    public SourceWithholdingTaxApplicationDto setRequiresFormula(Boolean requiresFormula) {
        this.requiresFormula = requiresFormula;
        return this;
    }

    public static SourceWithholdingTaxApplicationDtoBuilder builder() {
        return new SourceWithholdingTaxApplicationDtoBuilder();
    }

    public static class SourceWithholdingTaxApplicationDtoBuilder {
        private Integer taxAmount;
        private Integer baseTaxAmount;
        private BigDecimal excessTaxRate;
        private String calculationFormula;
        private Boolean requiresFormula;

        public SourceWithholdingTaxApplicationDtoBuilder taxAmount(Integer taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public SourceWithholdingTaxApplicationDtoBuilder baseTaxAmount(Integer baseTaxAmount) {
            this.baseTaxAmount = baseTaxAmount;
            return this;
        }

        public SourceWithholdingTaxApplicationDtoBuilder excessTaxRate(BigDecimal excessTaxRate) {
            this.excessTaxRate = excessTaxRate;
            return this;
        }

        public SourceWithholdingTaxApplicationDtoBuilder calculationFormula(String calculationFormula) {
            this.calculationFormula = calculationFormula;
            return this;
        }

        public SourceWithholdingTaxApplicationDtoBuilder requiresFormula(Boolean requiresFormula) {
            this.requiresFormula = requiresFormula;
            return this;
        }

        public SourceWithholdingTaxApplicationDto build() {
            return new SourceWithholdingTaxApplicationDto(taxAmount, baseTaxAmount, excessTaxRate,
                    calculationFormula, requiresFormula);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceWithholdingTaxApplicationDto that = (SourceWithholdingTaxApplicationDto) o;
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
        return "SourceWithholdingTaxApplicationDto{" +
                "taxAmount=" + taxAmount +
                ", baseTaxAmount=" + baseTaxAmount +
                ", excessTaxRate=" + excessTaxRate +
                ", calculationFormula='" + calculationFormula + '\'' +
                ", requiresFormula=" + requiresFormula +
                '}';
    }
}

