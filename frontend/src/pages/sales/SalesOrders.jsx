import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { BadgeDollarSign, Plus, Trash2, Truck, FileText, CheckCircle2 } from 'lucide-react';

export function SalesOrders() {
  const { salesOrders, addSalesOrder, deleteSalesOrder, customers, products } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    customerId: customers[0]?.id || 1,
    productId: products[0]?.id || 1,
    quantity: 5,
    orderDate: new Date().toISOString().split('T')[0],
    deliveryDate: new Date(Date.now() + 5 * 86400000).toISOString().split('T')[0],
    salesRepName: 'Elena Rostova',
    shippingAddress: ''
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    const customer = customers.find((c) => c.id === Number(formData.customerId)) || customers[0];
    const product = products.find((p) => p.id === Number(formData.productId)) || products[0];
    const unitPrice = product ? product.sellingPrice : 1500;
    const sub = unitPrice * Number(formData.quantity);
    const tax = sub * 0.08;
    const grand = sub + tax;

    const payload = {
      ...formData,
      customerName: customer?.name || 'Enterprise Client',
      subTotal: sub,
      taxAmount: tax,
      discountAmount: 0,
      grandTotal: grand,
      status: 'CONFIRMED',
      paymentStatus: 'UNPAID'
    };

    addSalesOrder(payload);
    addToast('Sales Order Created', `Order confirmed for ${payload.customerName}.`, 'success');
    setIsModalOpen(false);
  };

  const handleDelete = (id, soNumber) => {
    if (window.confirm(`Are you sure you want to delete order: ${soNumber || id}?`)) {
      deleteSalesOrder(id);
      addToast('Order Deleted', `Order ${soNumber || id} removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'SO Number',
      accessor: 'soNumber',
      render: (val, row) => <span className="font-mono font-bold text-blue-600">{val || row.orderNumber || 'SO-NEW'}</span>
    },
    {
      header: 'Customer Name',
      accessor: 'customerName',
      render: (val) => <span className="font-bold text-slate-900">{val || 'Acme Corp'}</span>
    },
    {
      header: 'Order Date',
      accessor: 'orderDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-08-30'}</span>
    },
    {
      header: 'Sales Rep',
      accessor: 'salesRepName',
      render: (val) => <span className="text-slate-700 font-medium">{val || 'Elena Rostova'}</span>
    },
    {
      header: 'Order Value',
      accessor: 'grandTotal',
      render: (val, row) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val || row.totalAmount || 12500)}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'DELIVERED' ? 'success' : 'warning'}>{val || 'CONFIRMED'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <div className="flex items-center space-x-2">
          <button
            onClick={() => handleDelete(id, row.soNumber || row.orderNumber)}
            className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
            title="Delete Sales Order"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Sales Orders & Commercial Fulfillment"
        description="Process commercial client purchase orders, trigger warehouse shipments and billing invoices"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Create Sales Order
          </button>
        }
      />

      <DataTable
        title="Sales Orders Registry"
        columns={columns}
        data={salesOrders}
        searchPlaceholder="Search sales orders by number, customer..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Create Commercial Sales Order">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Customer Account</label>
              <select
                value={formData.customerId}
                onChange={(e) => setFormData({ ...formData, customerId: Number(e.target.value) })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                {customers.map((c) => (
                  <option key={c.id} value={c.id}>
                    {c.name}
                  </option>
                ))}
                {customers.length === 0 && <option value="1">Elevit Default Customer</option>}
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Product Catalog Item</label>
              <select
                value={formData.productId}
                onChange={(e) => setFormData({ ...formData, productId: Number(e.target.value) })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                {products.map((p) => (
                  <option key={p.id} value={p.id}>
                    {p.name} (${p.sellingPrice || 1200})
                  </option>
                ))}
                {products.length === 0 && <option value="1">Enterprise ERP License ($15,000)</option>}
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Ordered Quantity</label>
              <input
                type="number"
                required
                min={1}
                value={formData.quantity}
                onChange={(e) => setFormData({ ...formData, quantity: Number(e.target.value) })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Sales Representative</label>
              <input
                type="text"
                value={formData.salesRepName}
                onChange={(e) => setFormData({ ...formData, salesRepName: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Order Date</label>
              <input
                type="date"
                required
                value={formData.orderDate}
                onChange={(e) => setFormData({ ...formData, orderDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Promised Delivery Date</label>
              <input
                type="date"
                required
                value={formData.deliveryDate}
                onChange={(e) => setFormData({ ...formData, deliveryDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
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
              Confirm Sales Order
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
