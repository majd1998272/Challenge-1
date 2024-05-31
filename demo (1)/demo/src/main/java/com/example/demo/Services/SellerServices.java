package com.example.demo.Services;

import com.example.demo.Model.Seller;
import com.example.demo.Repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerServices {
    private final SellerRepository sellerRepository;

    public SellerServices(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller addSeller(Seller seller) {
        if (seller == null) {
            throw new IllegalArgumentException("Seller cannot be null");
        }
        return sellerRepository.save(seller);
    }
}
