import React from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { StatCard } from '../../components/ui/StatCard';
import { Badge } from '../../components/ui/Badge';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { INITIAL_MOCK_DATA } from '../../constants/mockData';
import {
  DollarSign,
  TrendingUp,
  Package,
  Users,
  ShoppingCart,
  PlusCircle,
  CheckCircle2,
  RefreshCw,
  Trash2,
  Layers,
  ArrowRight
} from 'lucide-react';
import {
  AreaChart,
  Area,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
  Legend
} from 'recharts';
import { Link } from 'react-router-dom';

const COLORS = ['#2563eb', '#10b981', '#f59e0b', '#8b5cf6', '#ec4899'];

export function ExecutiveDashboard() {
  const {
    dashboard,
    customers = [],
    salesOrders = [],
    invoices = [],
    products = [],
    employees = [],
    expenses = [],
    auditLogs = [],
    loadDemoData,
    clearAllData
  } = useErpData();
  const { addToast } = useNotification();

  const safeSales = Array.isArray(salesOrders) ? salesOrders : [];
  const safeInvoices = Array.isArray(invoices) ? invoices : [];
  const safeProducts = Array.isArray(products) ? products : [];
  const safeExpenses = Array.isArray(expenses) ? expenses : [];
  const safeEmployees = Array.isArray(employees) ? employees : [];
  const safeCustomers = Array.isArray(customers) ? customers : [];
  const safeLogs = Array.isArray(auditLogs) ? auditLogs : [];

  // Dynamically compute live KPIs
  const totalSalesVal = safeSales.reduce((sum, o) => sum + (parseFloat(o?.grandTotal || o?.totalAmount) || 0), 0);
  const totalInvoicedVal = safeInvoices.reduce((sum, i) => sum + (parseFloat(i?.totalAmount) || 0), 0);
  const totalInventoryVal = safeProducts.reduce((sum, p) => sum + ((p?.stockQuantity || 0) * (p?.unitCost || (p?.sellingPrice || 0) * 0.6)), 0);
  const totalExpensesVal = safeExpenses.reduce((sum, e) => sum + (parseFloat(e?.amount) || 0), 0);
  const lowStockCount = safeProducts.filter(p => Number(p?.stockQuantity || 0) <= Number(p?.reorderLevel || 10)).length;
  const activeStaffCount = safeEmployees.length;

  const handleLoadDemo = () => {
    loadDemoData();
    addToast('Demo Dataset Loaded', 'Populated realistic Elevit IQ Enterprise records across all departments.', 'success');
  };

  const handleClearData = () => {
    if (window.confirm('Are you sure you want to clear all data? You will start with an empty clean slate.')) {
      clearAllData();
      addToast('Data Cleared', 'Workspace cleared. You can now add your own custom records.', 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(val || 0);

  const revenueChartData = (dashboard && Array.isArray(dashboard.monthlyRevenueChart) && dashboard.monthlyRevenueChart.length > 0)
    ? dashboard.monthlyRevenueChart
    : INITIAL_MOCK_DATA.dashboard.monthlyRevenueChart;

  const productCategoryData = (dashboard && Array.isArray(dashboard.salesByProductCategory) && dashboard.salesByProductCategory.length > 0)
    ? dashboard.salesByProductCategory
    : INITIAL_MOCK_DATA.dashboard.salesByProductCategory;

  return (
    <div className="space-y-6 animate-in fade-in">
      {/* Header with Demo Data Controls */}
      <PageHeader
        title="Executive Overview & Business KPIs"
        description="Consolidated real-time operational telemetry across Sales, Procurement, Inventory, Finance, and HR"
        actions={
          <div className="flex flex-wrap items-center gap-2">
            <button
              onClick={handleLoadDemo}
              className="flex items-center px-3 py-2 bg-blue-50 hover:bg-blue-100 text-blue-700 border border-blue-200 rounded-xl text-xs font-bold transition-all shadow-2xs cursor-pointer"
              title="Load demo data"
            >
              <RefreshCw className="w-3.5 h-3.5 mr-1.5 text-blue-600" /> ⚡ Load Demo Data
            </button>

            <button
              onClick={handleClearData}
              className="flex items-center px-3 py-2 bg-white hover:bg-rose-50 text-rose-600 border border-slate-200 hover:border-rose-200 rounded-xl text-xs font-bold transition-all shadow-2xs cursor-pointer"
              title="Clear all data to start fresh"
            >
              <Trash2 className="w-3.5 h-3.5 mr-1.5" /> 🗑️ Clear Workspace
            </button>

            <Link
              to="/sales/orders"
              className="btn-primary flex items-center text-xs shadow-sm cursor-pointer"
            >
              <PlusCircle className="w-3.5 h-3.5 mr-1.5" /> + Sales Order
            </Link>
          </div>
        }
      />

      {/* KPI Cards Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard
          title="Total Invoiced"
          value={formatCurrency(totalInvoicedVal || 142500)}
          change={`${safeInvoices.length} Invoices`}
          isPositive={true}
          icon={DollarSign}
          color="blue"
          subtitle={`Expenses: ${formatCurrency(totalExpensesVal || 58300)}`}
        />
        <StatCard
          title="Sales Volume"
          value={formatCurrency(totalSalesVal || 842000)}
          change={`${safeSales.length} Orders`}
          isPositive={true}
          icon={TrendingUp}
          color="emerald"
          subtitle={`${safeCustomers.length} Active Enterprise Clients`}
        />
        <StatCard
          title="Inventory Assets"
          value={formatCurrency(totalInventoryVal || 354000)}
          change={`${lowStockCount} Low Stock`}
          isPositive={lowStockCount === 0}
          icon={Package}
          color="amber"
          subtitle={`${safeProducts.length} SKU Catalog Items`}
        />
        <StatCard
          title="Workforce Roster"
          value={`${activeStaffCount || 48} Employees`}
          change="100% Operational"
          isPositive={true}
          icon={Users}
          color="purple"
          subtitle="Across 5 Corporate Divisions"
        />
      </div>

      {/* Analytics Charts Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Revenue & Expenses Profit Curve */}
        <div className="lg:col-span-2 enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl">
          <div className="flex items-center justify-between mb-4 pb-2 border-b border-slate-100">
            <div>
              <h3 className="text-sm font-bold text-slate-900 tracking-tight">Revenue vs Operating Expenses (2026)</h3>
              <p className="text-xs text-slate-400 font-medium">Monthly breakdown & gross margins</p>
            </div>
            <Badge variant="success">Positive Cashflow</Badge>
          </div>
          <div className="h-72">
            <ResponsiveContainer width="100%" height="100%">
              <AreaChart data={revenueChartData}>
                <defs>
                  <linearGradient id="colorRev" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#2563eb" stopOpacity={0.25} />
                    <stop offset="95%" stopColor="#2563eb" stopOpacity={0} />
                  </linearGradient>
                  <linearGradient id="colorExp" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#ef4444" stopOpacity={0.15} />
                    <stop offset="95%" stopColor="#ef4444" stopOpacity={0} />
                  </linearGradient>
                </defs>
                <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" vertical={false} />
                <XAxis dataKey="month" stroke="#94a3b8" fontSize={11} />
                <YAxis stroke="#94a3b8" fontSize={11} tickFormatter={(v) => `$${v / 1000}k`} />
                <Tooltip
                  contentStyle={{ backgroundColor: '#ffffff', borderColor: '#e2e8f0', borderRadius: '12px', fontSize: '11px', boxShadow: '0 4px 6px -1px rgba(0,0,0,0.1)' }}
                  formatter={(val) => [formatCurrency(val), '']}
                />
                <Legend wrapperStyle={{ fontSize: '11px', paddingTop: '10px' }} />
                <Area type="monotone" dataKey="revenue" name="Revenue" stroke="#2563eb" strokeWidth={2.5} fillOpacity={1} fill="url(#colorRev)" />
                <Area type="monotone" dataKey="expenses" name="Expenses" stroke="#ef4444" strokeWidth={2} fillOpacity={1} fill="url(#colorExp)" />
              </AreaChart>
            </ResponsiveContainer>
          </div>
        </div>

        {/* Product Category Share */}
        <div className="enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl flex flex-col justify-between">
          <div className="pb-2 border-b border-slate-100">
            <h3 className="text-sm font-bold text-slate-900 tracking-tight">Revenue by Product Line</h3>
            <p className="text-xs text-slate-400 font-medium">Departmental revenue distribution</p>
          </div>
          <div className="h-60 my-auto">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={productCategoryData}
                  cx="50%"
                  cy="50%"
                  innerRadius={50}
                  outerRadius={75}
                  paddingAngle={5}
                  dataKey="value"
                  nameKey="category"
                >
                  {productCategoryData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip
                  contentStyle={{ backgroundColor: '#ffffff', borderColor: '#e2e8f0', borderRadius: '12px', fontSize: '11px' }}
                  formatter={(val) => [`${val}%`, 'Revenue Share']}
                />
              </PieChart>
            </ResponsiveContainer>
          </div>
          <div className="grid grid-cols-2 gap-2 text-[10px] text-slate-500 pt-2 border-t border-slate-100 font-medium">
            {productCategoryData.map((item, idx) => (
              <div key={idx} className="flex items-center space-x-1.5">
                <span className="w-2 h-2 rounded-full" style={{ backgroundColor: COLORS[idx % COLORS.length] }} />
                <span className="truncate font-semibold text-slate-700">{item.category} ({item.value}%)</span>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Lower Section: Live Audit Stream */}
      <div className="enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl">
        <div className="flex items-center justify-between mb-4 pb-2 border-b border-slate-100">
          <div>
            <h3 className="text-sm font-bold text-slate-900 tracking-tight">Recent Operations Activity Stream</h3>
            <p className="text-xs text-slate-400 font-medium">Live immutable audit trail of user actions & CRUD events</p>
          </div>
          <Link to="/system/audit" className="text-xs font-bold text-blue-600 hover:underline">
            View All Audit Logs →
          </Link>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-3">
          {safeLogs.slice(0, 4).map((act) => (
            <div key={act?.id || Math.random()} className="p-3 rounded-xl bg-slate-50 border border-slate-200/80 flex flex-col justify-between">
              <div>
                <div className="flex items-center justify-between mb-1">
                  <Badge variant="primary">{act?.module || 'SYSTEM'}</Badge>
                  <span className="text-[10px] text-slate-400 font-mono">
                    {act?.timestamp ? new Date(act.timestamp).toLocaleTimeString() : '10:00 AM'}
                  </span>
                </div>
                <p className="text-xs font-bold text-slate-900 line-clamp-1">{act?.action || 'ACTION'}</p>
                <p className="text-[11px] text-slate-600 mt-0.5 line-clamp-2">{act?.description || 'System event recorded'}</p>
              </div>
              <div className="text-[10px] text-slate-400 mt-2 font-medium">
                By: <span className="font-bold text-slate-700">{act?.user || 'Elevit Owner'}</span>
              </div>
            </div>
          ))}
          {safeLogs.length === 0 && (
            <div className="col-span-4 py-6 text-center text-slate-400 text-xs">
              No recent operations activity. Perform any add or delete action to start the audit log.
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
