import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { BadgeDollarSign, Plus, Truck, FileText, CheckCircle2 } from 'lucide-react';

export function SalesOrders() {
  const [orders, setOrders] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [products, setProducts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    customerId: 1,
    productId: 1,
    quantity: 10,
    orderDate: new Date().toISOString().split('T')[0],
    deliveryDate: new Date(Date.now() + 5 * 86400000).toISOString().split('T')[0],
    salesRepName: 'Elena Rostova',
    shippingAddress: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadOrders();
    loadReferences();
  }, []);

  const loadOrders = async () => {
    const data = await api.get('/sales/orders', 'salesOrders');
    setOrders(Array.isArray(data) ? data : data?.content || []);
  };

  const loadReferences = async () => {
    const cData = await api.get('/customers', 'customers');
    setCustomers(Array.isArray(cData) ? cData : cData?.content || []);
    const pData = await api.get('/products', 'products');
    setProducts(Array.isArray(pData) ? pData : pData?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const customer = customers.find((c) => c.id === Number(formData.customerId));
    const product = products.find((p) => p.id === Number(formData.productId));
    const unitPrice = product ? product.sellingPrice : 1200;
    const sub = unitPrice * Number(formData.quantity);
    const tax = sub * 0.08;
    const grand = sub + tax;

    const payload = {
      ...formData,
      soNumber: 'SO-' + (1000 + orders.length + 1),
      customerName: customer?.name || 'Acme Global',
      subTotal: sub,
      taxAmount: tax,
      discountAmount: 0,
      grandTotal: grand,
      status: 'CONFIRMED',
      paymentStatus: 'UNPAID'
    };

    await api.post('/sales/orders', payload, 'salesOrders');
    addToast('Sales Order Created', `Order ${payload.soNumber} confirmed for ${payload.customerName}.`, 'success');
    setIsModalOpen(false);
    loadOrders();
  };

  const handleDeliver = async (id) => {
    await api.put(`/sales/orders/${id}/deliver`, {}, 'salesOrders');
    addToast('Order Dispatched & Delivered', 'Inventory deducted and delivery shipment logged.', 'success');
    setOrders(orders.map((o) => (o.id === id ? { ...o, status: 'DELIVERED' } : o)));
  };

  const handleGenerateInvoice = async (id) => {
    await api.post(`/sales/orders/${id}/generate-invoice`, {}, 'salesOrders');
    addToast('Tax Invoice Generated', 'Customer billing invoice created with 30-day term.', 'success');
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'SO Number',
      accessor: 'soNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Customer Name',
      accessor: 'customerName',
      render: (val) => <span className="font-bold text-white">{val}</span>
    },
    {
      header: 'Order Date',
      accessor: 'orderDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Sales Representative',
      accessor: 'salesRepName',
      render: (val) => <span className="text-slate-300">{val}</span>
    },
    {
      header: 'Order Value',
      accessor: 'grandTotal',
      render: (val) => <span className="font-mono font-bold text-emerald-400">{formatCurrency(val)}</span>
    },
    {
      header: 'Fulfillment',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'DELIVERED' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div className="flex items-center space-x-2">
          {row.status !== 'DELIVERED' ? (
            <button
              onClick={() => handleDeliver(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold flex items-center shadow transition-colors"
            >
              <Truck className="w-3.5 h-3.5 mr-1" /> Deliver
            </button>
          ) : (
            <button
              onClick={() => handleGenerateInvoice(val)}
              className="px-2.5 py-1 bg-slate-800 hover:bg-slate-700 text-blue-400 border border-slate-700 rounded-lg text-xs font-semibold flex items-center transition-colors"
            >
              <FileText className="w-3.5 h-3.5 mr-1" /> Invoice
            </button>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Sales Orders & Commercial Fulfillment"
        description="Process commercial client purchase requests, trigger warehouse dispatches and billing invoices"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Create Sales Order
          </button>
        }
      />

      <DataTable
        title="Sales Orders Registry"
        columns={columns}
        data={orders}
        searchPlaceholder="Search sales orders..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Create Commercial Sales Order">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Customer Client</label>
              <select
                value={formData.customerId}
                onChange={(e) => setFormData({ ...formData, customerId: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {customers.map((c) => (
                  <option key={c.id} value={c.id}>
                    {c.name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Product Item</label>
              <select
                value={formData.productId}
                onChange={(e) => setFormData({ ...formData, productId: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {products.map((p) => (
                  <option key={p.id} value={p.id}>
                    {p.name} (${p.sellingPrice})
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Ordered Quantity</label>
              <input
                type="number"
                required
                min={1}
                value={formData.quantity}
                onChange={(e) => setFormData({ ...formData, quantity: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Sales Representative</label>
              <input
                type="text"
                value={formData.salesRepName}
                onChange={(e) => setFormData({ ...formData, salesRepName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
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
              <label className="block text-xs font-semibold text-slate-300 mb-1">Promised Delivery Date</label>
              <input
                type="date"
                required
                value={formData.deliveryDate}
                onChange={(e) => setFormData({ ...formData, deliveryDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
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
              Confirm Sales Order
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
