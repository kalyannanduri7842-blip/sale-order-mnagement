import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { CreditCard, PlayCircle, FileText, CheckCircle, Printer, Download } from 'lucide-react';

export function PayrollManagement() {
  const [payrollRecords, setPayrollRecords] = useState([]);
  const [selectedPayslip, setSelectedPayslip] = useState(null);
  const [isPayslipOpen, setIsPayslipOpen] = useState(false);
  const { addToast } = useNotification();

  useEffect(() => {
    loadPayroll();
  }, []);

  const loadPayroll = async () => {
    const data = await api.get('/payroll', 'payroll');
    setPayrollRecords(Array.isArray(data) ? data : data?.content || []);
  };

  const handleGeneratePayroll = async () => {
    const data = await api.post('/payroll/generate?month=8&year=2026', {}, 'payroll');
    addToast('Payroll Batch Generated', 'Monthly salary calculations generated for all active employees.', 'success');
    loadPayroll();
  };

  const handleProcessPayment = async (id) => {
    await api.put(`/payroll/${id}/process-payment`, { paymentMethod: 'BANK_TRANSFER' }, 'payroll');
    addToast('Payment Disbursed', 'Direct deposit batch executed & Payslip generated.', 'success');
    setPayrollRecords(payrollRecords.map((p) => (p.id === id ? { ...p, paymentStatus: 'PAID' } : p)));
  };

  const viewPayslip = (row) => {
    setSelectedPayslip(row);
    setIsPayslipOpen(true);
  };

  const columns = [
    {
      header: 'Employee',
      accessor: 'employeeName',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">{row.departmentName}</span>
        </div>
      )
    },
    {
      header: 'Month/Year',
      accessor: 'month',
      render: (val, row) => <span className="font-mono text-slate-300">August 2026</span>
    },
    {
      header: 'Basic Pay',
      accessor: 'basicSalary',
      render: (val) => <span className="font-mono">${Number(val || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
    },
    {
      header: 'Allowances',
      accessor: 'allowancesTotal',
      render: (val) => <span className="font-mono text-cyan-400">+${Number(val || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
    },
    {
      header: 'Deductions (Tax/PF)',
      accessor: 'deductionsTotal',
      render: (val) => <span className="font-mono text-rose-400">-${Number(val || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
    },
    {
      header: 'Net Payable',
      accessor: 'netSalary',
      render: (val) => <span className="font-mono font-bold text-emerald-400">${Number(val || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
    },
    {
      header: 'Status',
      accessor: 'paymentStatus',
      render: (val) => <Badge variant={val === 'PAID' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div className="flex items-center space-x-2">
          {row.paymentStatus !== 'PAID' ? (
            <button
              onClick={() => handleProcessPayment(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold shadow transition-colors"
            >
              Disburse
            </button>
          ) : (
            <button
              onClick={() => viewPayslip(row)}
              className="px-2.5 py-1 bg-slate-800 hover:bg-slate-700 text-blue-400 border border-slate-700 rounded-lg text-xs font-semibold flex items-center transition-colors"
            >
              <FileText className="w-3.5 h-3.5 mr-1" /> Payslip
            </button>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Payroll Processing & Automated Payslips"
        description="Calculate monthly salaries, tax deductions, PF contributions and generate compliant electronic payslips"
        actions={
          <button
            onClick={handleGeneratePayroll}
            className="flex items-center px-3 py-2 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30 transition-all"
          >
            <PlayCircle className="w-3.5 h-3.5 mr-1.5" /> Run Monthly Payroll Batch
          </button>
        }
      />

      <DataTable
        title="August 2026 Payroll Cycle"
        columns={columns}
        data={payrollRecords}
        searchPlaceholder="Search by employee..."
      />

      {/* Payslip View Modal */}
      {selectedPayslip && (
        <Modal isOpen={isPayslipOpen} onClose={() => setIsPayslipOpen(false)} title="Electronic Payslip">
          <div className="space-y-6 p-4 bg-slate-950 rounded-2xl border border-slate-800 font-sans text-xs">
            {/* Header */}
            <div className="flex items-center justify-between border-b border-slate-800 pb-4">
              <div>
                <h4 className="text-base font-bold text-white tracking-tight">ENTERPRISEPRO ERP SYSTEMS</h4>
                <p className="text-[11px] text-slate-400">100 Innovation Blvd, Austin, TX 78701</p>
              </div>
              <div className="text-right">
                <span className="font-mono font-bold text-blue-400 block">PAYSLIP-2026-08</span>
                <span className="text-[10px] text-slate-400">Date: {selectedPayslip.paymentDate || '2026-08-29'}</span>
              </div>
            </div>

            {/* Employee Details */}
            <div className="grid grid-cols-2 gap-4 bg-slate-900/60 p-3 rounded-xl border border-slate-800/80">
              <div>
                <p className="text-slate-400 text-[10px]">Employee Name:</p>
                <p className="text-white font-bold">{selectedPayslip.employeeName}</p>
                <p className="text-slate-400 text-[10px] mt-2">Department:</p>
                <p className="text-slate-200">{selectedPayslip.departmentName || 'Engineering'}</p>
              </div>
              <div>
                <p className="text-slate-400 text-[10px]">Employee Code:</p>
                <p className="text-blue-400 font-mono font-bold">{selectedPayslip.employeeCode || 'EMP-1001'}</p>
                <p className="text-slate-400 text-[10px] mt-2">Bank Reference:</p>
                <p className="text-slate-200 font-mono">{selectedPayslip.transactionReference || 'TXN-99410291'}</p>
              </div>
            </div>

            {/* Earnings and Deductions Table */}
            <div className="grid grid-cols-2 gap-4">
              {/* Earnings */}
              <div className="border border-slate-800 rounded-xl p-3">
                <h5 className="font-bold text-slate-200 mb-2 border-b border-slate-800 pb-1">Earnings</h5>
                <div className="space-y-1.5 text-slate-300">
                  <div className="flex justify-between">
                    <span>Basic Salary:</span>
                    <span className="font-mono font-semibold">${Number(selectedPayslip.basicSalary || 0).toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between">
                    <span>House Rent & Travel:</span>
                    <span className="font-mono font-semibold">${Number(selectedPayslip.allowancesTotal || 0).toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between">
                    <span>Performance Bonus:</span>
                    <span className="font-mono font-semibold">${Number(selectedPayslip.bonuses || 0).toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-white">
                    <span>Gross Earnings:</span>
                    <span className="font-mono">${Number(selectedPayslip.grossSalary || 0).toFixed(2)}</span>
                  </div>
                </div>
              </div>

              {/* Deductions */}
              <div className="border border-slate-800 rounded-xl p-3">
                <h5 className="font-bold text-slate-200 mb-2 border-b border-slate-800 pb-1">Deductions</h5>
                <div className="space-y-1.5 text-slate-300">
                  <div className="flex justify-between">
                    <span>Income Tax (PAYE):</span>
                    <span className="font-mono text-rose-400 font-semibold">${Number(selectedPayslip.taxDeduction || 0).toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between">
                    <span>Provident / 401(k):</span>
                    <span className="font-mono text-rose-400 font-semibold">${(Number(selectedPayslip.deductionsTotal || 0) - Number(selectedPayslip.taxDeduction || 0)).toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-white">
                    <span>Total Deductions:</span>
                    <span className="font-mono text-rose-400">-${Number(selectedPayslip.deductionsTotal || 0).toFixed(2)}</span>
                  </div>
                </div>
              </div>
            </div>

            {/* Net Amount Banner */}
            <div className="p-4 rounded-xl bg-emerald-950/60 border border-emerald-700/60 flex items-center justify-between">
              <div>
                <span className="text-[10px] text-emerald-400 font-bold uppercase tracking-wider block">Net Disbursed Salary</span>
                <span className="text-xl font-bold text-white font-mono">${Number(selectedPayslip.netSalary || 0).toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
              </div>
              <Badge variant="success">PAID VIA DIRECT DEPOSIT</Badge>
            </div>

            <div className="flex justify-end space-x-3 pt-2">
              <button
                type="button"
                onClick={() => window.print()}
                className="px-3 py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 rounded-xl text-xs font-semibold flex items-center"
              >
                <Printer className="w-3.5 h-3.5 mr-1.5" /> Print Payslip
              </button>
              <button
                type="button"
                onClick={() => setIsPayslipOpen(false)}
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
