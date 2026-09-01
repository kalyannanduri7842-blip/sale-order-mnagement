package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.ShipmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplyChainService {
    Page<ShipmentDto> getAllShipments(String status, Pageable pageable);
    ShipmentDto getShipmentById(Long id);
    ShipmentDto createShipment(ShipmentDto shipmentDto);
    ShipmentDto updateShipmentStatus(Long id, String status);
}
