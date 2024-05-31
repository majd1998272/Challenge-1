package com.example.demo.Model;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@EnableJpaAuditing

@Entity
//@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Id")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "Creation_Date", nullable = false)
    private Date creationDate;

    @Column(name = "Available_Quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "Price", nullable = false)
    private double price;

    @NotNull
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Inventory inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Product() {
    }

    public Product(Long id, String name, String description, String category, Date creationDate, int availableQuantity, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

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

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", creationDate=" + creationDate +
                ", availableQuantity=" + availableQuantity +
                ", price=" + price +
                '}';
    }
}
