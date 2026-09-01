package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.BillOfMaterials;
import com.enterprisepro.erp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillOfMaterialsRepository extends JpaRepository<BillOfMaterials, Long> {
    Optional<BillOfMaterials> findByBomNumber(String bomNumber);
    Optional<BillOfMaterials> findByFinishedProduct(Product finishedProduct);
    Page<BillOfMaterials> findByBomNumberContainingIgnoreCase(String bomNumber, Pageable pageable);
}
