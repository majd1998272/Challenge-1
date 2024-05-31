package com.example.demo.Repository;

import com.example.demo.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByFirstName(String firstName);

    boolean existsByLastName(String firstName);

    @Query("SELECT c, SUM(t.quantity * t.price) AS totalSpending " +
            "FROM Client c " +
            "JOIN Transaction t " +
            "GROUP BY c " +
            "ORDER BY totalSpending DESC")
    List<Client> findTopSpendingClients(Pageable pageable);

    @Query("SELECT c, COUNT(c) FROM Client c GROUP BY c.address")
    List<Object[]> countClientsByLocation();
}
