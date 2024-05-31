package com.example.demo.Services;

import com.example.demo.Model.Client;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.SalesRepository;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ClientReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientServices {
    private final ClientRepository clientRepository;
    private final SalesRepository salesRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClientServices.class);


    public ClientServices(ClientRepository clientRepository, SalesRepository salesRepository) {
        this.clientRepository = clientRepository;
        this.salesRepository = salesRepository;
    }

    public Client addClient(Client client) throws IllegalAccessException {
        logger.info("Adding new client: {}", client);
        if (client.getFirstName().equals(null) || client.getLastName().equals(null)) {
            throw new IllegalAccessException("Last_NAme and First_NAme can not be null ");
        }
        if (clientRepository.existsByFirstName(client.getFirstName()) && clientRepository.existsByLastName(client.getLastName())) {
            throw new IllegalArgumentException(" client with name " + client.getFirstName() + client.getLastName() + " already exists.");
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long clientId, Client updatedClient) throws IllegalAccessException {
        logger.info("Updating client with ID {}: {}", clientId, updatedClient);
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalAccessException("Client with id " + clientId + " not found"));

        if (updatedClient.getFirstName() != null && !updatedClient.getFirstName().equals(existingClient.getFirstName())) {
            existingClient.setFirstName(updatedClient.getFirstName());
        }
        if (updatedClient.getLastName() != null && !updatedClient.getLastName().equals(existingClient.getLastName())) {
            existingClient.setLastName(updatedClient.getLastName());
        }
        if (updatedClient.getMobile() != null && !updatedClient.getMobile().equals(existingClient.getMobile())) {
            existingClient.setMobile(updatedClient.getMobile());
        }
        if (updatedClient.getEmail() != null && !updatedClient.getEmail().equals(existingClient.getEmail())) {
            existingClient.setEmail(updatedClient.getEmail());
        }
        if (updatedClient.getAddress() != null && !updatedClient.getAddress().equals(existingClient.getAddress())) {
            existingClient.setAddress(updatedClient.getAddress());
        }
        return clientRepository.save(existingClient);
    }

    public ClientDTO getClientById(Long clientId) throws IllegalAccessException {
        logger.info("Fetching client by ID: {}", clientId);
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalAccessException("Client with id " + clientId + " does not exist.");
        }

        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            return convertToDTO(client);
        } else {
            return null;
        }
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setMobile(client.getMobile());
        dto.setEmail(client.getEmail());
        dto.setAddress(client.getAddress());
        return dto;
    }


    public List<ClientDTO> getAllClients() {
        logger.info("Fetching all clients");
        List<Client> existingClients = clientRepository.findAll();
        return existingClients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteClientById(Long clientId) throws IllegalAccessException {
        logger.info("Deleting client with ID: {}", clientId);
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
        } else {
            throw new IllegalAccessException("Client with id " + clientId + " not found");
        }
    }

    public ClientReportDTO generateClientReport() {
        logger.info("Generating client report");
        ClientReportDTO report = new ClientReportDTO();
        report.setTotalNumberOfClients((int) clientRepository.count());
        report.setTopSpendingClients(findTopSpendingClients());
        report.setClientActivity(calculateClientActivity());
        report.setClientLocationStatistics(calculateClientLocationStatistics());
        return report;
    }

    private List<Client> findTopSpendingClients() {
        logger.info("Finding top spending clients");
        Pageable pageable = (Pageable) PageRequest.of(0, 10);
        return clientRepository.findTopSpendingClients(pageable);
    }

    private Map<String, Integer> calculateClientActivity() {
        logger.info("Calculating client activity");
        List<Object[]> clientActivityData = salesRepository.countSalesByClient();
        Map<String, Integer> clientActivityMap = new HashMap<>();

        for (Object[] data : clientActivityData) {
            Client client = (Client) data[0];
            Long count = (Long) data[1];
            clientActivityMap.put(client.getId().toString(), count.intValue());
        }

        return clientActivityMap;
    }

    private Map<String, Integer> calculateClientLocationStatistics() {
        logger.info("Calculating client location statistics");
        List<Object[]> locationStatisticsData = clientRepository.countClientsByLocation();
        Map<String, Integer> clientLocationStatisticsMap = new HashMap<>();

        for (Object[] data : locationStatisticsData) {
            String location = (String) data[0];
            Long count = (Long) data[1];
            clientLocationStatisticsMap.put(location, count.intValue());
        }

        return clientLocationStatisticsMap;
    }
}
