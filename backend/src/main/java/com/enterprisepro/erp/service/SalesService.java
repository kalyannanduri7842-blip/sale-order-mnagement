package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.SalesInvoiceDto;
import com.enterprisepro.erp.dto.SalesOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesService {
    Page<SalesOrderDto> getAllSalesOrders(String status, Pageable pageable);
    SalesOrderDto getSalesOrderById(Long id);
    SalesOrderDto createSalesOrder(SalesOrderDto salesOrderDto);
    SalesOrderDto confirmSalesOrder(Long id);
    SalesOrderDto deliverSalesOrder(Long id);
    SalesInvoiceDto generateSalesInvoice(Long salesOrderId);
    Page<SalesInvoiceDto> getSalesInvoices(String status, Pageable pageable);
}
