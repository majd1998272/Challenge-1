package com.example.demo.dto;

import com.example.demo.Model.Client;
import com.example.demo.Model.Seller;
import lombok.Data;

import java.util.Date;

@Data
public class SalesDTO {
    private Long id;
    private Date creationDate;
    private Client client;
    private Seller seller;
    private double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public SalesDTO(Long id, Date creationDate, Client client, Seller seller, double total) {
        this.id = id;
        this.creationDate = creationDate;
        this.client = client;
        this.seller = seller;
        this.total = total;
    }

    public SalesDTO() {
    }
}
