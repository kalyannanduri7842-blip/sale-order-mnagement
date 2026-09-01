import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import {
  Building2,
  ShieldCheck,
  BarChart3,
  Users,
  Package,
  ShoppingCart,
  DollarSign,
  ArrowRight,
  Sparkles,
  CheckCircle2,
  KanbanSquare,
  Globe,
  Layers,
  ChevronRight,
  TrendingUp,
  FileCheck2,
  Lock,
  LogIn
} from 'lucide-react';
import { useAuth } from '../context/AuthContext';

export function LandingPage() {
  const { switchRoleDemo, login } = useAuth();
  const navigate = useNavigate();

  const handleLaunchRoleDemo = (roleKey) => {
    switchRoleDemo(roleKey);
    navigate('/dashboard');
  };

  const handleQuickSignIn = async () => {
    await login('owner@elevitiq.com', 'admin123');
    navigate('/dashboard');
  };

  const departments = [
    {
      id: 'sales',
      title: 'Sales & CRM Funnels',
      desc: 'Customer accounts, opportunity Kanban pipeline, quotations, sales orders, and automated invoicing.',
      icon: TrendingUp,
      color: 'bg-blue-50 text-blue-600 border-blue-200'
    },
    {
      id: 'procurement',
      title: 'Procurement & Vendors',
      desc: 'Supplier directory, purchase requisitions, manager approvals, and automated purchase orders.',
      icon: ShoppingCart,
      color: 'bg-emerald-50 text-emerald-600 border-emerald-200'
    },
    {
      id: 'inventory',
      title: 'Inventory & Warehousing',
      desc: 'Product catalog with SKU tracking, stock movements, multi-warehouse transfers, and reorder alerts.',
      icon: Package,
      color: 'bg-amber-50 text-amber-600 border-amber-200'
    },
    {
      id: 'finance',
      title: 'Finance & General Ledger',
      desc: 'Chart of accounts, double-entry journal entries, expense ledger, and real-time P&L reporting.',
      icon: DollarSign,
      color: 'bg-purple-50 text-purple-600 border-purple-200'
    },
    {
      id: 'hr',
      title: 'Human Resources & Payroll',
      desc: 'Employee directory, attendance time tracking, leave approvals, and automated payroll calculations.',
      icon: Users,
      color: 'bg-sky-50 text-sky-600 border-sky-200'
    },
    {
      id: 'operations',
      title: 'Operations & Asset Lifecycle',
      desc: 'Project milestones, bills of materials (BOM), supply chain logistics, and fixed asset depreciation.',
      icon: Layers,
      color: 'bg-indigo-50 text-indigo-600 border-indigo-200'
    }
  ];

  return (
    <div className="min-h-screen bg-[#f4f6fb] text-slate-900 flex flex-col antialiased">
      {/* Top Navbar */}
      <header className="sticky top-0 z-40 bg-white/95 backdrop-blur-md border-b border-slate-200 px-6 h-16 flex items-center justify-between">
        <Link to="/" className="flex items-center space-x-3">
          <div className="w-9 h-9 rounded-2xl bg-gradient-to-tr from-blue-600 via-indigo-600 to-cyan-400 flex items-center justify-center font-black text-white text-base shadow-sm">
            E
          </div>
          <div>
            <span className="font-black tracking-tight text-slate-900 text-base leading-none">ENTERPRISEPRO</span>
            <span className="text-[10px] font-bold text-blue-600 block uppercase tracking-wider">ERP System</span>
          </div>
        </Link>

        <div className="flex items-center space-x-3">
          <button
            onClick={handleQuickSignIn}
            className="flex items-center gap-1 px-3.5 py-2 rounded-xl text-xs font-bold text-slate-700 hover:text-slate-900 hover:bg-slate-100 transition-colors"
          >
            <LogIn className="w-3.5 h-3.5" />
            <span>Sign In</span>
          </button>
          <Link
            to="/register"
            className="btn-primary text-xs flex items-center gap-1.5 shadow-sm"
          >
            <span>Get Started</span>
            <ArrowRight className="w-3.5 h-3.5" />
          </Link>
        </div>
      </header>

      {/* Hero Section */}
      <section className="px-6 py-12 md:py-20 max-w-6xl mx-auto text-center space-y-6">
        <div className="inline-flex items-center gap-2 px-3.5 py-1.5 rounded-full bg-blue-50 border border-blue-200 text-blue-700 text-xs font-bold shadow-2xs">
          <Sparkles className="w-3.5 h-3.5 text-blue-600" />
          <span>Enterprise Resource Planning • Elevit IQ Tenant Platform</span>
        </div>

        <h1 className="text-4xl sm:text-5xl md:text-6xl font-black text-slate-900 tracking-tight leading-tight max-w-4xl mx-auto">
          One System. <span className="bg-gradient-to-r from-blue-600 via-indigo-600 to-cyan-600 bg-clip-text text-transparent">Every Business Operation.</span>
        </h1>

        <p className="text-sm sm:text-base text-slate-600 max-w-2xl mx-auto font-medium leading-relaxed">
          Unify Sales, Procurement, Multi-Warehouse Inventory, General Ledger Finance, and HR Payroll into a single centralized enterprise ecosystem.
        </p>

        {/* Primary CTA Buttons */}
        <div className="flex flex-wrap items-center justify-center gap-3 pt-2">
          <button
            onClick={handleQuickSignIn}
            className="btn-primary px-6 py-3 text-sm flex items-center gap-2 shadow-md cursor-pointer"
          >
            <span>Launch ERP Workspace</span>
            <ArrowRight className="w-4 h-4" />
          </button>
          <Link
            to="/register"
            className="px-6 py-3 rounded-xl bg-white hover:bg-slate-50 border border-slate-200 text-slate-800 font-bold text-sm shadow-2xs transition-colors"
          >
            Register Organization
          </Link>
        </div>

        {/* One-Click Role Personas Launcher */}
        <div className="mt-10 pt-8 border-t border-slate-200/80 max-w-4xl mx-auto">
          <div className="flex items-center justify-center gap-2 mb-4 text-xs font-bold text-slate-500 uppercase tracking-wider">
            <Lock className="w-3.5 h-3.5 text-blue-600" />
            <span>⚡ Select Role to Enter Dashboard:</span>
          </div>

          <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-6 gap-2.5 text-xs">
            <button
              onClick={() => handleLaunchRoleDemo('SUPER_ADMIN')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">👑 Owner / Admin</span>
              <span className="text-[10px] text-slate-400">Full Master Access</span>
            </button>

            <button
              onClick={() => handleLaunchRoleDemo('SALES_MANAGER')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">📈 Sales Manager</span>
              <span className="text-[10px] text-slate-400">Orders, CRM & Billing</span>
            </button>

            <button
              onClick={() => handleLaunchRoleDemo('INVENTORY_MANAGER')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">📦 Inventory Lead</span>
              <span className="text-[10px] text-slate-400">Stock & Warehouses</span>
            </button>

            <button
              onClick={() => handleLaunchRoleDemo('PROCUREMENT_MANAGER')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">🛒 Procurement</span>
              <span className="text-[10px] text-slate-400">Vendors & PO Orders</span>
            </button>

            <button
              onClick={() => handleLaunchRoleDemo('FINANCE_MANAGER')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">💰 Finance CFO</span>
              <span className="text-[10px] text-slate-400">Accounts & Ledger</span>
            </button>

            <button
              onClick={() => handleLaunchRoleDemo('HR_MANAGER')}
              className="p-3 rounded-2xl bg-white hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-all shadow-2xs group cursor-pointer"
            >
              <span className="font-bold text-slate-900 block group-hover:text-blue-600 text-xs">👥 HR Lead</span>
              <span className="text-[10px] text-slate-400">Employees & Payroll</span>
            </button>
          </div>
        </div>
      </section>

      {/* Departments Grid */}
      <section className="px-6 py-12 max-w-6xl mx-auto w-full space-y-6">
        <div className="text-center space-y-1">
          <h2 className="text-2xl font-black text-slate-900 tracking-tight">Enterprise ERP Functional Modules</h2>
          <p className="text-xs text-slate-500 font-medium">Interconnected workflow modules built for enterprise scale</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5">
          {departments.map((dept) => {
            const Icon = dept.icon;
            return (
              <div key={dept.id} className="enterprise-card p-6 space-y-3 bg-white border border-slate-200 rounded-2xl shadow-sm hover:shadow-md transition-all">
                <div className={`p-3 rounded-2xl border w-fit ${dept.color}`}>
                  <Icon className="w-5 h-5" />
                </div>
                <h3 className="text-base font-bold text-slate-900">{dept.title}</h3>
                <p className="text-xs text-slate-600 leading-relaxed font-medium">{dept.desc}</p>
              </div>
            );
          })}
        </div>
      </section>

      {/* Footer */}
      <footer className="mt-auto border-t border-slate-200 bg-white py-6 px-6 text-center text-xs text-slate-500 font-medium">
        <div className="max-w-6xl mx-auto flex flex-col sm:flex-row items-center justify-between gap-3">
          <span>EnterprisePro ERP System • Elevit IQ Multi-Tenant Engine</span>
          <span className="text-[11px] text-slate-400">© 2026 EnterprisePro Systems Inc. All rights reserved.</span>
        </div>
      </footer>
    </div>
  );
}
