package com.example.demo.Controller;

import com.example.demo.Model.Client;
import com.example.demo.Services.ClientServices;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ClientReportDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientServices clientService;

    public ClientController(ClientServices clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/addNewClient")
    public ResponseEntity<Object> addClient(@RequestBody Client client) {
        logger.info("Received request to add new client: {}", client);
        try {
            Client createdClient = clientService.addClient(client);
            logger.info("Client added successfully: {}", createdClient);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding new client", e);
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error in creating client: " + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/updateClient/{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable Long clientId, @RequestBody Client updatedClient) {
        logger.info("Received request to update client with ID {}: {}", clientId, updatedClient);
        try {
            Client client = clientService.updateClient(clientId, updatedClient);
            logger.info("Client updated successfully: {}", client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating client with ID " + clientId, e);
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error in updating client: " + e.getMessage());
        }
    }

    @GetMapping("/getClientById/{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable Long clientId) {
        try {
            ClientDTO client = clientService.getClientById(clientId);
            if (client != null) {
                return new ResponseEntity<>(client, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/getAllClients")
    public ResponseEntity<Object> getAllClients() {
        try {
            List<ClientDTO> clients = clientService.getAllClients();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error: " + e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/deleteClientById/{clientId}")
    public ResponseEntity<Object> deleteClientById(@PathVariable Long clientId) {
        try {
            clientService.deleteClientById(clientId);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }


    @GetMapping("/report")
    public ResponseEntity<Object> generateClientReport() {
        try {
            ClientReportDTO report = clientService.generateClientReport();
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error  : " + e.getMessage());
        }
    }


}
