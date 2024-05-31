package com.example.demo.Repository;

import com.example.demo.Model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByCreationDateBetween(Date startDate, Date endDate);

    @Query("SELECT s.client, COUNT(s) FROM Sales s GROUP BY s.client")
    List<Object[]> countSalesByClient();


    @Query("SELECT AVG(t.price) FROM Transaction t WHERE t.product.id = :id")
    double findAveragePriceByProduct(@Param("id") Long id);

    @Query("SELECT MAX(t.price) FROM Transaction t WHERE t.product.id = :id")
    double findHighestPriceByProduct(@Param("id") Long id);

    @Query("SELECT MIN(t.price) FROM Transaction t WHERE t.product.id = :id")
    double findLowestPriceByProduct(@Param("id") Long id);

    @Query("SELECT SUM(t.quantity) FROM Transaction t WHERE t.product.id = :id")
    int findTotalQuantitySoldByProduct(@Param("id") Long id);

    @Query("SELECT SUM(t.quantity * t.price) FROM Transaction t WHERE t.product.id = :id")
    double findTotalSalesAmountByProduct(@Param("id") Long id);
}
