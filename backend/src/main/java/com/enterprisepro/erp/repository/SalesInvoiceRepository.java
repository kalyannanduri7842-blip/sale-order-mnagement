package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.entity.SalesInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
    Optional<SalesInvoice> findByInvoiceNumber(String invoiceNumber);
    List<SalesInvoice> findByCustomer(Customer customer);
    List<SalesInvoice> findByStatus(String status);
    
    @Query("SELECT SUM(s.totalAmount) FROM SalesInvoice s WHERE s.invoiceDate BETWEEN :startDate AND :endDate AND s.status != 'CANCELLED'")
    BigDecimal sumTotalInvoicedBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(s.totalAmount - s.paidAmount) FROM SalesInvoice s WHERE s.status != 'PAID' AND s.status != 'CANCELLED'")
    BigDecimal sumPendingCustomerReceivables();

    Page<SalesInvoice> findByInvoiceNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String invoiceNumber, String status, Pageable pageable);
}
