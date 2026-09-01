package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.PurchaseInvoice;
import com.enterprisepro.erp.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long> {
    Optional<PurchaseInvoice> findByInvoiceNumber(String invoiceNumber);
    List<PurchaseInvoice> findByVendor(Vendor vendor);
    List<PurchaseInvoice> findByStatus(String status);
    
    @Query("SELECT SUM(p.totalAmount - p.paidAmount) FROM PurchaseInvoice p WHERE p.status != 'PAID'")
    BigDecimal sumPendingVendorPayables();

    Page<PurchaseInvoice> findByInvoiceNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String invoiceNumber, String status, Pageable pageable);
}
