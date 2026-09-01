import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Plus, Check, AlertCircle, FileSpreadsheet, Trash2 } from 'lucide-react';

export function JournalEntries() {
  const [entries, setEntries] = useState([
    { id: 1, entryNumber: 'JV-1001', entryDate: '2026-08-30', reference: 'DEPR-AUG-2026', description: 'Monthly fixed asset depreciation amortization', totalDebit: 12400.0, totalCredit: 12400.0, status: 'POSTED' },
    { id: 2, entryNumber: 'JV-1002', entryDate: '2026-08-29', reference: 'PAYROLL-AUG-2026', description: 'August payroll wages, tax withholding and employer match', totalDebit: 24500.0, totalCredit: 24500.0, status: 'POSTED' }
  ]);
  const [accounts, setAccounts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [entryDate, setEntryDate] = useState(new Date().toISOString().split('T')[0]);
  const [reference, setReference] = useState('');
  const [description, setDescription] = useState('');
  const [lines, setLines] = useState([
    { accountId: 1, description: 'Debit Entry', debit: 5000, credit: 0 },
    { accountId: 2, description: 'Credit Entry', debit: 0, credit: 5000 }
  ]);
  const { addToast } = useNotification();

  useEffect(() => {
    loadAccounts();
  }, []);

  const loadAccounts = async () => {
    const data = await api.get('/finance/accounts', 'accounts');
    setAccounts(Array.isArray(data) ? data : data?.content || []);
  };

  const handleLineChange = (index, field, value) => {
    const newLines = [...lines];
    newLines[index][field] = value;
    setLines(newLines);
  };

  const addLine = () => {
    setLines([...lines, { accountId: accounts[0]?.id || 1, description: '', debit: 0, credit: 0 }]);
  };

  const removeLine = (index) => {
    if (lines.length > 2) {
      setLines(lines.filter((_, i) => i !== index));
    }
  };

  const totalDebit = lines.reduce((sum, l) => sum + (Number(l.debit) || 0), 0);
  const totalCredit = lines.reduce((sum, l) => sum + (Number(l.credit) || 0), 0);
  const isBalanced = Math.abs(totalDebit - totalCredit) < 0.01 && totalDebit > 0;

  const handleSave = async (e) => {
    e.preventDefault();
    if (!isBalanced) {
      addToast('Unbalanced Voucher', 'Total Debits must equal Total Credits before posting.', 'error');
      return;
    }

    const payload = {
      entryNumber: 'JV-' + (1000 + entries.length + 1),
      entryDate,
      reference,
      description,
      totalDebit,
      totalCredit,
      status: 'POSTED',
      lines
    };

    setEntries([payload, ...entries]);
    addToast('Journal Entry Posted', `Journal voucher ${payload.entryNumber} posted to general ledger.`, 'success');
    setIsModalOpen(false);
  };

  const columns = [
    {
      header: 'Voucher #',
      accessor: 'entryNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Posting Date',
      accessor: 'entryDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Reference',
      accessor: 'reference',
      render: (val) => <span className="font-mono text-slate-400 text-xs">{val || '—'}</span>
    },
    {
      header: 'Description',
      accessor: 'description',
      render: (val) => <span className="text-slate-200">{val}</span>
    },
    {
      header: 'Debit Total',
      accessor: 'totalDebit',
      render: (val) => <span className="font-mono font-semibold text-white">${Number(val || 0).toLocaleString()}</span>
    },
    {
      header: 'Credit Total',
      accessor: 'totalCredit',
      render: (val) => <span className="font-mono font-semibold text-white">${Number(val || 0).toLocaleString()}</span>
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
        title="Double-Entry Journal Vouchers"
        description="Record balanced financial transactions with automatic debit & credit ledger validation"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Post Journal Entry
          </button>
        }
      />

      <DataTable
        title="Posted Journal Entries"
        columns={columns}
        data={entries}
        searchPlaceholder="Search journal vouchers..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} maxWidth="max-w-4xl" title="Create Journal Voucher">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-3 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Posting Date</label>
              <input
                type="date"
                required
                value={entryDate}
                onChange={(e) => setEntryDate(e.target.value)}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Reference / Doc ID</label>
              <input
                type="text"
                value={reference}
                onChange={(e) => setReference(e.target.value)}
                placeholder="e.g. MEMO-991"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Description</label>
              <input
                type="text"
                required
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                placeholder="Voucher narrative..."
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          {/* Line Items */}
          <div className="border border-slate-800 rounded-xl p-4 space-y-3 bg-slate-950/50">
            <div className="flex items-center justify-between">
              <span className="text-xs font-bold text-slate-200">Journal Lines (Debits & Credits)</span>
              <button
                type="button"
                onClick={addLine}
                className="text-xs text-blue-400 hover:text-blue-300 flex items-center font-semibold"
              >
                <Plus className="w-3 h-3 mr-1" /> Add Line
              </button>
            </div>

            <div className="space-y-2">
              {lines.map((line, idx) => (
                <div key={idx} className="grid grid-cols-12 gap-2 items-center">
                  <div className="col-span-4">
                    <select
                      value={line.accountId}
                      onChange={(e) => handleLineChange(idx, 'accountId', Number(e.target.value))}
                      className="w-full bg-slate-900 border border-slate-700 rounded-lg px-2 py-1.5 text-xs text-white"
                    >
                      {accounts.map((a) => (
                        <option key={a.id} value={a.id}>
                          {a.accountNumber} - {a.accountName}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="col-span-3">
                    <input
                      type="text"
                      placeholder="Line memo..."
                      value={line.description}
                      onChange={(e) => handleLineChange(idx, 'description', e.target.value)}
                      className="w-full bg-slate-900 border border-slate-700 rounded-lg px-2 py-1.5 text-xs text-white"
                    />
                  </div>
                  <div className="col-span-2">
                    <input
                      type="number"
                      placeholder="Debit ($)"
                      value={line.debit}
                      onChange={(e) => handleLineChange(idx, 'debit', Number(e.target.value))}
                      className="w-full bg-slate-900 border border-slate-700 rounded-lg px-2 py-1.5 text-xs text-white text-right font-mono"
                    />
                  </div>
                  <div className="col-span-2">
                    <input
                      type="number"
                      placeholder="Credit ($)"
                      value={line.credit}
                      onChange={(e) => handleLineChange(idx, 'credit', Number(e.target.value))}
                      className="w-full bg-slate-900 border border-slate-700 rounded-lg px-2 py-1.5 text-xs text-white text-right font-mono"
                    />
                  </div>
                  <div className="col-span-1 text-center">
                    <button
                      type="button"
                      onClick={() => removeLine(idx)}
                      className="text-slate-500 hover:text-rose-400 p-1"
                    >
                      <Trash2 className="w-3.5 h-3.5" />
                    </button>
                  </div>
                </div>
              ))}
            </div>

            {/* Total Balance Validation Bar */}
            <div className="pt-3 border-t border-slate-800 flex items-center justify-between text-xs">
              <div className="flex items-center space-x-2">
                {isBalanced ? (
                  <span className="text-emerald-400 font-semibold flex items-center">
                    <Check className="w-4 h-4 mr-1" /> Balanced ($0.00 Difference)
                  </span>
                ) : (
                  <span className="text-rose-400 font-semibold flex items-center">
                    <AlertCircle className="w-4 h-4 mr-1" /> Imbalance: ${Math.abs(totalDebit - totalCredit).toFixed(2)}
                  </span>
                )}
              </div>
              <div className="space-x-4 font-mono">
                <span>Total Debit: <b className="text-white">${totalDebit.toFixed(2)}</b></span>
                <span>Total Credit: <b className="text-white">${totalCredit.toFixed(2)}</b></span>
              </div>
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
              disabled={!isBalanced}
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 disabled:opacity-40 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30"
            >
              Post Journal Voucher
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
