package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentNumber(String paymentNumber);
    Page<Payment> findByPaymentNumberContainingIgnoreCaseOrPaymentTypeContainingIgnoreCase(
            String paymentNumber, String paymentType, Pageable pageable);
}
