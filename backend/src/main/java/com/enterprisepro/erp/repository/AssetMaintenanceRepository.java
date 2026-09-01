package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Asset;
import com.enterprisepro.erp.entity.AssetMaintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetMaintenanceRepository extends JpaRepository<AssetMaintenance, Long> {
    List<AssetMaintenance> findByAssetOrderByServiceDateDesc(Asset asset);
    Page<AssetMaintenance> findAllByOrderByServiceDateDesc(Pageable pageable);
}
