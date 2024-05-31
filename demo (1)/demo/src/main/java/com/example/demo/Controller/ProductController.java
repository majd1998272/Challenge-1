package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Services.ProductService;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductReportDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    @PostMapping("/createNewProduct")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error In Creat Product : " + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(productId, updatedProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error In Update Product : " + e.getMessage());
        }
    }


    @GetMapping("/getAllProducts")
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }


    }


    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) {
        try {
            ProductDTO product = productService.getProductById(productId);
            if (product != null) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteProductById/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long productId) throws IllegalAccessException {
        try {
            productService.deleteProductById(productId);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }


    @GetMapping("/report")
    public ResponseEntity<ProductReportDTO> generateProductReport() {
        ProductReportDTO report = productService.generateProductReport();
        return ResponseEntity.ok(report);
    }


}
