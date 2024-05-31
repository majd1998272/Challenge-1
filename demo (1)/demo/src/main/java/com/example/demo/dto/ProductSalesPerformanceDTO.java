package com.example.demo.dto;

public class ProductSalesPerformanceDTO {
    private Long productId;
    private String productName;
    private int totalQuantitySold;
    private double totalSalesAmount;

    public ProductSalesPerformanceDTO() {
    }

    public ProductSalesPerformanceDTO(Long productId, String productName, int totalQuantitySold, double totalSalesAmount) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantitySold = totalQuantitySold;
        this.totalSalesAmount = totalSalesAmount;
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

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }

    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(double totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount;
    }
}
