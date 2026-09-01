package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.StockAdjustmentDto;
import com.enterprisepro.erp.dto.StockMovementDto;
import com.enterprisepro.erp.dto.StockTransferDto;
import com.enterprisepro.erp.entity.*;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.InsufficientStockException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.*;
import com.enterprisepro.erp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private StockTransferRepository stockTransferRepository;

    @Autowired
    private StockAdjustmentRepository stockAdjustmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<StockMovementDto> getStockMovements(Pageable pageable) {
        return stockMovementRepository.findAllByOrderByMovementDateDesc(pageable).map(this::mapMovementToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockMovementDto> getProductMovementHistory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        return stockMovementRepository.findByProductOrderByMovementDateDesc(product).stream()
                .map(this::mapMovementToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StockTransferDto transferStock(StockTransferDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", dto.getProductId()));

        Warehouse source = warehouseRepository.findById(dto.getSourceWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", dto.getSourceWarehouseId()));

        Warehouse dest = warehouseRepository.findById(dto.getDestinationWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", dto.getDestinationWarehouseId()));

        if (source.getId().equals(dest.getId())) {
            throw new BadRequestException("Source and destination warehouses cannot be the same!");
        }

        if (product.getCurrentStock() < dto.getQuantity()) {
            throw new InsufficientStockException(product.getName(), product.getCurrentStock(), dto.getQuantity());
        }

        StockTransfer transfer = new StockTransfer();
        transfer.setTransferNumber("TRF-" + System.currentTimeMillis());
        transfer.setProduct(product);
        transfer.setSourceWarehouse(source);
        transfer.setDestinationWarehouse(dest);
        transfer.setQuantity(dto.getQuantity());
        transfer.setStatus("COMPLETED");
        transfer.setNotes(dto.getNotes());
        transfer.setCompletedDate(LocalDateTime.now());

        StockTransfer saved = stockTransferRepository.save(transfer);

        // Record stock movements
        StockMovement outMovement = new StockMovement(product, source, "TRANSFER_OUT", dto.getQuantity(),
                product.getCurrentStock(), product.getCurrentStock(), saved.getTransferNumber(), "Transferred to " + dest.getName());
        StockMovement inMovement = new StockMovement(product, dest, "TRANSFER_IN", dto.getQuantity(),
                product.getCurrentStock(), product.getCurrentStock(), saved.getTransferNumber(), "Transferred from " + source.getName());

        stockMovementRepository.save(outMovement);
        stockMovementRepository.save(inMovement);

        StockTransferDto res = new StockTransferDto();
        res.setId(saved.getId());
        res.setTransferNumber(saved.getTransferNumber());
        res.setProductId(product.getId());
        res.setProductName(product.getName());
        res.setSourceWarehouseId(source.getId());
        res.setSourceWarehouseName(source.getName());
        res.setDestinationWarehouseId(dest.getId());
        res.setDestinationWarehouseName(dest.getName());
        res.setQuantity(saved.getQuantity());
        res.setStatus(saved.getStatus());
        res.setTransferDate(saved.getTransferDate());
        return res;
    }

    @Override
    @Transactional
    public StockAdjustmentDto adjustStock(StockAdjustmentDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", dto.getProductId()));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", dto.getWarehouseId()));

        int stockBefore = product.getCurrentStock();
        int stockAfter;

        if ("ADDITION".equalsIgnoreCase(dto.getAdjustmentType())) {
            stockAfter = stockBefore + dto.getQuantity();
        } else if ("SUBTRACTION".equalsIgnoreCase(dto.getAdjustmentType())) {
            if (stockBefore < dto.getQuantity()) {
                throw new InsufficientStockException(product.getName(), stockBefore, dto.getQuantity());
            }
            stockAfter = stockBefore - dto.getQuantity();
        } else {
            throw new BadRequestException("Invalid adjustment type: " + dto.getAdjustmentType() + ". Must be ADDITION or SUBTRACTION.");
        }

        product.setCurrentStock(stockAfter);
        productRepository.save(product);

        StockAdjustment adjustment = new StockAdjustment();
        adjustment.setAdjustmentNumber("ADJ-" + System.currentTimeMillis());
        adjustment.setProduct(product);
        adjustment.setWarehouse(warehouse);
        adjustment.setAdjustmentType(dto.getAdjustmentType().toUpperCase());
        adjustment.setQuantity(dto.getQuantity());
        adjustment.setStockBefore(stockBefore);
        adjustment.setStockAfter(stockAfter);
        adjustment.setReason(dto.getReason());
        adjustment.setNotes(dto.getNotes());

        StockAdjustment saved = stockAdjustmentRepository.save(adjustment);

        // Record stock movement
        String moveType = "ADDITION".equalsIgnoreCase(dto.getAdjustmentType()) ? "ADJUSTMENT_ADD" : "ADJUSTMENT_SUBTRACT";
        StockMovement movement = new StockMovement(product, warehouse, moveType, dto.getQuantity(),
                stockBefore, stockAfter, saved.getAdjustmentNumber(), dto.getReason());
        stockMovementRepository.save(movement);

        StockAdjustmentDto res = new StockAdjustmentDto();
        res.setId(saved.getId());
        res.setAdjustmentNumber(saved.getAdjustmentNumber());
        res.setProductId(product.getId());
        res.setProductName(product.getName());
        res.setWarehouseId(warehouse.getId());
        res.setWarehouseName(warehouse.getName());
        res.setAdjustmentType(saved.getAdjustmentType());
        res.setQuantity(saved.getQuantity());
        res.setStockBefore(stockBefore);
        res.setStockAfter(stockAfter);
        res.setReason(saved.getReason());
        res.setAdjustmentDate(saved.getAdjustmentDate());
        return res;
    }

    private StockMovementDto mapMovementToDto(StockMovement movement) {
        StockMovementDto dto = new StockMovementDto();
        dto.setId(movement.getId());
        dto.setProductId(movement.getProduct().getId());
        dto.setProductName(movement.getProduct().getName());
        dto.setProductSku(movement.getProduct().getSku());
        dto.setWarehouseId(movement.getWarehouse().getId());
        dto.setWarehouseName(movement.getWarehouse().getName());
        dto.setMovementType(movement.getMovementType());
        dto.setQuantity(movement.getQuantity());
        dto.setStockBefore(movement.getStockBefore());
        dto.setStockAfter(movement.getStockAfter());
        dto.setReferenceNumber(movement.getReferenceNumber());
        dto.setNotes(movement.getNotes());
        if (movement.getPerformedBy() != null) {
            dto.setPerformedByUsername(movement.getPerformedBy().getUsername());
        }
        dto.setMovementDate(movement.getMovementDate());
        return dto;
    }
}
