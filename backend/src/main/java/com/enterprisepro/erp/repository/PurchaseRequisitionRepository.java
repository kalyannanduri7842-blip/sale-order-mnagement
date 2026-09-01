package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.PurchaseRequisition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRequisitionRepository extends JpaRepository<PurchaseRequisition, Long> {
    Optional<PurchaseRequisition> findByPrNumber(String prNumber);
    List<PurchaseRequisition> findByStatus(String status);
    
    @Query("SELECT COUNT(p) FROM PurchaseRequisition p WHERE p.status = 'SUBMITTED'")
    long countPendingRequisitions();

    Page<PurchaseRequisition> findByPrNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String prNumber, String status, Pageable pageable);
}
