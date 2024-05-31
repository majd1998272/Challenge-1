package com.example.demo.Services;

import com.example.demo.Model.Product;
import com.example.demo.Repository.InventoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.SalesRepository;
import com.example.demo.dto.*;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;
    private final InventoryRepository inventoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public ProductService(ProductRepository productRepository, SalesRepository salesRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Product createProduct(Product product) throws IllegalAccessException {
        logger.info("Creating product: {}", product.getName());
        if (productRepository.existsByName(product.getName())) {
            throw new IllegalAccessException("A product with name" + product.getName() + "already exists.");
        }
         productRepository.save(product);
        return product;


    }

    public Product updateProduct(Long productId, Product updatedProduct) throws IllegalAccessException {
        logger.info("Updating product with ID {}: {}", productId, updatedProduct.getName());
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalAccessException("Product with id " + productId + " not found"));
        if (updatedProduct.getName() != null && !updatedProduct.getName().equals(existingProduct.getName())) {
            existingProduct.setName(updatedProduct.getName());
        }
        if (updatedProduct.getDescription() != null && !updatedProduct.getDescription().equals(existingProduct.getDescription())) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getCategory() != null && !updatedProduct.getCategory().equals(existingProduct.getCategory())) {
            existingProduct.setCategory(updatedProduct.getCategory());
        }

        if (updatedProduct.getAvailableQuantity() != existingProduct.getAvailableQuantity()) {

            existingProduct.setAvailableQuantity(updatedProduct.getAvailableQuantity());
        }

        if (updatedProduct.getPrice() != existingProduct.getPrice()) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }


        return productRepository.save(existingProduct);


    }

    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> existingProducts = productRepository.findAllByAvailableQuantityGreaterThan(0);
        return existingProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setCreationDate(product.getCreationDate());
        dto.setAvailableQuantity(product.getAvailableQuantity());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public ProductDTO getProductById(Long productId) throws IllegalAccessException {
        logger.info("Fetching product by ID: {}", productId);
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalAccessException("product with Id" + productId + "does not  exist.");
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            return convertToDTO(product);
        } else {
            return null;
        }
    }


    public void deleteProductById(Long productId) throws IllegalAccessException {
        logger.info("Deleting product with ID: {}", productId);
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalAccessException("Product with id " + productId + " not found");
        }
    }


    public List<ProductInventoryDTO> getProductInventoryStatus() {
        logger.info("Getting product inventory status");
        List<ProductInventoryDTO> inventoryStatusList = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            int availableQuantity = inventoryRepository.findAvailableQuantityByProduct(product.getId());
            ProductInventoryDTO inventoryDTO = new ProductInventoryDTO();
            inventoryDTO.setProductId(product.getId());
            inventoryDTO.setProductPrice(product.getPrice());
            inventoryDTO.setAvailableQuantity(availableQuantity);
            inventoryStatusList.add(inventoryDTO);
        }

        return inventoryStatusList;
    }


    public ProductReportDTO generateProductReport() {
        logger.info("Generating product report");
        ProductReportDTO report = new ProductReportDTO();
        report.setInventoryStatus(getProductInventoryStatus());
        report.setSalesPerformance(getProductSalesPerformance());
        report.setPricingAnalysis(getProductPricingAnalysis());
        return report;
    }

    public List<ProductSalesPerformanceDTO> getProductSalesPerformance() {
        logger.info("Getting product sales performance");
        List<ProductSalesPerformanceDTO> salesPerformanceList = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            int totalQuantitySold = salesRepository.findTotalQuantitySoldByProduct(product.getId());
            double totalSalesAmount = salesRepository.findTotalSalesAmountByProduct(product.getId());
            ProductSalesPerformanceDTO performanceDTO = new ProductSalesPerformanceDTO();
            performanceDTO.setProductId(product.getId());
            performanceDTO.setProductName(product.getName());
            performanceDTO.setTotalQuantitySold(totalQuantitySold);
            performanceDTO.setTotalSalesAmount(totalSalesAmount);
            salesPerformanceList.add(performanceDTO);
        }

        return salesPerformanceList;
    }

    public List<ProductPricingAnalysisDTO> getProductPricingAnalysis() {
        logger.info("Performing product pricing analysis");
        List<ProductPricingAnalysisDTO> pricingAnalysisList = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            double averagePrice = salesRepository.findAveragePriceByProduct(product.getId());
            double highestPrice = salesRepository.findHighestPriceByProduct(product.getId());
            double lowestPrice = salesRepository.findLowestPriceByProduct(product.getId());
            ProductPricingAnalysisDTO pricingDTO = new ProductPricingAnalysisDTO();
            pricingDTO.setProductId(product.getId());
            pricingDTO.setProductName(product.getName());
            pricingDTO.setAveragePrice(averagePrice);
            pricingDTO.setHighestPrice(highestPrice);
            pricingDTO.setLowestPrice(lowestPrice);
            pricingAnalysisList.add(pricingDTO);
        }

        return pricingAnalysisList;
    }


}


