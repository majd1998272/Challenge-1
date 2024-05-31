package com.example.demo.Controller;

import com.example.demo.Model.Seller;
import com.example.demo.Services.SellerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Seller")
public class SellerController {
    private final SellerServices sellerService;

    public SellerController(SellerServices sellerService) {
        this.sellerService = sellerService;
    }


    @PostMapping("/addSaler")
    public ResponseEntity<Object> addSeller(@RequestBody Seller seller) {
        try {
            Seller savedSeller = sellerService.addSeller(seller);
            return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error In Creat Saller : " + e.getMessage());
        }
    }


}
