package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.PurchaseOrder;
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
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByPoNumber(String poNumber);
    List<PurchaseOrder> findByVendor(Vendor vendor);
    List<PurchaseOrder> findByStatus(String status);
    
    @Query("SELECT COUNT(p) FROM PurchaseOrder p WHERE p.status = 'ISSUED' OR p.status = 'PARTIALLY_RECEIVED'")
    long countPendingPurchaseOrders();

    @Query("SELECT SUM(p.grandTotal) FROM PurchaseOrder p WHERE p.status != 'CANCELLED'")
    BigDecimal sumTotalPurchaseValue();

    Page<PurchaseOrder> findByPoNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String poNumber, String status, Pageable pageable);
}
