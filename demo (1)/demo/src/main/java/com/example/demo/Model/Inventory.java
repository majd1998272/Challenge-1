package com.example.demo.Model;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@EnableJpaAuditing

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_Id")
    private Long id;

    private int quantity;
    private  double price ;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Inventory(Long id, int quantity, double price, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Inventory() {
    }

    public Inventory(Long id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
