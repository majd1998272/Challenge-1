package com.example.demo.Controller;

import com.example.demo.Model.Inventory;
import com.example.demo.Services.InventoryServices;
import com.example.demo.dto.InventoryRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryServices inventoryService;
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);


    public InventoryController(InventoryServices inventoryService) {
        this.inventoryService = inventoryService;

    }

    @PostMapping("/addProductToInventory")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        logger.info("Received request to add new product to inventory: {}", inventory);
        Inventory newInventory = inventoryService.addNewInventory(inventory);
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

}
