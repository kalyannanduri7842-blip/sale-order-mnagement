import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { ArrowLeftRight, SlidersHorizontal, Plus, ArrowUpRight, ArrowDownLeft } from 'lucide-react';

export function StockMovements() {
  const [movements, setMovements] = useState([
    { id: 1, productName: 'Enterprise Cloud Server Pro X1', productSku: 'SRV-X1-48C', warehouseName: 'Austin Main Central Logistics Hub', movementType: 'TRANSFER_IN', quantity: 15, stockBefore: 30, stockAfter: 45, referenceNumber: 'TRF-99102', movementDate: '2026-08-30T11:20:00', notes: 'Restocked from Reno Hub' },
    { id: 2, productName: 'Ultra Gigabit Managed Switch 48P', productSku: 'NET-48P-SW', warehouseName: 'Austin Main Central Logistics Hub', movementType: 'SALES_OUT', quantity: 10, stockBefore: 90, stockAfter: 80, referenceNumber: 'SO-1001', movementDate: '2026-08-29T14:40:00', notes: 'Dispatched to Acme Global Corp' },
    { id: 3, productName: 'Heavy-Duty Aluminum 2U Chassis', productSku: 'RAW-2U-CHAS', warehouseName: 'Reno West Coast Fulfillment Center', movementType: 'ADJUSTMENT_SUBTRACT', quantity: 2, stockBefore: 10, stockAfter: 8, referenceNumber: 'ADJ-1002', movementDate: '2026-08-28T09:15:00', notes: 'Scrapped damaged test parts' }
  ]);
  const [products, setProducts] = useState([]);
  const [warehouses, setWarehouses] = useState([]);
  const [isTransferModalOpen, setIsTransferModalOpen] = useState(false);
  const [isAdjustModalOpen, setIsAdjustModalOpen] = useState(false);

  const [transferData, setTransferData] = useState({
    productId: 1,
    sourceWarehouseId: 1,
    destinationWarehouseId: 2,
    quantity: 5,
    notes: ''
  });

  const [adjustData, setAdjustData] = useState({
    productId: 1,
    warehouseId: 1,
    adjustmentType: 'ADDITION',
    quantity: 10,
    reason: 'Physical cycle count adjustment'
  });

  const { addToast } = useNotification();

  useEffect(() => {
    loadReferences();
  }, []);

  const loadReferences = async () => {
    const pData = await api.get('/products', 'products');
    setProducts(Array.isArray(pData) ? pData : pData?.content || []);
    const wData = await api.get('/warehouses', 'warehouses');
    setWarehouses(Array.isArray(wData) ? wData : wData?.content || []);
  };

  const handleTransfer = async (e) => {
    e.preventDefault();
    const prod = products.find((p) => p.id === Number(transferData.productId));
    const srcWh = warehouses.find((w) => w.id === Number(transferData.sourceWarehouseId));
    const destWh = warehouses.find((w) => w.id === Number(transferData.destinationWarehouseId));

    const newMove = {
      id: Date.now(),
      productName: prod ? prod.name : 'Product',
      productSku: prod ? prod.sku : 'SKU',
      warehouseName: destWh ? destWh.name : 'Warehouse',
      movementType: 'TRANSFER_IN',
      quantity: transferData.quantity,
      stockBefore: prod ? prod.currentStock : 50,
      stockAfter: prod ? prod.currentStock : 50,
      referenceNumber: 'TRF-' + Date.now().toString().slice(-5),
      movementDate: new Date().toISOString(),
      notes: `Transferred from ${srcWh?.name || 'Hub'}`
    };

    setMovements([newMove, ...movements]);
    addToast('Transfer Completed', `Transferred ${transferData.quantity} units to ${destWh?.name || 'destination'}.`, 'success');
    setIsTransferModalOpen(false);
  };

  const handleAdjust = async (e) => {
    e.preventDefault();
    const prod = products.find((p) => p.id === Number(adjustData.productId));
    const wh = warehouses.find((w) => w.id === Number(adjustData.warehouseId));
    const before = prod ? prod.currentStock : 50;
    const qty = Number(adjustData.quantity);
    const after = adjustData.adjustmentType === 'ADDITION' ? before + qty : before - qty;

    const newMove = {
      id: Date.now(),
      productName: prod ? prod.name : 'Product',
      productSku: prod ? prod.sku : 'SKU',
      warehouseName: wh ? wh.name : 'Warehouse',
      movementType: adjustData.adjustmentType === 'ADDITION' ? 'ADJUSTMENT_ADD' : 'ADJUSTMENT_SUBTRACT',
      quantity: qty,
      stockBefore: before,
      stockAfter: after,
      referenceNumber: 'ADJ-' + Date.now().toString().slice(-5),
      movementDate: new Date().toISOString(),
      notes: adjustData.reason
    };

    setMovements([newMove, ...movements]);
    addToast('Stock Count Adjusted', `Updated ledger balance to ${after} units.`, 'success');
    setIsAdjustModalOpen(false);
  };

  const columns = [
    {
      header: 'Timestamp',
      accessor: 'movementDate',
      render: (val) => <span className="font-mono text-slate-400 text-xs">{new Date(val).toLocaleString()}</span>
    },
    {
      header: 'Product / SKU',
      accessor: 'productName',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <span className="text-[10px] font-mono text-blue-400">{row.productSku}</span>
        </div>
      )
    },
    {
      header: 'Movement Type',
      accessor: 'movementType',
      render: (val) => {
        const isPositive = val.includes('IN') || val.includes('ADD') || val.includes('PURCHASE');
        return (
          <Badge variant={isPositive ? 'success' : 'warning'}>
            {val}
          </Badge>
        );
      }
    },
    {
      header: 'Quantity',
      accessor: 'quantity',
      render: (val, row) => {
        const isPositive = row.movementType.includes('IN') || row.movementType.includes('ADD');
        return (
          <span className={`font-mono font-bold ${isPositive ? 'text-emerald-400' : 'text-rose-400'}`}>
            {isPositive ? '+' : '-'}{val}
          </span>
        );
      }
    },
    {
      header: 'Stock Flow (Before -> After)',
      accessor: 'stockBefore',
      render: (_, row) => (
        <span className="font-mono text-slate-300">
          {row.stockBefore} ➔ <b className="text-white">{row.stockAfter}</b>
        </span>
      )
    },
    {
      header: 'Warehouse Hub',
      accessor: 'warehouseName',
      render: (val) => <span className="text-slate-300">{val}</span>
    },
    {
      header: 'Reference',
      accessor: 'referenceNumber',
      render: (val) => <span className="font-mono text-blue-400 text-xs">{val}</span>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Stock Movements & Ledger Flow"
        description="Immutable audit trail of inventory transfers, physical count reconciliations and logistics dispatches"
        actions={
          <div className="flex items-center space-x-2">
            <button
              onClick={() => setIsTransferModalOpen(true)}
              className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
            >
              <ArrowLeftRight className="w-3.5 h-3.5 mr-1.5" /> Transfer Stock
            </button>
            <button
              onClick={() => setIsAdjustModalOpen(true)}
              className="flex items-center px-3 py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 border border-slate-700 rounded-xl text-xs font-semibold shadow transition-all"
            >
              <SlidersHorizontal className="w-3.5 h-3.5 mr-1.5" /> Adjust Count
            </button>
          </div>
        }
      />

      <DataTable
        title="Stock Card Movement History"
        columns={columns}
        data={movements}
        searchPlaceholder="Search movement ledger..."
      />

      {/* Transfer Modal */}
      <Modal isOpen={isTransferModalOpen} onClose={() => setIsTransferModalOpen(false)} title="Inter-Warehouse Stock Transfer">
        <form onSubmit={handleTransfer} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Select Product</label>
            <select
              value={transferData.productId}
              onChange={(e) => setTransferData({ ...transferData, productId: Number(e.target.value) })}
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            >
              {products.map((p) => (
                <option key={p.id} value={p.id}>
                  {p.name} (Stock: {p.currentStock})
                </option>
              ))}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Source Facility</label>
              <select
                value={transferData.sourceWarehouseId}
                onChange={(e) => setTransferData({ ...transferData, sourceWarehouseId: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {warehouses.map((w) => (
                  <option key={w.id} value={w.id}>
                    {w.name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Destination Facility</label>
              <select
                value={transferData.destinationWarehouseId}
                onChange={(e) => setTransferData({ ...transferData, destinationWarehouseId: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {warehouses.map((w) => (
                  <option key={w.id} value={w.id}>
                    {w.name}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Quantity to Transfer</label>
            <input
              type="number"
              required
              min={1}
              value={transferData.quantity}
              onChange={(e) => setTransferData({ ...transferData, quantity: Number(e.target.value) })}
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsTransferModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30"
            >
              Execute Stock Transfer
            </button>
          </div>
        </form>
      </Modal>

      {/* Adjust Modal */}
      <Modal isOpen={isAdjustModalOpen} onClose={() => setIsAdjustModalOpen(false)} title="Physical Stock Count Adjustment">
        <form onSubmit={handleAdjust} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Product Item</label>
            <select
              value={adjustData.productId}
              onChange={(e) => setAdjustData({ ...adjustData, productId: Number(e.target.value) })}
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            >
              {products.map((p) => (
                <option key={p.id} value={p.id}>
                  {p.name} (Stock: {p.currentStock})
                </option>
              ))}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Adjustment Mode</label>
              <select
                value={adjustData.adjustmentType}
                onChange={(e) => setAdjustData({ ...adjustData, adjustmentType: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="ADDITION">Addition (+ Found Stock / Audit Yield)</option>
                <option value="SUBTRACTION">Subtraction (- Damage / Shrinkage)</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Quantity</label>
              <input
                type="number"
                required
                min={1}
                value={adjustData.quantity}
                onChange={(e) => setAdjustData({ ...adjustData, quantity: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Audit Reconciliation Reason</label>
            <input
              type="text"
              required
              value={adjustData.reason}
              onChange={(e) => setAdjustData({ ...adjustData, reason: e.target.value })}
              placeholder="e.g. Annual warehouse physical inventory count audit"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsAdjustModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30"
            >
              Save Count Adjustment
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
