package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Product;
import com.enterprisepro.erp.entity.StockMovement;
import com.enterprisepro.erp.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductOrderByMovementDateDesc(Product product);
    List<StockMovement> findByWarehouseOrderByMovementDateDesc(Warehouse warehouse);
    Page<StockMovement> findAllByOrderByMovementDateDesc(Pageable pageable);
}
