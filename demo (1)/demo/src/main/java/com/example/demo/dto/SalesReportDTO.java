package com.example.demo.dto;

import com.example.demo.Model.Product;
import com.example.demo.Model.Seller;
import lombok.Data;

import java.util.List;

@Data
public class SalesReportDTO {
    private int totalNumberOfSales;
    private double totalRevenue;
    private List<Product> topSellingProducts;
    private List<Seller> topPerformingSellers;

    public SalesReportDTO() {
    }

    public SalesReportDTO(int totalNumberOfSales, double totalRevenue, List<Product> topSellingProducts, List<Seller> topPerformingSellers) {
        this.totalNumberOfSales = totalNumberOfSales;
        this.totalRevenue = totalRevenue;
        this.topSellingProducts = topSellingProducts;
        this.topPerformingSellers = topPerformingSellers;
    }

    public int getTotalNumberOfSales() {
        return totalNumberOfSales;
    }

    public void setTotalNumberOfSales(int totalNumberOfSales) {
        this.totalNumberOfSales = totalNumberOfSales;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<Product> getTopSellingProducts() {
        return topSellingProducts;
    }

    public void setTopSellingProducts(List<Product> topSellingProducts) {
        this.topSellingProducts = topSellingProducts;
    }

    public List<Seller> getTopPerformingSellers() {
        return topPerformingSellers;
    }

    public void setTopPerformingSellers(List<Seller> topPerformingSellers) {
        this.topPerformingSellers = topPerformingSellers;
    }
}
