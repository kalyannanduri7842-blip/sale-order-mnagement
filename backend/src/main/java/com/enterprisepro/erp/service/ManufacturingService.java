package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.BillOfMaterialsDto;
import com.enterprisepro.erp.dto.WorkOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManufacturingService {
    Page<BillOfMaterialsDto> getAllBoms(Pageable pageable);
    BillOfMaterialsDto getBomById(Long id);
    BillOfMaterialsDto createBom(BillOfMaterialsDto bomDto);

    Page<WorkOrderDto> getAllWorkOrders(String status, Pageable pageable);
    WorkOrderDto getWorkOrderById(Long id);
    WorkOrderDto createWorkOrder(WorkOrderDto workOrderDto);
    WorkOrderDto completeWorkOrder(Long id, int producedQty, int scrappedQty);
}
