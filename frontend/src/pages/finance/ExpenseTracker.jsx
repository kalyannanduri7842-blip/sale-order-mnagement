import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Receipt, Plus, Trash2, DollarSign } from 'lucide-react';

export function ExpenseTracker() {
  const { expenses, addExpense, deleteExpense } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    title: '',
    category: 'IT & Infrastructure',
    amount: 1500,
    paymentMethod: 'CREDIT_CARD',
    reference: ''
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addExpense({
      ...formData,
      amount: parseFloat(formData.amount) || 0
    });
    addToast('Expense Recorded', `Disbursement of $${formData.amount} posted to ledger.`, 'success');
    setIsModalOpen(false);
    setFormData({
      title: '',
      category: 'IT & Infrastructure',
      amount: 1500,
      paymentMethod: 'CREDIT_CARD',
      reference: ''
    });
  };

  const handleDelete = (id, title) => {
    if (window.confirm(`Are you sure you want to delete expense record: ${title}?`)) {
      deleteExpense(id);
      addToast('Expense Deleted', `${title} removed from ledger.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Expense #',
      accessor: 'expenseNumber',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'EXP-NEW'}</span>
    },
    {
      header: 'Description / Payee',
      accessor: 'title',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Category',
      accessor: 'category',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Disbursement Date',
      accessor: 'expenseDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-08-30'}</span>
    },
    {
      header: 'Amount',
      accessor: 'amount',
      render: (val) => <span className="font-mono font-bold text-rose-600">-{formatCurrency(val)}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'APPROVED' ? 'success' : 'warning'}>{val || 'APPROVED'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.title)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Expense Record"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Operating Expenses & Corporate Disbursements"
        description="Track departmental expenses, cloud infrastructure, travel reimbursements and corporate vendor billing"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Record Expense
          </button>
        }
      />

      <DataTable
        title="Expense Disbursements"
        columns={columns}
        data={expenses}
        searchPlaceholder="Search expenses by title, category..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Record Operational Expense">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Expense Title / Payee</label>
            <input
              type="text"
              required
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
              placeholder="e.g. AWS Cloud Hosting & Kubernetes Cluster"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Expense Category</label>
              <select
                value={formData.category}
                onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="IT & Infrastructure">IT & Infrastructure</option>
                <option value="Marketing & Growth">Marketing & Growth</option>
                <option value="Travel & Entertainment">Travel & Entertainment</option>
                <option value="Office & Facilities">Office & Facilities</option>
                <option value="Legal & Professional">Legal & Professional</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Total Amount ($)</label>
              <input
                type="number"
                required
                min={1}
                value={formData.amount}
                onChange={(e) => setFormData({ ...formData, amount: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Payment Method</label>
              <select
                value={formData.paymentMethod}
                onChange={(e) => setFormData({ ...formData, paymentMethod: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="CREDIT_CARD">Corporate Credit Card</option>
                <option value="BANK_TRANSFER">Direct Wire / Bank Transfer</option>
                <option value="PETTY_CASH">Petty Cash</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Receipt Reference #</label>
              <input
                type="text"
                value={formData.reference}
                onChange={(e) => setFormData({ ...formData, reference: e.target.value })}
                placeholder="INV-AWS-88910"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
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
              Post Expense
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
