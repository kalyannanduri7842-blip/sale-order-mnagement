package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.PurchaseOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    Page<PurchaseOrderDto> getAllPurchaseOrders(String status, Pageable pageable);
    PurchaseOrderDto getPurchaseOrderById(Long id);
    PurchaseOrderDto getPurchaseOrderByNumber(String poNumber);
    PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto poDto);
    PurchaseOrderDto receiveGoods(Long poId);
    PurchaseOrderDto cancelPurchaseOrder(Long poId);
}
