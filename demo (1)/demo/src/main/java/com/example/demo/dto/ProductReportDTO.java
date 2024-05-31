package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductReportDTO {
    private List<ProductInventoryDTO> inventoryStatus;
    private List<ProductSalesPerformanceDTO> salesPerformance;
    private List<ProductPricingAnalysisDTO> pricingAnalysis;

    public ProductReportDTO(List<ProductInventoryDTO> inventoryStatus, List<ProductSalesPerformanceDTO> salesPerformance, List<ProductPricingAnalysisDTO> pricingAnalysis) {
        this.inventoryStatus = inventoryStatus;
        this.salesPerformance = salesPerformance;
        this.pricingAnalysis = pricingAnalysis;
    }

    public List<ProductInventoryDTO> getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(List<ProductInventoryDTO> inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public List<ProductSalesPerformanceDTO> getSalesPerformance() {
        return salesPerformance;
    }

    public void setSalesPerformance(List<ProductSalesPerformanceDTO> salesPerformance) {
        this.salesPerformance = salesPerformance;
    }

    public List<ProductPricingAnalysisDTO> getPricingAnalysis() {
        return pricingAnalysis;
    }

    public void setPricingAnalysis(List<ProductPricingAnalysisDTO> pricingAnalysis) {
        this.pricingAnalysis = pricingAnalysis;
    }

    public ProductReportDTO() {
    }
}
