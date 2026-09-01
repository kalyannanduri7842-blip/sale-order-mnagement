package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.entity.SalesQuotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesQuotationRepository extends JpaRepository<SalesQuotation, Long> {
    Optional<SalesQuotation> findByQuotationNumber(String quotationNumber);
    List<SalesQuotation> findByCustomer(Customer customer);
    Page<SalesQuotation> findByQuotationNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String quotationNumber, String status, Pageable pageable);
}
