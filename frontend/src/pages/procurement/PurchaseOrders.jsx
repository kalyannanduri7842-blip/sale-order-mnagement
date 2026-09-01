import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { ShoppingCart, Plus, Trash2, CheckCircle2, Truck } from 'lucide-react';

export function PurchaseOrders() {
  const { purchaseOrders, addPurchaseOrder, deletePurchaseOrder, vendors } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    vendorId: vendors[0]?.id || 1,
    poNumber: '',
    totalAmount: 24500,
    expectedDate: new Date(Date.now() + 7 * 86400000).toISOString().split('T')[0],
    notes: 'Standard requisition terms.'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    const vendor = vendors.find(v => v.id === Number(formData.vendorId)) || vendors[0];
    const total = parseFloat(formData.totalAmount) || 24500;

    const payload = {
      ...formData,
      vendorName: vendor?.name || 'Cisco Systems Global',
      totalAmount: total,
      status: 'SENT'
    };

    addPurchaseOrder(payload);
    addToast('Purchase Order Issued', `PO dispatched to ${payload.vendorName}.`, 'success');
    setIsModalOpen(false);
  };

  const handleDelete = (id, num) => {
    if (window.confirm(`Are you sure you want to delete purchase order: ${num || id}?`)) {
      deletePurchaseOrder(id);
      addToast('PO Deleted', `Purchase order ${num || id} removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'PO Number',
      accessor: 'poNumber',
      render: (val, row) => <span className="font-mono font-bold text-blue-600">{val || row.orderNumber || 'PO-NEW'}</span>
    },
    {
      header: 'Supplier / Vendor',
      accessor: 'vendorName',
      render: (val, row) => <span className="font-bold text-slate-900">{val || row.supplierName || 'Cisco Systems'}</span>
    },
    {
      header: 'Order Date',
      accessor: 'orderDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-08-30'}</span>
    },
    {
      header: 'Expected Arrival',
      accessor: 'expectedDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-09-06'}</span>
    },
    {
      header: 'Total Value',
      accessor: 'totalAmount',
      render: (val) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val || 24500)}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'RECEIVED' ? 'success' : 'warning'}>{val || 'SENT'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.poNumber || row.orderNumber)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Purchase Order"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Purchase Orders & Vendor Requisitions"
        description="Procurement orders, vendor fulfillment status, receiving dock receipts and accounts payable tracking"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Create Purchase Order
          </button>
        }
      />

      <DataTable
        title="Purchase Orders Ledger"
        columns={columns}
        data={purchaseOrders}
        searchPlaceholder="Search purchase orders..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Issue Vendor Purchase Order">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Approved Supplier</label>
            <select
              value={formData.vendorId}
              onChange={(e) => setFormData({ ...formData, vendorId: Number(e.target.value) })}
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
            >
              {vendors.map((v) => (
                <option key={v.id} value={v.id}>
                  {v.name} ({v.category})
                </option>
              ))}
              {vendors.length === 0 && <option value="1">Elevit Approved Hardware Vendor</option>}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">PO Total Estimated Amount ($)</label>
              <input
                type="number"
                required
                min={1}
                value={formData.totalAmount}
                onChange={(e) => setFormData({ ...formData, totalAmount: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Expected Delivery Date</label>
              <input
                type="date"
                required
                value={formData.expectedDate}
                onChange={(e) => setFormData({ ...formData, expectedDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Requisition Reason / Notes</label>
            <textarea
              rows={3}
              value={formData.notes}
              onChange={(e) => setFormData({ ...formData, notes: e.target.value })}
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>

          <div className="pt-4 border-t border-slate-100 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 rounded-xl text-xs font-bold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="btn-primary text-xs"
            >
              Issue Purchase Order
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
