package com.example.demo.Services;

import com.example.demo.Model.Product;
import com.example.demo.Model.Sales;
import com.example.demo.Model.Seller;
import com.example.demo.Model.Transaction;
import com.example.demo.Repository.SalesRepository;
import com.example.demo.dto.SalesReportDTO;
import com.example.demo.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesServices {
    private static final Logger logger = LoggerFactory.getLogger(SalesServices.class);

    private final SalesRepository salesRepository;

    public SalesServices(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales createSales(Sales sales) {

        return salesRepository.save(sales);
    }

    public Sales editSales(Long salesId, List<TransactionDTO> transactionDTOs) throws IllegalAccessException {
        logger.info("Editing sales with ID: {}", salesId);
        Optional<Sales> optionalSales = salesRepository.findById(salesId);
        if (optionalSales.isPresent()) {
            Sales sales = optionalSales.get();
            List<Transaction> transactions = sales.getTransactions();
            for (TransactionDTO transactionDTO : transactionDTOs) {
                Optional<Transaction> optionalTransaction = transactions.stream()
                        .filter(t -> t.getId().equals(transactionDTO.getId()))
                        .findFirst();
                if (optionalTransaction.isPresent()) {
                    Transaction transaction = optionalTransaction.get();
                    transaction.setQuantity(transactionDTO.getQuantity());
                    transaction.setPrice(transactionDTO.getPrice());
                }
            }

            return salesRepository.save(sales);
        }
        throw new IllegalAccessException("Sales with id " + salesId + " not found");
    }

    public SalesReportDTO generateSalesReport(Date startDate, Date endDate) {
        List<Sales> sales = salesRepository.findByCreationDateBetween(startDate, endDate);

        int totalNumberOfSales = sales.size();
        double totalRevenue = calculateTotalRevenue(sales);
        List<Product> topSellingProducts = findTopSellingProducts(sales);
        List<Seller> topPerformingSellers = findTopPerformingSellers(sales);

        SalesReportDTO report = new SalesReportDTO();
        report.setTotalNumberOfSales(totalNumberOfSales);
        report.setTotalRevenue(totalRevenue);
        report.setTopSellingProducts(topSellingProducts);
        report.setTopPerformingSellers(topPerformingSellers);
        return report;
    }

    private double calculateTotalRevenue(List<Sales> sales) {
        return sales.stream().mapToDouble(Sales::getTotal).sum();
    }

    private List<Product> findTopSellingProducts(List<Sales> sales) {
        Map<Long, Integer> productQuantityMap = new HashMap<>();

        for (Sales sale : sales) {
            for (Transaction transaction : sale.getTransactions()) {
                Product product = transaction.getProduct();
                int quantity = transaction.getQuantity();
                productQuantityMap.put(product.getId(), productQuantityMap.getOrDefault(product.getId(), 0) + quantity);
            }
        }

        List<Map.Entry<Long, Integer>> sortedProductQuantityList = new ArrayList<>(productQuantityMap.entrySet());
        sortedProductQuantityList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        List<Product> topSellingProducts = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : sortedProductQuantityList) {
            Product product = new Product();
            product.setId(entry.getKey());
            topSellingProducts.add(product);
        }
        return topSellingProducts;
    }

    private List<Seller> findTopPerformingSellers(List<Sales> sales) {
        Map<Long, Double> sellerSalesMap = new HashMap<>();

        for (Sales sale : sales) {
            Seller seller = sale.getSeller();
            double totalAmount = sale.getTotal();
            sellerSalesMap.put(seller.getId(), sellerSalesMap.getOrDefault(seller.getId(), 0.0) + totalAmount);
        }

        List<Map.Entry<Long, Double>> sortedSellerSalesList = new ArrayList<>(sellerSalesMap.entrySet());
        sortedSellerSalesList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        List<Seller> topPerformingSellers = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : sortedSellerSalesList) {
            Seller seller = new Seller();
            seller.setId(entry.getKey());
            topPerformingSellers.add(seller);
        }

        return topPerformingSellers;
    }

}
