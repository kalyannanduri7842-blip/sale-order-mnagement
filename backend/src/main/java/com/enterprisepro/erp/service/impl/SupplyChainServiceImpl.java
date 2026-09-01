package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.ShipmentDto;
import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.entity.SalesOrder;
import com.enterprisepro.erp.entity.Shipment;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.CustomerRepository;
import com.enterprisepro.erp.repository.SalesOrderRepository;
import com.enterprisepro.erp.repository.ShipmentRepository;
import com.enterprisepro.erp.service.SupplyChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Service
public class SupplyChainServiceImpl implements SupplyChainService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ShipmentDto> getAllShipments(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return shipmentRepository.findByShipmentNumberContainingIgnoreCaseOrTrackingNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(
                    status, status, status, pageable).map(this::mapToDto);
        }
        return shipmentRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ShipmentDto getShipmentById(Long id) {
        Shipment s = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment", "id", id));
        return mapToDto(s);
    }

    @Override
    @Transactional
    public ShipmentDto createShipment(ShipmentDto dto) {
        Shipment s = new Shipment();
        s.setShipmentNumber(StringUtils.hasText(dto.getShipmentNumber()) ? dto.getShipmentNumber() : "SHP-" + (1000 + shipmentRepository.count() + 1));
        s.setTrackingNumber(StringUtils.hasText(dto.getTrackingNumber()) ? dto.getTrackingNumber() : "TRK" + System.currentTimeMillis());
        s.setCarrierName(dto.getCarrierName() != null ? dto.getCarrierName() : "FedEx Logistics");
        s.setShipmentDate(dto.getShipmentDate() != null ? dto.getShipmentDate() : LocalDate.now());
        s.setEstimatedDeliveryDate(dto.getEstimatedDeliveryDate() != null ? dto.getEstimatedDeliveryDate() : LocalDate.now().plusDays(4));
        s.setStatus("IN_TRANSIT");
        s.setDestinationAddress(dto.getDestinationAddress());
        s.setNotes(dto.getNotes());

        if (dto.getSalesOrderId() != null) {
            SalesOrder so = salesOrderRepository.findById(dto.getSalesOrderId()).orElse(null);
            s.setSalesOrder(so);
            if (so != null && so.getCustomer() != null) {
                s.setCustomer(so.getCustomer());
                if (!StringUtils.hasText(s.getDestinationAddress())) {
                    s.setDestinationAddress(so.getShippingAddress());
                }
            }
        } else if (dto.getCustomerId() != null) {
            Customer cust = customerRepository.findById(dto.getCustomerId()).orElse(null);
            s.setCustomer(cust);
        }

        Shipment saved = shipmentRepository.save(s);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public ShipmentDto updateShipmentStatus(Long id, String status) {
        Shipment s = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment", "id", id));
        s.setStatus(status.toUpperCase());
        if ("DELIVERED".equalsIgnoreCase(status)) {
            s.setActualDeliveryDate(LocalDate.now());
        }
        Shipment updated = shipmentRepository.save(s);
        return mapToDto(updated);
    }

    private ShipmentDto mapToDto(Shipment s) {
        ShipmentDto dto = new ShipmentDto();
        dto.setId(s.getId());
        dto.setShipmentNumber(s.getShipmentNumber());
        dto.setTrackingNumber(s.getTrackingNumber());
        if (s.getSalesOrder() != null) {
            dto.setSalesOrderId(s.getSalesOrder().getId());
            dto.setSalesOrderNumber(s.getSalesOrder().getSoNumber());
        }
        if (s.getCustomer() != null) {
            dto.setCustomerId(s.getCustomer().getId());
            dto.setCustomerName(s.getCustomer().getName());
        }
        dto.setCarrierName(s.getCarrierName());
        dto.setShipmentDate(s.getShipmentDate());
        dto.setEstimatedDeliveryDate(s.getEstimatedDeliveryDate());
        dto.setActualDeliveryDate(s.getActualDeliveryDate());
        dto.setStatus(s.getStatus());
        dto.setDestinationAddress(s.getDestinationAddress());
        dto.setNotes(s.getNotes());
        return dto;
    }
}
