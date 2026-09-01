import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { StatCard } from '../../components/ui/StatCard';
import { Badge } from '../../components/ui/Badge';
import { api } from '../../services/api';
import {
  DollarSign,
  TrendingUp,
  Package,
  Users,
  Building2,
  FolderKanban,
  ShoppingCart,
  PlusCircle,
  FileCheck,
  CheckCircle2,
  Clock,
  AlertTriangle,
  Receipt,
  Truck
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
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadData() {
      const summary = await api.get('/dashboard/summary', 'dashboard');
      setData(summary);
      setLoading(false);
    }
    loadData();
  }, []);

  if (loading || !data) {
    return <div className="p-12 text-center text-slate-400 text-xs">Loading Executive Dashboard KPIs...</div>;
  }

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(val || 0);

  return (
    <div className="space-y-6">
      {/* Header */}
      <PageHeader
        title="Executive Overview & Business KPIs"
        description="Consolidated real-time metrics across all business operations, inventory, and financial ledgers"
        actions={
          <div className="flex items-center space-x-2">
            <Link
              to="/sales/orders"
              className="flex items-center px-3.5 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-xl text-xs font-bold shadow-sm transition-all"
            >
              <PlusCircle className="w-3.5 h-3.5 mr-1.5" /> + Sales Order
            </Link>
            <Link
              to="/procurement/orders"
              className="flex items-center px-3.5 py-2 bg-white hover:bg-slate-50 text-slate-700 border border-slate-200 rounded-xl text-xs font-bold transition-all shadow-2xs"
            >
              <ShoppingCart className="w-3.5 h-3.5 mr-1.5 text-blue-600" /> + Purchase Order
            </Link>
          </div>
        }
      />

      {/* KPI Cards Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <StatCard
          title="Monthly Revenue"
          value={formatCurrency(data.monthlyRevenue)}
          change="+18.4% vs last mo"
          isPositive={true}
          icon={DollarSign}
          color="blue"
          subtitle={`Net Profit: ${formatCurrency(data.netProfit)}`}
        />
        <StatCard
          title="Total Sales Volume"
          value={formatCurrency(data.totalSales)}
          change="+12.2% YTD"
          isPositive={true}
          icon={TrendingUp}
          color="emerald"
          subtitle={`${data.pendingOrders || 34} orders in fulfillment`}
        />
        <StatCard
          title="Inventory Valuation"
          value={formatCurrency(data.inventoryValuation)}
          change={`${data.lowStockCount || 12} Low Stock Alerts`}
          isPositive={data.lowStockCount === 0}
          icon={Package}
          color="amber"
          subtitle="Across 3 Central Warehouses"
        />
        <StatCard
          title="Active Workforce"
          value={`${data.activeEmployees || 186} Employees`}
          change="98% Attendance"
          isPositive={true}
          icon={Users}
          color="purple"
          subtitle={`${data.pendingLeaves || 4} pending leave approvals`}
        />
      </div>

      {/* Analytics Charts Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Revenue & Expenses Profit Curve */}
        <div className="lg:col-span-2 enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl">
          <div className="flex items-center justify-between mb-4 pb-2 border-b border-slate-100">
            <div>
              <h3 className="text-sm font-bold text-slate-900 tracking-tight">Revenue vs Operating Expenses (2026)</h3>
              <p className="text-xs text-slate-400">Monthly breakdown and gross cashflow margins</p>
            </div>
            <Badge variant="success">Positive Margins</Badge>
          </div>
          <div className="h-72">
            <ResponsiveContainer width="100%" height="100%">
              <AreaChart data={data.monthlyRevenueChart || []}>
                <defs>
                  <linearGradient id="colorRev" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#2563eb" stopOpacity={0.3} />
                    <stop offset="95%" stopColor="#2563eb" stopOpacity={0} />
                  </linearGradient>
                  <linearGradient id="colorExp" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#ef4444" stopOpacity={0.2} />
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
            <p className="text-xs text-slate-400">Share of revenue distribution</p>
          </div>
          <div className="h-60 my-auto">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={data.salesByProductCategory || []}
                  cx="50%"
                  cy="50%"
                  innerRadius={50}
                  outerRadius={75}
                  paddingAngle={5}
                  dataKey="value"
                  nameKey="category"
                >
                  {(data.salesByProductCategory || []).map((entry, index) => (
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
          <div className="grid grid-cols-2 gap-2 text-[10px] text-slate-500 pt-2 border-t border-slate-100">
            {(data.salesByProductCategory || []).map((item, idx) => (
              <div key={idx} className="flex items-center space-x-1.5">
                <span className="w-2 h-2 rounded-full" style={{ backgroundColor: COLORS[idx % COLORS.length] }} />
                <span className="truncate font-semibold">{item.category} ({item.value}%)</span>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Lower Row: Department Headcount & Live Audit Stream */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Department Distribution Bar Chart */}
        <div className="enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl">
          <h3 className="text-sm font-bold text-slate-900 tracking-tight mb-0.5">Department Workforce Allocation</h3>
          <p className="text-xs text-slate-400 mb-4">Active headcount per operational branch</p>
          <div className="h-60">
            <ResponsiveContainer width="100%" height="100%">
              <BarChart data={data.departmentEmployeeDistribution || []} layout="vertical">
                <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" vertical={false} />
                <XAxis type="number" stroke="#94a3b8" fontSize={11} />
                <YAxis dataKey="name" type="category" stroke="#94a3b8" fontSize={10} width={120} />
                <Tooltip
                  contentStyle={{ backgroundColor: '#ffffff', borderColor: '#e2e8f0', borderRadius: '12px', fontSize: '11px' }}
                />
                <Bar dataKey="employees" fill="#4f46e5" radius={[0, 8, 8, 0]} name="Headcount" />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </div>

        {/* Live Enterprise Activity Stream */}
        <div className="enterprise-card p-5 bg-white border border-slate-200 shadow-sm rounded-2xl flex flex-col justify-between">
          <div>
            <div className="flex items-center justify-between mb-4 pb-2 border-b border-slate-100">
              <div>
                <h3 className="text-sm font-bold text-slate-900 tracking-tight">Recent Operations Activity</h3>
                <p className="text-xs text-slate-400">Live immutable ledger events & workflow triggers</p>
              </div>
              <Link to="/system/audit" className="text-xs font-bold text-blue-600 hover:underline">
                View Audit Trail →
              </Link>
            </div>

            <div className="space-y-2.5">
              {(data.recentActivities || []).map((act) => (
                <div key={act.id} className="p-3 rounded-xl bg-slate-50 border border-slate-200/80 flex items-start space-x-3">
                  <div className="p-2 rounded-lg bg-blue-50 border border-blue-200 text-blue-600 flex-shrink-0 mt-0.5">
                    <CheckCircle2 className="w-3.5 h-3.5" />
                  </div>
                  <div className="flex-1 min-w-0">
                    <div className="flex items-center justify-between">
                      <span className="text-xs font-bold text-slate-900 truncate">{act.action}</span>
                      <Badge variant="primary">{act.module}</Badge>
                    </div>
                    <p className="text-[11px] text-slate-600 mt-0.5">{act.description}</p>
                    <div className="flex items-center space-x-2 text-[10px] text-slate-400 mt-1">
                      <span>By: <strong>{act.user}</strong></span>
                      <span>•</span>
                      <span>{new Date(act.timestamp).toLocaleTimeString()}</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
