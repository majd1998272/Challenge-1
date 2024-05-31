package com.example.demo.dto;

public class InventoryRequestDTO {
    private int quantity;
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public InventoryRequestDTO(int quantity, Long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public InventoryRequestDTO() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryRequestDTO(int quantity) {
        this.quantity = quantity;
    }
}
