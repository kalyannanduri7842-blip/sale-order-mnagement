import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useNotification } from '../../context/NotificationContext';
import { FileCheck2, Printer, CheckCircle, Clock } from 'lucide-react';

export function Invoices() {
  const [invoices, setInvoices] = useState([
    { id: 1, invoiceNumber: 'INV-1001', salesOrderNumber: 'SO-1001', customerName: 'Acme Global Technologies Inc', invoiceDate: '2026-08-28', dueDate: '2026-09-28', totalAmount: 57320.0, paidAmount: 57320.0, status: 'PAID' },
    { id: 2, invoiceNumber: 'INV-1002', salesOrderNumber: 'SO-1002', customerName: 'Apex Health Systems LLC', invoiceDate: '2026-08-29', dueDate: '2026-09-29', totalAmount: 19440.0, paidAmount: 0.0, status: 'ISSUED' }
  ]);
  const [selectedInvoice, setSelectedInvoice] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { addToast } = useNotification();

  const handleMarkPaid = (id) => {
    setInvoices(invoices.map((inv) => (inv.id === id ? { ...inv, status: 'PAID', paidAmount: inv.totalAmount } : inv)));
    addToast('Payment Reconciled', 'Invoice balance fully settled in Accounts Receivable ledger.', 'success');
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Invoice #',
      accessor: 'invoiceNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Sales Order Ref',
      accessor: 'salesOrderNumber',
      render: (val) => <span className="font-mono text-slate-300">{val || '—'}</span>
    },
    {
      header: 'Customer Client',
      accessor: 'customerName',
      render: (val) => <span className="font-bold text-white">{val}</span>
    },
    {
      header: 'Issue Date',
      accessor: 'invoiceDate',
      render: (val) => <span className="font-mono text-slate-400">{val}</span>
    },
    {
      header: 'Due Date',
      accessor: 'dueDate',
      render: (val) => <span className="font-mono text-slate-400">{val}</span>
    },
    {
      header: 'Total Due',
      accessor: 'totalAmount',
      render: (val) => <span className="font-mono font-bold text-white">{formatCurrency(val)}</span>
    },
    {
      header: 'Billing Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'PAID' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div className="flex items-center space-x-2">
          {row.status !== 'PAID' && (
            <button
              onClick={() => handleMarkPaid(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold shadow transition-colors"
            >
              Record Payment
            </button>
          )}
          <button
            onClick={() => {
              setSelectedInvoice(row);
              setIsModalOpen(true);
            }}
            className="px-2.5 py-1 bg-slate-800 hover:bg-slate-700 text-slate-200 border border-slate-700 rounded-lg text-xs font-semibold transition-colors"
          >
            View
          </button>
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Commercial Tax Invoices & Accounts Receivable"
        description="Monitor outstanding receivables, payment statuses and generate compliant commercial tax invoices"
      />

      <DataTable
        title="Commercial Invoices Ledger"
        columns={columns}
        data={invoices}
        searchPlaceholder="Search invoices..."
      />

      {/* Invoice Preview Modal */}
      {selectedInvoice && (
        <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Commercial Tax Invoice">
          <div className="space-y-6 p-4 bg-slate-950 rounded-2xl border border-slate-800 font-sans text-xs">
            <div className="flex items-center justify-between border-b border-slate-800 pb-4">
              <div>
                <h4 className="text-base font-bold text-white tracking-tight">ENTERPRISEPRO ERP SYSTEMS INC</h4>
                <p className="text-[11px] text-slate-400">100 Innovation Blvd, Austin, TX 78701 • Tax ID: US-8941092</p>
              </div>
              <div className="text-right">
                <span className="font-mono font-bold text-blue-400 text-sm block">{selectedInvoice.invoiceNumber}</span>
                <span className="text-[10px] text-slate-400">Order: {selectedInvoice.salesOrderNumber}</span>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4 bg-slate-900/60 p-3 rounded-xl border border-slate-800">
              <div>
                <p className="text-slate-400 text-[10px]">Billed To:</p>
                <p className="text-white font-bold">{selectedInvoice.customerName}</p>
                <p className="text-slate-400 text-[10px] mt-1">Payment Term: Net 30 Days</p>
              </div>
              <div className="text-right">
                <p className="text-slate-400 text-[10px]">Invoice Date: <b className="text-slate-200">{selectedInvoice.invoiceDate}</b></p>
                <p className="text-slate-400 text-[10px] mt-1">Due Date: <b className="text-rose-400">{selectedInvoice.dueDate}</b></p>
              </div>
            </div>

            <div className="border border-slate-800 rounded-xl overflow-hidden">
              <table className="w-full text-left text-xs">
                <thead className="bg-slate-900 text-slate-400 uppercase">
                  <tr>
                    <th className="py-2.5 px-4">Line Item Description</th>
                    <th className="py-2.5 px-4 text-right">Amount ($)</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-slate-800 text-slate-300">
                  <tr>
                    <td className="py-3 px-4">Enterprise Server Hardware & ERP Software License Package</td>
                    <td className="py-3 px-4 text-right font-mono font-bold text-white">
                      {formatCurrency(selectedInvoice.totalAmount)}
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <div className="p-4 rounded-xl bg-slate-900/80 border border-slate-800 flex items-center justify-between">
              <div>
                <span className="text-[10px] text-slate-400 font-bold uppercase tracking-wider block">Total Amount Due</span>
                <span className="text-xl font-bold text-emerald-400 font-mono">{formatCurrency(selectedInvoice.totalAmount)}</span>
              </div>
              <Badge variant={selectedInvoice.status === 'PAID' ? 'success' : 'warning'}>
                {selectedInvoice.status === 'PAID' ? 'FULLY PAID' : 'PAYMENT DUE'}
              </Badge>
            </div>

            <div className="flex justify-end space-x-3 pt-2">
              <button
                type="button"
                onClick={() => window.print()}
                className="px-3 py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 rounded-xl text-xs font-semibold flex items-center"
              >
                <Printer className="w-3.5 h-3.5 mr-1.5" /> Print Invoice
              </button>
              <button
                type="button"
                onClick={() => setIsModalOpen(false)}
                className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold"
              >
                Close
              </button>
            </div>
          </div>
        </Modal>
      )}
    </div>
  );
}
