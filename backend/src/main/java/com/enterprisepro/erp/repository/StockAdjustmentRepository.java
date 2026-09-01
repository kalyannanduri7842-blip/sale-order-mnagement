package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.StockAdjustment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockAdjustmentRepository extends JpaRepository<StockAdjustment, Long> {
    Optional<StockAdjustment> findByAdjustmentNumber(String adjustmentNumber);
    Page<StockAdjustment> findAllByOrderByAdjustmentDateDesc(Pageable pageable);
}
