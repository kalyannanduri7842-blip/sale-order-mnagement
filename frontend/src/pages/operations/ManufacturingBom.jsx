import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useNotification } from '../../context/NotificationContext';
import { Cpu, Plus, Play, Wrench, Layers, DollarSign } from 'lucide-react';

export function ManufacturingBom() {
  const [boms, setBoms] = useState([
    {
      id: 1,
      bomNumber: 'BOM-1001',
      finishedProductName: 'Enterprise Cloud Server Pro X1',
      finishedProductSku: 'SRV-X1-48C',
      batchQuantity: 1,
      estimatedTotalCost: 3200.0,
      version: 'v2.1',
      active: true,
      items: [
        { rawMaterialName: 'Heavy-Duty Aluminum 2U Chassis', quantityRequired: 1, unitCost: 120.0, totalCost: 120.0 },
        { rawMaterialName: 'Intel Xeon Platinum 48-Core Processor', quantityRequired: 2, unitCost: 1100.0, totalCost: 2200.0 },
        { rawMaterialName: '128GB DDR5 ECC Registered Memory Kit', quantityRequired: 4, unitCost: 220.0, totalCost: 880.0 }
      ]
    }
  ]);

  const [workOrders, setWorkOrders] = useState([
    {
      id: 1,
      orderNumber: 'WO-1001',
      bomNumber: 'BOM-1001',
      productName: 'Enterprise Cloud Server Pro X1',
      targetQuantity: 10,
      producedQuantity: 10,
      scrappedQuantity: 0,
      startDate: '2026-08-25',
      dueDate: '2026-08-30',
      status: 'COMPLETED',
      priority: 'HIGH'
    },
    {
      id: 2,
      orderNumber: 'WO-1002',
      bomNumber: 'BOM-1001',
      productName: 'Enterprise Cloud Server Pro X1',
      targetQuantity: 15,
      producedQuantity: 6,
      scrappedQuantity: 0,
      startDate: '2026-08-30',
      dueDate: '2026-09-06',
      status: 'IN_PROGRESS',
      priority: 'HIGH'
    }
  ]);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedBom, setSelectedBom] = useState(null);
  const { addToast } = useNotification();

  const handleLaunchWorkOrder = (bom) => {
    const newWo = {
      id: Date.now(),
      orderNumber: 'WO-' + (1000 + workOrders.length + 1),
      bomNumber: bom.bomNumber,
      productName: bom.finishedProductName,
      targetQuantity: 20,
      producedQuantity: 0,
      scrappedQuantity: 0,
      startDate: new Date().toISOString().split('T')[0],
      dueDate: new Date(Date.now() + 7 * 86400000).toISOString().split('T')[0],
      status: 'IN_PROGRESS',
      priority: 'HIGH'
    };
    setWorkOrders([newWo, ...workOrders]);
    addToast('Work Order Launched', `Assembly floor scheduled for ${newWo.orderNumber}`, 'success');
  };

  const handleCompleteWorkOrder = (id) => {
    setWorkOrders(workOrders.map((w) => (w.id === id ? { ...w, status: 'COMPLETED', producedQuantity: w.targetQuantity } : w)));
    addToast('Work Order Finished', 'Assembly output yielded into warehouse inventory.', 'success');
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const bomColumns = [
    {
      header: 'BOM Code',
      accessor: 'bomNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Finished Product Output',
      accessor: 'finishedProductName',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] font-mono text-slate-400">SKU: {row.finishedProductSku} • {row.version}</span>
        </div>
      )
    },
    {
      header: 'Batch Unit Yield',
      accessor: 'batchQuantity',
      render: (val) => <span className="font-mono text-slate-300">{val} Unit(s)</span>
    },
    {
      header: 'Est. Unit BOM Cost',
      accessor: 'estimatedTotalCost',
      render: (val) => <span className="font-mono font-bold text-emerald-400">{formatCurrency(val)}</span>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (_, row) => (
        <div className="flex items-center space-x-2">
          <button
            onClick={() => handleLaunchWorkOrder(row)}
            className="px-3 py-1 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white rounded-lg text-xs font-semibold flex items-center shadow transition-all"
          >
            <Play className="w-3.5 h-3.5 mr-1" /> Launch Production
          </button>
        </div>
      )
    }
  ];

  const woColumns = [
    {
      header: 'Work Order #',
      accessor: 'orderNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Target Product',
      accessor: 'productName',
      render: (val) => <span className="font-semibold text-white">{val}</span>
    },
    {
      header: 'Yield Output (Produced / Target)',
      accessor: 'producedQuantity',
      render: (val, row) => (
        <span className="font-mono font-bold text-slate-200">
          {val} / {row.targetQuantity} units
        </span>
      )
    },
    {
      header: 'Schedule',
      accessor: 'startDate',
      render: (val, row) => (
        <span className="text-[11px] font-mono text-slate-400">
          {val} ➔ {row.dueDate}
        </span>
      )
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'COMPLETED' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Action',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div>
          {row.status !== 'COMPLETED' && (
            <button
              onClick={() => handleCompleteWorkOrder(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold shadow transition-colors"
            >
              Yield Output
            </button>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Manufacturing & Bill of Materials (BOM)"
        description="Engineering recipes, raw component bills of material, shop floor production schedules and assembly work orders"
      />

      <DataTable
        title="Engineering Bills of Material (BOM Recipes)"
        columns={bomColumns}
        data={boms}
      />

      <DataTable
        title="Production Work Orders & Assembly Runs"
        columns={woColumns}
        data={workOrders}
      />
    </div>
  );
}
