package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.SalesInvoiceDto;
import com.enterprisepro.erp.dto.SalesOrderDto;
import com.enterprisepro.erp.dto.SalesOrderItemDto;
import com.enterprisepro.erp.entity.*;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.InsufficientStockException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.*;
import com.enterprisepro.erp.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SalesOrderDto> getAllSalesOrders(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return salesOrderRepository.findBySoNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(status, status, pageable)
                    .map(this::mapOrderToDto);
        }
        return salesOrderRepository.findAll(pageable).map(this::mapOrderToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SalesOrderDto getSalesOrderById(Long id) {
        SalesOrder so = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SalesOrder", "id", id));
        return mapOrderToDto(so);
    }

    @Override
    @Transactional
    public SalesOrderDto createSalesOrder(SalesOrderDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", dto.getCustomerId()));

        Warehouse warehouse = null;
        if (dto.getSourceWarehouseId() != null) {
            warehouse = warehouseRepository.findById(dto.getSourceWarehouseId()).orElse(null);
        }

        SalesOrder order = new SalesOrder();
        order.setSoNumber(StringUtils.hasText(dto.getSoNumber()) ? dto.getSoNumber() : "SO-" + (1000 + salesOrderRepository.count() + 1));
        order.setCustomer(customer);
        order.setSourceWarehouse(warehouse);
        order.setOrderDate(dto.getOrderDate() != null ? dto.getOrderDate() : LocalDate.now());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setShippingAddress(dto.getShippingAddress() != null ? dto.getShippingAddress() : customer.getAddress());
        order.setNotes(dto.getNotes());
        order.setStatus("CONFIRMED");
        order.setPaymentStatus("UNPAID");

        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal taxTotal = BigDecimal.ZERO;

        if (dto.getItems() != null) {
            for (SalesOrderItemDto itemDto : dto.getItems()) {
                Product product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", itemDto.getProductId()));

                if (product.getCurrentStock() < itemDto.getQuantity()) {
                    throw new InsufficientStockException(product.getName(), product.getCurrentStock(), itemDto.getQuantity());
                }

                SalesOrderItem item = new SalesOrderItem();
                item.setProduct(product);
                item.setQuantity(itemDto.getQuantity());
                item.setUnitPrice(itemDto.getUnitPrice() != null ? itemDto.getUnitPrice() : product.getSellingPrice());

                BigDecimal taxRate = itemDto.getTaxRate() != null ? itemDto.getTaxRate() : BigDecimal.valueOf(8.0);
                item.setTaxRate(taxRate);

                BigDecimal lineSub = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                BigDecimal lineTax = lineSub.multiply(taxRate).divide(BigDecimal.valueOf(100), 2, java.math.RoundingMode.HALF_UP);
                BigDecimal lineTotal = lineSub.add(lineTax);

                item.setDiscount(BigDecimal.ZERO);
                item.setLineTotal(lineTotal);

                order.addItem(item);

                subTotal = subTotal.add(lineSub);
                taxTotal = taxTotal.add(lineTax);
            }
        }

        BigDecimal discount = dto.getDiscountAmount() != null ? dto.getDiscountAmount() : BigDecimal.ZERO;
        order.setSubTotal(subTotal);
        order.setTaxAmount(taxTotal);
        order.setDiscountAmount(discount);
        order.setGrandTotal(subTotal.add(taxTotal).subtract(discount));

        SalesOrder saved = salesOrderRepository.save(order);
        return mapOrderToDto(saved);
    }

    @Override
    @Transactional
    public SalesOrderDto confirmSalesOrder(Long id) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SalesOrder", "id", id));
        order.setStatus("CONFIRMED");
        return mapOrderToDto(salesOrderRepository.save(order));
    }

    @Override
    @Transactional
    public SalesOrderDto deliverSalesOrder(Long id) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SalesOrder", "id", id));

        if ("DELIVERED".equals(order.getStatus())) {
            throw new BadRequestException("Order is already delivered.");
        }

        Warehouse warehouse = order.getSourceWarehouse();
        if (warehouse == null) {
            warehouse = warehouseRepository.findAll().stream().findFirst().orElse(null);
        }

        // Deduct inventory stock and record movements
        for (SalesOrderItem item : order.getItems()) {
            Product prod = item.getProduct();
            int before = prod.getCurrentStock();
            int after = Math.max(0, before - item.getQuantity());
            prod.setCurrentStock(after);
            productRepository.save(prod);

            if (warehouse != null) {
                StockMovement sm = new StockMovement(prod, warehouse, "SALES_OUT", item.getQuantity(),
                        before, after, order.getSoNumber(), "Shipped to client " + order.getCustomer().getName());
                stockMovementRepository.save(sm);
            }
        }

        order.setStatus("DELIVERED");
        Customer customer = order.getCustomer();
        customer.setTotalSpend(customer.getTotalSpend() + order.getGrandTotal().doubleValue());
        customerRepository.save(customer);

        SalesOrder updated = salesOrderRepository.save(order);
        return mapOrderToDto(updated);
    }

    @Override
    @Transactional
    public SalesInvoiceDto generateSalesInvoice(Long salesOrderId) {
        SalesOrder order = salesOrderRepository.findById(salesOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("SalesOrder", "id", salesOrderId));

        SalesInvoice invoice = new SalesInvoice();
        invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        invoice.setSalesOrder(order);
        invoice.setCustomer(order.getCustomer());
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setDueDate(LocalDate.now().plusDays(30));
        invoice.setTotalAmount(order.getGrandTotal());
        invoice.setPaidAmount(BigDecimal.ZERO);
        invoice.setStatus("ISSUED");
        invoice.setNotes(order.getNotes());

        SalesInvoice saved = salesInvoiceRepository.save(invoice);
        return mapInvoiceToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesInvoiceDto> getSalesInvoices(String status, Pageable pageable) {
        if (StringUtils.hasText(status)) {
            return salesInvoiceRepository.findByInvoiceNumberContainingIgnoreCaseOrStatusContainingIgnoreCase(status, status, pageable)
                    .map(this::mapInvoiceToDto);
        }
        return salesInvoiceRepository.findAll(pageable).map(this::mapInvoiceToDto);
    }

    private SalesOrderDto mapOrderToDto(SalesOrder order) {
        SalesOrderDto dto = new SalesOrderDto();
        dto.setId(order.getId());
        dto.setSoNumber(order.getSoNumber());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerName(order.getCustomer().getName());
        if (order.getSourceWarehouse() != null) {
            dto.setSourceWarehouseId(order.getSourceWarehouse().getId());
            dto.setSourceWarehouseName(order.getSourceWarehouse().getName());
        }
        dto.setOrderDate(order.getOrderDate());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setSubTotal(order.getSubTotal());
        dto.setTaxAmount(order.getTaxAmount());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setGrandTotal(order.getGrandTotal());
        dto.setStatus(order.getStatus());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setNotes(order.getNotes());
        if (order.getSalesRepresentative() != null) {
            dto.setSalesRepName(order.getSalesRepresentative().getFullName());
        }

        if (order.getItems() != null) {
            dto.setItems(order.getItems().stream().map(item -> {
                SalesOrderItemDto itemDto = new SalesOrderItemDto();
                itemDto.setId(item.getId());
                itemDto.setProductId(item.getProduct().getId());
                itemDto.setProductName(item.getProduct().getName());
                itemDto.setProductSku(item.getProduct().getSku());
                itemDto.setQuantity(item.getQuantity());
                itemDto.setUnitPrice(item.getUnitPrice());
                itemDto.setTaxRate(item.getTaxRate());
                itemDto.setDiscount(item.getDiscount());
                itemDto.setLineTotal(item.getLineTotal());
                return itemDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }

    private SalesInvoiceDto mapInvoiceToDto(SalesInvoice invoice) {
        SalesInvoiceDto dto = new SalesInvoiceDto();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        if (invoice.getSalesOrder() != null) {
            dto.setSalesOrderId(invoice.getSalesOrder().getId());
            dto.setSalesOrderNumber(invoice.getSalesOrder().getSoNumber());
        }
        dto.setCustomerId(invoice.getCustomer().getId());
        dto.setCustomerName(invoice.getCustomer().getName());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setPaidAmount(invoice.getPaidAmount());
        dto.setStatus(invoice.getStatus());
        dto.setNotes(invoice.getNotes());
        return dto;
    }
}
