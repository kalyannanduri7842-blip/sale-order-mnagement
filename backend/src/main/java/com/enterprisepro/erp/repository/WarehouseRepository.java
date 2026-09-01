package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findByCode(String code);
    List<Warehouse> findByActiveTrue();
    Page<Warehouse> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseOrCityContainingIgnoreCase(
            String name, String code, String city, Pageable pageable);
}
