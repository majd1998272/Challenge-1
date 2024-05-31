package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductInventoryDTO {
    private Long productId;
    private double productPrice;
    private int availableQuantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProductInventoryDTO(Long productId, double productPrice, int availableQuantity) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.availableQuantity = availableQuantity;
    }

    public ProductInventoryDTO() {
    }
}
