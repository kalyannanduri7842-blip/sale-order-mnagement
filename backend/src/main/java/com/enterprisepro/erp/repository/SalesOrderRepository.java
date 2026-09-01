package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.entity.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    Optional<SalesOrder> findBySoNumber(String soNumber);
    List<SalesOrder> findByCustomer(Customer customer);
    List<SalesOrder> findByStatus(String status);

    @Query("SELECT COUNT(s) FROM SalesOrder s WHERE s.status = 'PENDING' OR s.status = 'PROCESSING'")
    long countPendingSalesOrders();

    @Query("SELECT SUM(s.grandTotal) FROM SalesOrder s WHERE s.status != 'CANCELLED'")
    BigDecimal sumTotalSalesValue();

    Page<SalesOrder> findBySoNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String soNumber, String status, Pageable pageable);
}
