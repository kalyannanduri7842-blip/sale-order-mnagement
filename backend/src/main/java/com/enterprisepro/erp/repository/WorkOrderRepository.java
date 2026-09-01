package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    Optional<WorkOrder> findByOrderNumber(String orderNumber);
    List<WorkOrder> findByStatus(String status);
    Page<WorkOrder> findByOrderNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String orderNumber, String status, Pageable pageable);
}
