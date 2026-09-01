import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Receipt, Plus, CheckCircle2, XCircle } from 'lucide-react';

export function ExpenseTracker() {
  const [expenses, setExpenses] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    title: '',
    category: 'IT & Infrastructure',
    amount: 1000,
    expenseDate: new Date().toISOString().split('T')[0],
    paymentMethod: 'CREDIT_CARD',
    reference: '',
    description: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadExpenses();
  }, []);

  const loadExpenses = async () => {
    const data = await api.get('/expenses', 'expenses');
    setExpenses(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      expenseNumber: 'EXP-' + (1000 + expenses.length + 1),
      status: 'APPROVED'
    };
    await api.post('/expenses', payload, 'expenses');
    addToast('Expense Logged', `Expense ${payload.title} ($${formData.amount}) recorded.`, 'success');
    setIsModalOpen(false);
    loadExpenses();
  };

  const columns = [
    {
      header: 'Expense #',
      accessor: 'expenseNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Description',
      accessor: 'title',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">Ref: {row.reference || 'N/A'}</span>
        </div>
      )
    },
    {
      header: 'Category',
      accessor: 'category',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Date',
      accessor: 'expenseDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Amount',
      accessor: 'amount',
      render: (val) => <span className="font-mono font-bold text-rose-400">${Number(val || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
    },
    {
      header: 'Payment Method',
      accessor: 'paymentMethod',
      render: (val) => <span className="text-xs text-slate-300 uppercase">{val}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant="success">{val}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Operational Expenditures & Bills"
        description="Log corporate receipts, utility expenditures, software vendor subscriptions and cloud bills"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Log Expenditure
          </button>
        }
      />

      <DataTable
        title="Expenditure Ledger"
        columns={columns}
        data={expenses}
        searchPlaceholder="Search expenses..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Record Corporate Expenditure">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Expense Title / Vendor</label>
            <input
              type="text"
              required
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
              placeholder="e.g. Google Cloud Platform billing"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Expense Category</label>
              <select
                value={formData.category}
                onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="IT & Infrastructure">IT & Infrastructure</option>
                <option value="Utilities & Facilities">Utilities & Facilities</option>
                <option value="Marketing & Advertising">Marketing & Advertising</option>
                <option value="Legal & Professional">Legal & Professional</option>
                <option value="Travel & Meals">Travel & Meals</option>
                <option value="Office Supplies">Office Supplies</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Amount ($)</label>
              <input
                type="number"
                required
                value={formData.amount}
                onChange={(e) => setFormData({ ...formData, amount: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Date of Payment</label>
              <input
                type="date"
                required
                value={formData.expenseDate}
                onChange={(e) => setFormData({ ...formData, expenseDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Payment Method</label>
              <select
                value={formData.paymentMethod}
                onChange={(e) => setFormData({ ...formData, paymentMethod: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="CREDIT_CARD">Corporate Credit Card</option>
                <option value="BANK_TRANSFER">Direct Wire / ACH</option>
                <option value="CASH">Petty Cash</option>
                <option value="CHECK">Corporate Check</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Invoice / Receipt Reference Number</label>
            <input
              type="text"
              value={formData.reference}
              onChange={(e) => setFormData({ ...formData, reference: e.target.value })}
              placeholder="e.g. INV-99120"
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
              Record Expense
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
