package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findByShortCode(String shortCode);
    List<Unit> findByActiveTrue();
}
