import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { ShoppingCart, Plus, CheckCircle, PackageCheck } from 'lucide-react';

export function PurchaseOrders() {
  const [orders, setOrders] = useState([]);
  const [vendors, setVendors] = useState([]);
  const [warehouses, setWarehouses] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    vendorId: 1,
    destinationWarehouseId: 1,
    orderDate: new Date().toISOString().split('T')[0],
    expectedDeliveryDate: new Date(Date.now() + 7 * 86400000).toISOString().split('T')[0],
    subTotal: 15000,
    taxAmount: 750,
    discountAmount: 0,
    grandTotal: 15750,
    notes: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadOrders();
    loadVendorsAndWarehouses();
  }, []);

  const loadOrders = async () => {
    const data = await api.get('/purchases/orders', 'purchaseOrders');
    setOrders(Array.isArray(data) ? data : data?.content || []);
  };

  const loadVendorsAndWarehouses = async () => {
    const vData = await api.get('/vendors', 'vendors');
    setVendors(Array.isArray(vData) ? vData : vData?.content || []);
    const wData = await api.get('/warehouses', 'warehouses');
    setWarehouses(Array.isArray(wData) ? wData : wData?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const vendor = vendors.find((v) => v.id === Number(formData.vendorId));
    const wh = warehouses.find((w) => w.id === Number(formData.destinationWarehouseId));

    const payload = {
      ...formData,
      poNumber: 'PO-' + (1000 + orders.length + 1),
      vendorName: vendor?.companyName || 'Intel Semiconductor',
      destinationWarehouseName: wh?.name || 'Central Hub',
      status: 'ISSUED',
      paymentStatus: 'UNPAID'
    };

    await api.post('/purchases/orders', payload, 'purchaseOrders');
    addToast('Purchase Order Issued', `PO ${payload.poNumber} issued to ${payload.vendorName}.`, 'success');
    setIsModalOpen(false);
    loadOrders();
  };

  const handleReceive = async (id) => {
    await api.put(`/purchases/orders/${id}/receive-goods`, {}, 'purchaseOrders');
    addToast('Goods Received', 'Stock ledger incremented and Goods Receipt Note (GRN) created.', 'success');
    setOrders(orders.map((o) => (o.id === id ? { ...o, status: 'RECEIVED' } : o)));
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'PO Number',
      accessor: 'poNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Vendor Supplier',
      accessor: 'vendorName',
      render: (val) => <span className="font-bold text-white">{val}</span>
    },
    {
      header: 'Order Date',
      accessor: 'orderDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Expected Delivery',
      accessor: 'expectedDeliveryDate',
      render: (val) => <span className="font-mono text-slate-400">{val || '—'}</span>
    },
    {
      header: 'Total Order Cost',
      accessor: 'grandTotal',
      render: (val) => <span className="font-mono font-bold text-emerald-400">{formatCurrency(val)}</span>
    },
    {
      header: 'Order Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'RECEIVED' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div>
          {row.status !== 'RECEIVED' && (
            <button
              onClick={() => handleReceive(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold flex items-center shadow transition-colors"
            >
              <PackageCheck className="w-3.5 h-3.5 mr-1" /> Receive GRN
            </button>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Purchase Orders (PO) & Goods Receipt (GRN)"
        description="Issue formal supply procurement orders, track expected lead-time fulfillment and inspect incoming goods"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Issue Purchase Order
          </button>
        }
      />

      <DataTable
        title="Procurement Order Logs"
        columns={columns}
        data={orders}
        searchPlaceholder="Search POs..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Issue Formal Purchase Order">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Approved Supplier</label>
              <select
                value={formData.vendorId}
                onChange={(e) => setFormData({ ...formData, vendorId: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {vendors.map((v) => (
                  <option key={v.id} value={v.id}>
                    {v.companyName}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Delivery Destination</label>
              <select
                value={formData.destinationWarehouseId}
                onChange={(e) => setFormData({ ...formData, destinationWarehouseId: Number(e.target.value) })}
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

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Order Date</label>
              <input
                type="date"
                required
                value={formData.orderDate}
                onChange={(e) => setFormData({ ...formData, orderDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Expected Delivery Date</label>
              <input
                type="date"
                required
                value={formData.expectedDeliveryDate}
                onChange={(e) => setFormData({ ...formData, expectedDeliveryDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-3 gap-3">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Subtotal ($)</label>
              <input
                type="number"
                required
                value={formData.subTotal}
                onChange={(e) => {
                  const sub = Number(e.target.value);
                  const tax = sub * 0.05;
                  setFormData({ ...formData, subTotal: sub, taxAmount: tax, grandTotal: sub + tax });
                }}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Est. Tax ($)</label>
              <input
                type="number"
                value={formData.taxAmount}
                onChange={(e) => setFormData({ ...formData, taxAmount: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Total Amount ($)</label>
              <input
                type="number"
                value={formData.grandTotal}
                disabled
                className="w-full bg-slate-900 border border-slate-700 rounded-xl px-3 py-2 text-xs text-emerald-400 font-mono font-bold"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Instructions / Notes</label>
            <textarea
              rows={2}
              value={formData.notes}
              onChange={(e) => setFormData({ ...formData, notes: e.target.value })}
              placeholder="Delivery dock instructions..."
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30"
            >
              Issue Purchase Order
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
