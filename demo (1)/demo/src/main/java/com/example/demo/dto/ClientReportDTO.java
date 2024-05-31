package com.example.demo.dto;

import com.example.demo.Model.Client;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ClientReportDTO {
    private int totalNumberOfClients;
    private List<Client> topSpendingClients;
    private Map<String, Integer> clientActivity;
    private Map<String, Integer> clientLocationStatistics;

    public int getTotalNumberOfClients() {
        return totalNumberOfClients;
    }

    public void setTotalNumberOfClients(int totalNumberOfClients) {
        this.totalNumberOfClients = totalNumberOfClients;
    }

    public List<Client> getTopSpendingClients() {
        return topSpendingClients;
    }

    public void setTopSpendingClients(List<Client> topSpendingClients) {
        this.topSpendingClients = topSpendingClients;
    }

    public Map<String, Integer> getClientActivity() {
        return clientActivity;
    }

    public void setClientActivity(Map<String, Integer> clientActivity) {
        this.clientActivity = clientActivity;
    }

    public Map<String, Integer> getClientLocationStatistics() {
        return clientLocationStatistics;
    }

    public void setClientLocationStatistics(Map<String, Integer> clientLocationStatistics) {
        this.clientLocationStatistics = clientLocationStatistics;
    }

    public ClientReportDTO(int totalNumberOfClients, List<Client> topSpendingClients, Map<String, Integer> clientActivity, Map<String, Integer> clientLocationStatistics) {
        this.totalNumberOfClients = totalNumberOfClients;
        this.topSpendingClients = topSpendingClients;
        this.clientActivity = clientActivity;
        this.clientLocationStatistics = clientLocationStatistics;
    }

    public ClientReportDTO() {
    }
}
