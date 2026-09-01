import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { FileCheck2, Plus, Trash2, DollarSign, Send } from 'lucide-react';

export function Invoices() {
  const { invoices, addInvoice, deleteInvoice, customers } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    customerId: customers[0]?.id || 1,
    invoiceNumber: '',
    totalAmount: 18500,
    dueDate: new Date(Date.now() + 30 * 86400000).toISOString().split('T')[0],
    notes: 'Payment terms: Net 30 days.'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    const customer = customers.find(c => c.id === Number(formData.customerId)) || customers[0];
    const total = parseFloat(formData.totalAmount) || 15000;
    const sub = total * 0.92;
    const tax = total * 0.08;

    const payload = {
      ...formData,
      customerName: customer?.name || 'Acme Global Corp',
      subTotal: sub,
      taxAmount: tax,
      totalAmount: total,
      balance: total,
      paidAmount: 0,
      status: 'SENT'
    };

    addInvoice(payload);
    addToast('Invoice Issued', `Invoice sent to ${payload.customerName}.`, 'success');
    setIsModalOpen(false);
  };

  const handleDelete = (id, num) => {
    if (window.confirm(`Are you sure you want to delete invoice: ${num || id}?`)) {
      deleteInvoice(id);
      addToast('Invoice Deleted', `Invoice ${num || id} has been removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Invoice #',
      accessor: 'invoiceNumber',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'INV-NEW'}</span>
    },
    {
      header: 'Customer',
      accessor: 'customerName',
      render: (val) => <span className="font-bold text-slate-900">{val || 'Acme Corp'}</span>
    },
    {
      header: 'Issue Date',
      accessor: 'issueDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-08-30'}</span>
    },
    {
      header: 'Due Date',
      accessor: 'dueDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-09-30'}</span>
    },
    {
      header: 'Total Amount',
      accessor: 'totalAmount',
      render: (val, row) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val || row.grandTotal || 18500)}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'PAID' ? 'success' : 'warning'}>{val || 'SENT'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.invoiceNumber)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Invoice"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Commercial Invoices & Accounts Receivable"
        description="Issue tax compliant customer invoices, track aging schedules and reconcile incoming settlements"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Issue New Invoice
          </button>
        }
      />

      <DataTable
        title="Invoices Ledger"
        columns={columns}
        data={invoices}
        searchPlaceholder="Search invoices..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Issue Customer Tax Invoice">
        <form onSubmit={handleSave} className="space-y-4">
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
              {customers.length === 0 && <option value="1">Elevit Client Corp</option>}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Total Billable Amount ($)</label>
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
              <label className="block text-xs font-bold text-slate-700 mb-1">Payment Due Date</label>
              <input
                type="date"
                required
                value={formData.dueDate}
                onChange={(e) => setFormData({ ...formData, dueDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Payment Terms / Memo</label>
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
              Issue Invoice
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
