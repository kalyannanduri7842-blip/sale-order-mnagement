package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.BillOfMaterialsDto;
import com.enterprisepro.erp.dto.BomItemDto;
import com.enterprisepro.erp.dto.WorkOrderDto;
import com.enterprisepro.erp.entity.*;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.*;
import com.enterprisepro.erp.service.ManufacturingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturingServiceImpl implements ManufacturingService {

    @Autowired
    private BillOfMaterialsRepository bomRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<BillOfMaterialsDto> getAllBoms(Pageable pageable) {
        return bomRepository.findAll(pageable).map(this::mapBomToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public BillOfMaterialsDto getBomById(Long id) {
        BillOfMaterials bom = bomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BillOfMaterials", "id", id));
        return mapBomToDto(bom);
    }

    @Override
    @Transactional
    public BillOfMaterialsDto createBom(BillOfMaterialsDto dto) {
        Product finished = productRepository.findById(dto.getFinishedProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", dto.getFinishedProductId()));

        BillOfMaterials bom = new BillOfMaterials();
        bom.setBomNumber(StringUtils.hasText(dto.getBomNumber()) ? dto.getBomNumber() : "BOM-" + (1000 + bomRepository.count() + 1));
        bom.setFinishedProduct(finished);
        bom.setBatchQuantity(dto.getBatchQuantity() > 0 ? dto.getBatchQuantity() : 1);
        bom.setVersion(dto.getVersion() != null ? dto.getVersion() : "v1.0");
        bom.setActive(true);

        BigDecimal totalCost = BigDecimal.ZERO;

        if (dto.getItems() != null) {
            for (BomItemDto itemDto : dto.getItems()) {
                Product raw = productRepository.findById(itemDto.getRawMaterialId())
                        .orElseThrow(() -> new ResourceNotFoundException("RawMaterial", "id", itemDto.getRawMaterialId()));

                BigDecimal unitCost = itemDto.getUnitCost() != null ? itemDto.getUnitCost() : raw.getCostPrice();
                BomItem item = new BomItem(raw, itemDto.getQuantityRequired(), unitCost);
                bom.addItem(item);

                totalCost = totalCost.add(item.getTotalCost());
            }
        }
        bom.setEstimatedTotalCost(totalCost);

        BillOfMaterials saved = bomRepository.save(bom);
        return mapBomToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkOrderDto> getAllWorkOrders(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return workOrderRepository.findByOrderNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(status, status, pageable)
                    .map(this::mapWorkOrderToDto);
        }
        return workOrderRepository.findAll(pageable).map(this::mapWorkOrderToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public WorkOrderDto getWorkOrderById(Long id) {
        WorkOrder wo = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkOrder", "id", id));
        return mapWorkOrderToDto(wo);
    }

    @Override
    @Transactional
    public WorkOrderDto createWorkOrder(WorkOrderDto dto) {
        BillOfMaterials bom = bomRepository.findById(dto.getBomId())
                .orElseThrow(() -> new ResourceNotFoundException("BillOfMaterials", "id", dto.getBomId()));

        Product product = bom.getFinishedProduct();

        WorkOrder wo = new WorkOrder();
        wo.setOrderNumber(StringUtils.hasText(dto.getOrderNumber()) ? dto.getOrderNumber() : "WO-" + (1000 + workOrderRepository.count() + 1));
        wo.setBillOfMaterials(bom);
        wo.setProduct(product);
        wo.setTargetQuantity(dto.getTargetQuantity());
        wo.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : LocalDate.now());
        wo.setDueDate(dto.getDueDate());
        wo.setStatus("IN_PROGRESS");
        wo.setPriority(dto.getPriority() != null ? dto.getPriority() : "MEDIUM");
        wo.setNotes(dto.getNotes());

        if (dto.getSupervisorId() != null) {
            Employee sup = employeeRepository.findById(dto.getSupervisorId()).orElse(null);
            wo.setSupervisor(sup);
        }

        if (dto.getTargetWarehouseId() != null) {
            Warehouse wh = warehouseRepository.findById(dto.getTargetWarehouseId()).orElse(null);
            wo.setTargetWarehouse(wh);
        }

        WorkOrder saved = workOrderRepository.save(wo);
        return mapWorkOrderToDto(saved);
    }

    @Override
    @Transactional
    public WorkOrderDto completeWorkOrder(Long id, int producedQty, int scrappedQty) {
        WorkOrder wo = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WorkOrder", "id", id));

        wo.setProducedQuantity(producedQty);
        wo.setScrappedQuantity(scrappedQty);
        wo.setStatus("COMPLETED");
        wo.setCompletionDate(LocalDate.now());

        // Increment stock of finished product
        Product finished = wo.getProduct();
        int before = finished.getCurrentStock();
        int after = before + producedQty;
        finished.setCurrentStock(after);
        productRepository.save(finished);

        Warehouse wh = wo.getTargetWarehouse();
        if (wh == null) wh = warehouseRepository.findAll().stream().findFirst().orElse(null);

        if (wh != null) {
            StockMovement sm = new StockMovement(finished, wh, "PURCHASE_IN", producedQty,
                    before, after, wo.getOrderNumber(), "Manufactured output from Work Order " + wo.getOrderNumber());
            stockMovementRepository.save(sm);
        }

        WorkOrder updated = workOrderRepository.save(wo);
        return mapWorkOrderToDto(updated);
    }

    private BillOfMaterialsDto mapBomToDto(BillOfMaterials bom) {
        BillOfMaterialsDto dto = new BillOfMaterialsDto();
        dto.setId(bom.getId());
        dto.setBomNumber(bom.getBomNumber());
        dto.setFinishedProductId(bom.getFinishedProduct().getId());
        dto.setFinishedProductName(bom.getFinishedProduct().getName());
        dto.setFinishedProductSku(bom.getFinishedProduct().getSku());
        dto.setBatchQuantity(bom.getBatchQuantity());
        dto.setEstimatedTotalCost(bom.getEstimatedTotalCost());
        dto.setVersion(bom.getVersion());
        dto.setActive(bom.isActive());

        if (bom.getItems() != null) {
            dto.setItems(bom.getItems().stream().map(item -> {
                BomItemDto iDto = new BomItemDto();
                iDto.setId(item.getId());
                iDto.setRawMaterialId(item.getRawMaterial().getId());
                iDto.setRawMaterialName(item.getRawMaterial().getName());
                iDto.setRawMaterialSku(item.getRawMaterial().getSku());
                iDto.setQuantityRequired(item.getQuantityRequired());
                iDto.setUnitCost(item.getUnitCost());
                iDto.setTotalCost(item.getTotalCost());
                return iDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    private WorkOrderDto mapWorkOrderToDto(WorkOrder wo) {
        WorkOrderDto dto = new WorkOrderDto();
        dto.setId(wo.getId());
        dto.setOrderNumber(wo.getOrderNumber());
        dto.setBomId(wo.getBillOfMaterials().getId());
        dto.setBomNumber(wo.getBillOfMaterials().getBomNumber());
        dto.setProductId(wo.getProduct().getId());
        dto.setProductName(wo.getProduct().getName());
        dto.setProductSku(wo.getProduct().getSku());
        dto.setTargetQuantity(wo.getTargetQuantity());
        dto.setProducedQuantity(wo.getProducedQuantity());
        dto.setScrappedQuantity(wo.getScrappedQuantity());
        dto.setStartDate(wo.getStartDate());
        dto.setDueDate(wo.getDueDate());
        dto.setCompletionDate(wo.getCompletionDate());
        dto.setStatus(wo.getStatus());
        dto.setPriority(wo.getPriority());
        if (wo.getSupervisor() != null) {
            dto.setSupervisorId(wo.getSupervisor().getId());
            dto.setSupervisorName(wo.getSupervisor().getFullName());
        }
        if (wo.getTargetWarehouse() != null) {
            dto.setTargetWarehouseId(wo.getTargetWarehouse().getId());
            dto.setTargetWarehouseName(wo.getTargetWarehouse().getName());
        }
        dto.setNotes(wo.getNotes());
        return dto;
    }
}
