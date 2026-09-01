import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { BookOpen, Plus, Trash2, DollarSign } from 'lucide-react';

export function ChartOfAccounts() {
  const { accounts, addAccount, deleteAccount } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    accountNumber: '',
    accountName: '',
    accountType: 'ASSET',
    subType: 'CASH',
    balance: 50000
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addAccount(formData);
    addToast('Account Created', `GL Account ${formData.accountName} added.`, 'success');
    setIsModalOpen(false);
    setFormData({
      accountNumber: '',
      accountName: '',
      accountType: 'ASSET',
      subType: 'CASH',
      balance: 50000
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to delete general ledger account: ${name}?`)) {
      deleteAccount(id);
      addToast('Account Deleted', `${name} has been removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'GL Code',
      accessor: 'accountNumber',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || '1000'}</span>
    },
    {
      header: 'Account Name',
      accessor: 'accountName',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Category',
      accessor: 'accountType',
      render: (val) => <Badge variant={val === 'ASSET' || val === 'REVENUE' ? 'success' : 'primary'}>{val}</Badge>
    },
    {
      header: 'Classification',
      accessor: 'subType',
      render: (val) => <span className="text-slate-600 font-medium text-xs">{val || 'STANDARD'}</span>
    },
    {
      header: 'Current Ledger Balance',
      accessor: 'balance',
      render: (val) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val)}</span>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.accountName)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete GL Account"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Chart of Accounts & General Ledger Structure"
        description="Double-entry chart of accounts defining Assets, Liabilities, Equity, Revenues and Operating Expenses"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add GL Account
          </button>
        }
      />

      <DataTable
        title="General Ledger Chart of Accounts"
        columns={columns}
        data={accounts}
        searchPlaceholder="Search accounts by code, name..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Create General Ledger Account">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">GL Account Number</label>
              <input
                type="text"
                required
                value={formData.accountNumber}
                onChange={(e) => setFormData({ ...formData, accountNumber: e.target.value })}
                placeholder="e.g. 1050"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Account Title / Name</label>
              <input
                type="text"
                required
                value={formData.accountName}
                onChange={(e) => setFormData({ ...formData, accountName: e.target.value })}
                placeholder="e.g. Petty Cash Reserve"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Account Type</label>
              <select
                value={formData.accountType}
                onChange={(e) => setFormData({ ...formData, accountType: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="ASSET">ASSET</option>
                <option value="LIABILITY">LIABILITY</option>
                <option value="EQUITY">EQUITY</option>
                <option value="REVENUE">REVENUE</option>
                <option value="EXPENSE">EXPENSE</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Opening Balance ($)</label>
              <input
                type="number"
                value={formData.balance}
                onChange={(e) => setFormData({ ...formData, balance: e.target.value })}
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
              Save GL Account
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
