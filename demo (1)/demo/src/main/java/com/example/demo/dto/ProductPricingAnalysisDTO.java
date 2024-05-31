package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductPricingAnalysisDTO {
    private Long productId;
    private String productName;
    private double averagePrice;
    private double highestPrice;
    private double lowestPrice;

    public ProductPricingAnalysisDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public ProductPricingAnalysisDTO(Long productId, String productName, double averagePrice, double highestPrice, double lowestPrice) {
        this.productId = productId;
        this.productName = productName;
        this.averagePrice = averagePrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
    }
}
