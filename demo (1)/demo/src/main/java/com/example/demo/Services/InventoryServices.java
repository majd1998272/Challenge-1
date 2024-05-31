package com.example.demo.Services;

import com.example.demo.Model.Inventory;
import com.example.demo.Model.Product;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.dto.InventoryRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InventoryServices {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    ;


    public InventoryServices(InventoryRepository inventoryRepository, ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }


    public Inventory addNewInventory(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }
}
