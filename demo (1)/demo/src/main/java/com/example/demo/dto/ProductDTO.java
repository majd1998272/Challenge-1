package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Date creationDate;
    private int availableQuantity;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductDTO(Long id, String name, String description, String category, Date creationDate, int availableQuantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public ProductDTO() {
    }
}
