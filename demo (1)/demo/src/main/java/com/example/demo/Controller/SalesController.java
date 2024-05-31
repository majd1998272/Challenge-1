package com.example.demo.Controller;

import com.example.demo.Model.Sales;
import com.example.demo.Services.SalesServices;
import com.example.demo.dto.SalesDTO;
import com.example.demo.dto.SalesReportDTO;
import com.example.demo.dto.TransactionDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Sales")
public class SalesController {


    private final SalesServices salesService;

    public SalesController(SalesServices salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/getAllSales")
    public ResponseEntity<Object> getAllSales() {
        try {
            List<Sales> sales = salesService.getAllSales();
            List<SalesDTO> salesDTOs = sales.stream().map(this::convertToDTO).collect(Collectors.toList());
            return new ResponseEntity<>(salesDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }

    @PostMapping("/createSales")
    public ResponseEntity<Object> createSales(@RequestBody SalesDTO salesDTO) {
        try {
            Sales sales = convertToEntity(salesDTO);
            Sales createdSales = salesService.createSales(sales);
            return new ResponseEntity<>(convertToDTO(createdSales), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }

    @PutMapping("/edit/{salesId}")
    public ResponseEntity<Object> editSales(@PathVariable Long salesId, @RequestBody List<TransactionDTO> transactionDTOs) throws IllegalAccessException {
        try {
            Sales editedSales = salesService.editSales(salesId, transactionDTOs);
            return new ResponseEntity<>(convertToDTO(editedSales), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }


    @GetMapping("/report")
    public ResponseEntity<Object> generateSalesReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            SalesReportDTO report = salesService.generateSalesReport(startDate, endDate);
            return new ResponseEntity<>(report, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }

    }


    private Sales convertToEntity(SalesDTO salesDTO) {
        Sales sales = new Sales();
        sales.setCreationDate(salesDTO.getCreationDate());
        sales.setClient(salesDTO.getClient());
        sales.setSeller(salesDTO.getSeller());
        sales.setTotal(salesDTO.getTotal());
        return sales;
    }

    private SalesDTO convertToDTO(Sales sales) {
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.setId(sales.getId());
        salesDTO.setCreationDate(sales.getCreationDate());
        salesDTO.setClient(sales.getClient());
        salesDTO.setSeller(sales.getSeller());
        salesDTO.setTotal(sales.getTotal());
        return salesDTO;
    }

}
