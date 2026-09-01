package com.enterprisepro.erp.repository;

import com.enterprisepro.erp.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByShipmentNumber(String shipmentNumber);
    Optional<Shipment> findByTrackingNumber(String trackingNumber);
    List<Shipment> findByStatus(String status);
    Page<Shipment> findByShipmentNumberContainingIgnoreCaseOrTrackingNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String shipmentNumber, String trackingNumber, String status, Pageable pageable);
}
