package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.PurchaseReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturn, Long> {
    Optional<PurchaseReturn> findByReturnNumber(String returnNumber);
    Page<PurchaseReturn> findAllByOrderByReturnDateDesc(Pageable pageable);
}
