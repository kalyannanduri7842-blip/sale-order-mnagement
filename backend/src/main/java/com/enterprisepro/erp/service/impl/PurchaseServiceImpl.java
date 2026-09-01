package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.PurchaseOrderDto;
import com.enterprisepro.erp.dto.PurchaseOrderItemDto;
import com.enterprisepro.erp.entity.*;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.*;
import com.enterprisepro.erp.service.PurchaseService;
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
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PurchaseOrderDto> getAllPurchaseOrders(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return purchaseOrderRepository.findByPoNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(status, status, pageable)
                    .map(this::mapToDto);
        }
        return purchaseOrderRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDto getPurchaseOrderById(Long id) {
        PurchaseOrder po = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder", "id", id));
        return mapToDto(po);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDto getPurchaseOrderByNumber(String poNumber) {
        PurchaseOrder po = purchaseOrderRepository.findByPoNumber(poNumber)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder", "poNumber", poNumber));
        return mapToDto(po);
    }

    @Override
    @Transactional
    public PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto dto) {
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor", "id", dto.getVendorId()));

        Warehouse warehouse = null;
        if (dto.getDestinationWarehouseId() != null) {
            warehouse = warehouseRepository.findById(dto.getDestinationWarehouseId()).orElse(null);
        }

        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber(StringUtils.hasText(dto.getPoNumber()) ? dto.getPoNumber() : "PO-" + (1000 + purchaseOrderRepository.count() + 1));
        po.setVendor(vendor);
        po.setDestinationWarehouse(warehouse);
        po.setOrderDate(dto.getOrderDate() != null ? dto.getOrderDate() : LocalDate.now());
        po.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        po.setStatus("ISSUED");
        po.setPaymentStatus("UNPAID");
        po.setNotes(dto.getNotes());

        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;

        if (dto.getItems() != null) {
            for (PurchaseOrderItemDto itemDto : dto.getItems()) {
                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", itemDto.getProductId()));

                PurchaseOrderItem item = new PurchaseOrderItem();
                item.setProduct(product);
                item.setQuantityOrdered(itemDto.getQuantityOrdered());
                item.setQuantityReceived(0);
                item.setUnitPrice(itemDto.getUnitPrice() != null ? itemDto.getUnitPrice() : product.getCostPrice());

                BigDecimal taxRate = itemDto.getTaxRate() != null ? itemDto.getTaxRate() : BigDecimal.valueOf(5.0);
                item.setTaxRate(taxRate);

                BigDecimal lineSub = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantityOrdered()));
                BigDecimal lineTax = lineSub.multiply(taxRate).divide(BigDecimal.valueOf(100), 2, java.math.RoundingMode.HALF_UP);
                BigDecimal lineTotal = lineSub.add(lineTax);

                item.setSubTotal(lineSub);
                item.setLineTotal(lineTotal);

                po.addItem(item);

                subTotal = subTotal.add(lineSub);
                taxTotal = taxTotal.add(lineTax);
            }
        }

        BigDecimal discount = dto.getDiscountAmount() != null ? dto.getDiscountAmount() : BigDecimal.ZERO;
        po.setSubTotal(subTotal);
        po.setTaxAmount(taxTotal);
        po.setDiscountAmount(discount);
        po.setGrandTotal(subTotal.add(taxTotal).subtract(discount));

        PurchaseOrder saved = purchaseOrderRepository.save(po);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public PurchaseOrderDto receiveGoods(Long poId) {
        PurchaseOrder po = purchaseOrderRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder", "id", poId));

        if ("RECEIVED".equals(po.getStatus())) {
            throw new BadRequestException("Purchase order is already marked as RECEIVED");
        }

        Warehouse warehouse = po.getDestinationWarehouse();
        if (warehouse == null) {
            warehouse = warehouseRepository.findAll().stream().findFirst().orElse(null);
        }

        // Increase product stock & log movements
        for (PurchaseOrderItem item : po.getItems()) {
            item.setQuantityReceived(item.getQuantityOrdered());
            Product prod = item.getProduct();
            int before = prod.getCurrentStock();
            int after = before + item.getQuantityOrdered();
            prod.setCurrentStock(after);
            productRepository.save(prod);

            if (warehouse != null) {
                StockMovement sm = new StockMovement(prod, warehouse, "PURCHASE_IN", item.getQuantityOrdered(),
                        before, after, po.getPoNumber(), "Goods received from " + po.getVendor().getCompanyName());
                stockMovementRepository.save(sm);
            }
        }

        po.setStatus("RECEIVED");
        PurchaseOrder updated = purchaseOrderRepository.save(po);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public PurchaseOrderDto cancelPurchaseOrder(Long poId) {
        PurchaseOrder po = purchaseOrderRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder", "id", poId));

        if ("RECEIVED".equals(po.getStatus())) {
            throw new BadRequestException("Cannot cancel an order that has already been received.");
        }

        po.setStatus("CANCELLED");
        return mapToDto(purchaseOrderRepository.save(po));
    }

    private PurchaseOrderDto mapToDto(PurchaseOrder po) {
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setId(po.getId());
        dto.setPoNumber(po.getPoNumber());
        dto.setVendorId(po.getVendor().getId());
        dto.setVendorName(po.getVendor().getCompanyName());
        if (po.getDestinationWarehouse() != null) {
            dto.setDestinationWarehouseId(po.getDestinationWarehouse().getId());
            dto.setDestinationWarehouseName(po.getDestinationWarehouse().getName());
        }
        dto.setOrderDate(po.getOrderDate());
        dto.setExpectedDeliveryDate(po.getExpectedDeliveryDate());
        dto.setSubTotal(po.getSubTotal());
        dto.setTaxAmount(po.getTaxAmount());
        dto.setDiscountAmount(po.getDiscountAmount());
        dto.setGrandTotal(po.getGrandTotal());
        dto.setStatus(po.getStatus());
        dto.setPaymentStatus(po.getPaymentStatus());
        dto.setNotes(po.getNotes());
        if (po.getCreatedBy() != null) {
            dto.setCreatedByUsername(po.getCreatedBy().getUsername());
        }

        if (po.getItems() != null) {
            dto.setItems(po.getItems().stream().map(item -> {
                PurchaseOrderItemDto itemDto = new PurchaseOrderItemDto();
                itemDto.setId(item.getId());
                itemDto.setProductId(item.getProduct().getId());
                itemDto.setProductName(item.getProduct().getName());
                itemDto.setProductSku(item.getProduct().getSku());
                itemDto.setQuantityOrdered(item.getQuantityOrdered());
                itemDto.setQuantityReceived(item.getQuantityReceived());
                itemDto.setUnitPrice(item.getUnitPrice());
                itemDto.setTaxRate(item.getTaxRate());
                itemDto.setSubTotal(item.getSubTotal());
                itemDto.setLineTotal(item.getLineTotal());
                return itemDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
