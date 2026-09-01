package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Category;
import com.enterprisepro.erp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);
    Optional<Product> findBySku(String sku);
    List<Product> findByCategory(Category category);
    
    @Query("SELECT p FROM Product p WHERE p.currentStock <= p.minStockAlert AND p.active = true")
    List<Product> findLowStockProducts();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.currentStock <= p.minStockAlert AND p.active = true")
    long countLowStockProducts();

    @Query("SELECT SUM(p.currentStock * p.costPrice) FROM Product p WHERE p.active = true")
    Double calculateTotalInventoryValuation();

    Page<Product> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
            String name, String sku, String productCode, Pageable pageable);
}
