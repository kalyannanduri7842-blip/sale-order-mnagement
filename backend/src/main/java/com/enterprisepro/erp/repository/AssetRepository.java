package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findByAssetCode(String assetCode);
    List<Asset> findByCategory(String category);
    List<Asset> findByStatus(String status);
    
    @Query("SELECT SUM(a.currentValuation) FROM Asset a WHERE a.status != 'DISPOSED'")
    BigDecimal sumTotalAssetValuation();

    Page<Asset> findByNameContainingIgnoreCaseOrAssetCodeContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name, String assetCode, String category, Pageable pageable);
}
