import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Plus, BookOpen, Layers, DollarSign } from 'lucide-react';

export function ChartOfAccounts() {
  const [accounts, setAccounts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    accountNumber: '',
    accountName: '',
    accountType: 'ASSET',
    subType: 'CURRENT_ASSET',
    balance: 0,
    description: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadAccounts();
  }, []);

  const loadAccounts = async () => {
    const data = await api.get('/finance/accounts', 'accounts');
    setAccounts(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    await api.post('/finance/accounts', formData, 'accounts');
    addToast('Account Created', `Account ${formData.accountNumber} - ${formData.accountName} registered in ledger.`, 'success');
    setIsModalOpen(false);
    loadAccounts();
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Account #',
      accessor: 'accountNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Account Name',
      accessor: 'accountName',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">{row.subType}</span>
        </div>
      )
    },
    {
      header: 'Classification',
      accessor: 'accountType',
      render: (val) => {
        const map = {
          ASSET: 'primary',
          LIABILITY: 'warning',
          EQUITY: 'purple',
          REVENUE: 'success',
          EXPENSE: 'danger'
        };
        return <Badge variant={map[val] || 'default'}>{val}</Badge>;
      }
    },
    {
      header: 'Current Ledger Balance',
      accessor: 'balance',
      render: (val, row) => (
        <span className={`font-mono font-bold ${row.accountType === 'EXPENSE' || row.accountType === 'LIABILITY' ? 'text-amber-400' : 'text-emerald-400'}`}>
          {formatCurrency(val)}
        </span>
      )
    },
    {
      header: 'Status',
      accessor: 'active',
      render: (val) => <Badge variant={val !== false ? 'success' : 'danger'}>{val !== false ? 'ACTIVE' : 'LOCKED'}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Chart of Accounts (COA) Ledger"
        description="General ledger classification tree across Assets, Liabilities, Equity, Revenues and Operating Expenses"
        actions={
          <button
            onClick={() => {
              setFormData({
                accountNumber: String(1000 + accounts.length * 10),
                accountName: '',
                accountType: 'ASSET',
                subType: 'CURRENT_ASSET',
                balance: 0,
                description: ''
              });
              setIsModalOpen(true);
            }}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add GL Account
          </button>
        }
      />

      <DataTable
        title="General Ledger Master Accounts"
        columns={columns}
        data={accounts}
        searchPlaceholder="Search accounts by number or name..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register General Ledger Account">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Account Number (GL)</label>
              <input
                type="text"
                required
                value={formData.accountNumber}
                onChange={(e) => setFormData({ ...formData, accountNumber: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Account Name</label>
              <input
                type="text"
                required
                value={formData.accountName}
                onChange={(e) => setFormData({ ...formData, accountName: e.target.value })}
                placeholder="e.g. Petty Cash"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Account Classification</label>
              <select
                value={formData.accountType}
                onChange={(e) => setFormData({ ...formData, accountType: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="ASSET">ASSET (Debit Normal)</option>
                <option value="LIABILITY">LIABILITY (Credit Normal)</option>
                <option value="EQUITY">EQUITY (Credit Normal)</option>
                <option value="REVENUE">REVENUE (Credit Normal)</option>
                <option value="EXPENSE">EXPENSE (Debit Normal)</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Opening Balance ($)</label>
              <input
                type="number"
                value={formData.balance}
                onChange={(e) => setFormData({ ...formData, balance: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Description / Notes</label>
            <textarea
              rows={2}
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold transition-colors"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30 transition-colors"
            >
              Save GL Account
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
