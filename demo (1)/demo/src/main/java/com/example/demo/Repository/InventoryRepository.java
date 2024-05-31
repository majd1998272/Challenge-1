package com.example.demo.Repository;

import com.example.demo.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i.quantity FROM Inventory i WHERE i.product.id = :productId")
    int findAvailableQuantityByProduct(@Param("productId") Long productId);
}
