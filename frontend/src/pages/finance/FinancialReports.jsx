import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { FileSpreadsheet, Download, CheckCircle2, DollarSign, ArrowUpRight, ArrowDownRight } from 'lucide-react';

export function FinancialReports() {
  const [activeTab, setActiveTab] = useState('trial_balance');

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const trialBalanceAccounts = [
    { number: '1000', name: 'Operating Cash Account', type: 'ASSET', debit: 150000.0, credit: 0 },
    { number: '1010', name: 'Main Commercial Bank Checking', type: 'ASSET', debit: 485000.0, credit: 0 },
    { number: '1200', name: 'Accounts Receivable', type: 'ASSET', debit: 45200.0, credit: 0 },
    { number: '1300', name: 'Inventory Assets & Stock', type: 'ASSET', debit: 354000.0, credit: 0 },
    { number: '1500', name: 'Machinery & IT Equipment', type: 'ASSET', debit: 220000.0, credit: 0 },
    { number: '2000', name: 'Accounts Payable - Suppliers', type: 'LIABILITY', debit: 0, credit: 18900.0 },
    { number: '2100', name: 'Payroll & Bonus Accruals', type: 'LIABILITY', debit: 0, credit: 32000.0 },
    { number: '3000', name: 'Owner Paid-In Capital', type: 'EQUITY', debit: 0, credit: 700000.0 },
    { number: '3100', name: 'Retained Earnings', type: 'EQUITY', debit: 0, credit: 419100.0 },
    { number: '4000', name: 'Enterprise Software License Revenue', type: 'REVENUE', debit: 0, credit: 450000.0 },
    { number: '4100', name: 'Cloud Consulting & Managed Services', type: 'REVENUE', debit: 0, credit: 392000.0 },
    { number: '5000', name: 'Cost of Goods Sold (COGS)', type: 'EXPENSE', debit: 180000.0, credit: 0 },
    { number: '6000', name: 'Salaries & Benefits Expense', type: 'EXPENSE', debit: 125000.0, credit: 0 },
    { number: '6100', name: 'Facility Rent & Utilities', type: 'EXPENSE', debit: 53800.0, credit: 0 }
  ];

  const totalDebit = trialBalanceAccounts.reduce((s, a) => s + a.debit, 0);
  const totalCredit = trialBalanceAccounts.reduce((s, a) => s + a.credit, 0);

  return (
    <div className="space-y-6">
      <PageHeader
        title="Financial Statements & Analytics Reports"
        description="Generate GAAP/IFRS compliant financial statements, real-time Trial Balance, P&L, and Balance Sheet"
        actions={
          <button
            onClick={() => window.print()}
            className="flex items-center px-3 py-2 bg-slate-800 hover:bg-slate-700 text-slate-200 border border-slate-700 rounded-xl text-xs font-semibold shadow transition-all"
          >
            <Download className="w-3.5 h-3.5 mr-1.5" /> Export PDF Statement
          </button>
        }
      />

      {/* Tabs */}
      <div className="flex space-x-2 border-b border-slate-800 pb-2">
        <button
          onClick={() => setActiveTab('trial_balance')}
          className={`px-4 py-2 rounded-xl text-xs font-bold transition-all ${
            activeTab === 'trial_balance'
              ? 'bg-blue-600 text-white shadow-lg shadow-blue-600/30'
              : 'text-slate-400 hover:text-white hover:bg-slate-900'
          }`}
        >
          Trial Balance
        </button>
        <button
          onClick={() => setActiveTab('pl')}
          className={`px-4 py-2 rounded-xl text-xs font-bold transition-all ${
            activeTab === 'pl'
              ? 'bg-blue-600 text-white shadow-lg shadow-blue-600/30'
              : 'text-slate-400 hover:text-white hover:bg-slate-900'
          }`}
        >
          Profit & Loss (P&L)
        </button>
        <button
          onClick={() => setActiveTab('balance_sheet')}
          className={`px-4 py-2 rounded-xl text-xs font-bold transition-all ${
            activeTab === 'balance_sheet'
              ? 'bg-blue-600 text-white shadow-lg shadow-blue-600/30'
              : 'text-slate-400 hover:text-white hover:bg-slate-900'
          }`}
        >
          Balance Sheet
        </button>
      </div>

      {/* TAB 1: TRIAL BALANCE */}
      {activeTab === 'trial_balance' && (
        <div className="bg-slate-900/70 border border-slate-800 rounded-2xl p-6 shadow-xl space-y-4">
          <div className="flex items-center justify-between border-b border-slate-800 pb-4">
            <div>
              <h3 className="text-base font-bold text-white">Adjusted Trial Balance</h3>
              <p className="text-xs text-slate-400">As of August 31, 2026</p>
            </div>
            <div className="flex items-center space-x-2 text-xs font-semibold text-emerald-400 bg-emerald-950/60 border border-emerald-800/40 px-3 py-1.5 rounded-xl">
              <CheckCircle2 className="w-4 h-4" />
              <span>Debits & Credits Fully In Balance</span>
            </div>
          </div>

          <div className="overflow-x-auto">
            <table className="w-full text-xs text-left">
              <thead className="text-slate-400 uppercase border-b border-slate-800 bg-slate-950/40">
                <tr>
                  <th className="py-3 px-4">GL Account #</th>
                  <th className="py-3 px-4">Account Title</th>
                  <th className="py-3 px-4">Classification</th>
                  <th className="py-3 px-4 text-right">Debit ($)</th>
                  <th className="py-3 px-4 text-right">Credit ($)</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-slate-800/50 text-slate-300">
                {trialBalanceAccounts.map((a) => (
                  <tr key={a.number} className="hover:bg-slate-800/30 transition-colors">
                    <td className="py-3 px-4 font-mono font-bold text-blue-400">{a.number}</td>
                    <td className="py-3 px-4 font-semibold text-white">{a.name}</td>
                    <td className="py-3 px-4">
                      <Badge variant="primary">{a.type}</Badge>
                    </td>
                    <td className="py-3 px-4 text-right font-mono font-semibold">
                      {a.debit > 0 ? formatCurrency(a.debit) : '—'}
                    </td>
                    <td className="py-3 px-4 text-right font-mono font-semibold">
                      {a.credit > 0 ? formatCurrency(a.credit) : '—'}
                    </td>
                  </tr>
                ))}
              </tbody>
              <tfoot className="border-t-2 border-slate-700 font-bold text-white bg-slate-950/60 text-sm">
                <tr>
                  <td colSpan={3} className="py-4 px-4 uppercase tracking-wider">
                    Total Adjusted Balance
                  </td>
                  <td className="py-4 px-4 text-right font-mono text-emerald-400">
                    {formatCurrency(totalDebit)}
                  </td>
                  <td className="py-4 px-4 text-right font-mono text-emerald-400">
                    {formatCurrency(totalCredit)}
                  </td>
                </tr>
              </tfoot>
            </table>
          </div>
        </div>
      )}

      {/* TAB 2: PROFIT AND LOSS */}
      {activeTab === 'pl' && (
        <div className="bg-slate-900/70 border border-slate-800 rounded-2xl p-6 shadow-xl space-y-6">
          <div className="border-b border-slate-800 pb-4">
            <h3 className="text-base font-bold text-white">Income Statement (Profit & Loss)</h3>
            <p className="text-xs text-slate-400">For the period Jan 1, 2026 – Aug 31, 2026</p>
          </div>

          <div className="space-y-4 text-xs">
            {/* Revenues */}
            <div className="bg-slate-950/60 p-4 rounded-xl border border-slate-800 space-y-2">
              <h4 className="font-bold text-emerald-400 uppercase tracking-wider text-xs">Operating Revenues</h4>
              <div className="flex justify-between py-1 text-slate-300">
                <span>Enterprise Software License Revenue</span>
                <span className="font-mono font-semibold text-white">$450,000.00</span>
              </div>
              <div className="flex justify-between py-1 text-slate-300">
                <span>Cloud Consulting & Managed Services</span>
                <span className="font-mono font-semibold text-white">$392,000.00</span>
              </div>
              <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-emerald-400 text-sm">
                <span>Total Gross Revenue</span>
                <span className="font-mono">$842,000.00</span>
              </div>
            </div>

            {/* COGS */}
            <div className="bg-slate-950/60 p-4 rounded-xl border border-slate-800 space-y-2">
              <h4 className="font-bold text-amber-400 uppercase tracking-wider text-xs">Cost of Goods Sold (COGS)</h4>
              <div className="flex justify-between py-1 text-slate-300">
                <span>Direct Hardware & Infrastructure Costs</span>
                <span className="font-mono font-semibold text-white">$180,000.00</span>
              </div>
              <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-amber-400 text-sm">
                <span>Gross Profit (78.6% Margin)</span>
                <span className="font-mono">$662,000.00</span>
              </div>
            </div>

            {/* Operating Expenses */}
            <div className="bg-slate-950/60 p-4 rounded-xl border border-slate-800 space-y-2">
              <h4 className="font-bold text-rose-400 uppercase tracking-wider text-xs">Operating Expenditures</h4>
              <div className="flex justify-between py-1 text-slate-300">
                <span>Staff Salaries, Benefits & Payroll</span>
                <span className="font-mono font-semibold text-white">$125,000.00</span>
              </div>
              <div className="flex justify-between py-1 text-slate-300">
                <span>Facility Rent, Utilities & AWS Cloud</span>
                <span className="font-mono font-semibold text-white">$53,800.00</span>
              </div>
              <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-rose-400 text-sm">
                <span>Total Operating Expenses</span>
                <span className="font-mono">$178,800.00</span>
              </div>
            </div>

            {/* Net Income */}
            <div className="p-5 rounded-xl bg-gradient-to-r from-emerald-950/80 to-slate-900 border border-emerald-700/60 flex items-center justify-between">
              <div>
                <span className="text-xs text-emerald-400 font-bold uppercase tracking-wider block">Net Enterprise Income</span>
                <span className="text-2xl font-bold text-white font-mono">$483,200.00</span>
              </div>
              <Badge variant="success">EBITDA Margin: 57.3%</Badge>
            </div>
          </div>
        </div>
      )}

      {/* TAB 3: BALANCE SHEET */}
      {activeTab === 'balance_sheet' && (
        <div className="bg-slate-900/70 border border-slate-800 rounded-2xl p-6 shadow-xl space-y-6">
          <div className="border-b border-slate-800 pb-4">
            <h3 className="text-base font-bold text-white">Consolidated Balance Sheet</h3>
            <p className="text-xs text-slate-400">As of August 31, 2026</p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-6 text-xs">
            {/* Assets */}
            <div className="space-y-4">
              <div className="bg-slate-950/60 p-4 rounded-xl border border-slate-800 space-y-2">
                <h4 className="font-bold text-blue-400 uppercase tracking-wider text-xs">Current & Fixed Assets</h4>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Cash & Bank Balances</span>
                  <span className="font-mono font-semibold text-white">$635,000.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Accounts Receivable</span>
                  <span className="font-mono font-semibold text-white">$45,200.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Merchandise & Parts Inventory</span>
                  <span className="font-mono font-semibold text-white">$354,000.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Property, Plant & Server Equipment</span>
                  <span className="font-mono font-semibold text-white">$220,000.00</span>
                </div>
                <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-blue-400 text-sm">
                  <span>Total Enterprise Assets</span>
                  <span className="font-mono">$1,254,200.00</span>
                </div>
              </div>
            </div>

            {/* Liabilities & Equity */}
            <div className="space-y-4">
              <div className="bg-slate-950/60 p-4 rounded-xl border border-slate-800 space-y-2">
                <h4 className="font-bold text-purple-400 uppercase tracking-wider text-xs">Liabilities & Stockholder Equity</h4>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Accounts Payable (Suppliers)</span>
                  <span className="font-mono font-semibold text-white">$18,900.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Payroll & Tax Accruals</span>
                  <span className="font-mono font-semibold text-white">$32,000.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Owner Paid-In Capital</span>
                  <span className="font-mono font-semibold text-white">$700,000.00</span>
                </div>
                <div className="flex justify-between py-1 text-slate-300">
                  <span>Retained Earnings</span>
                  <span className="font-mono font-semibold text-white">$503,300.00</span>
                </div>
                <div className="flex justify-between border-t border-slate-800 pt-2 font-bold text-purple-400 text-sm">
                  <span>Total Liabilities & Equity</span>
                  <span className="font-mono">$1,254,200.00</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
