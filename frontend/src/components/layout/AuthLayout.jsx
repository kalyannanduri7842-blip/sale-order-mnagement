import React from 'react';
import { Outlet, Link } from 'react-router-dom';
import { ShieldCheck, BarChart3, Users2, Database, Building2, Layers } from 'lucide-react';

export function AuthLayout() {
  return (
    <div className="min-h-screen bg-slate-50 flex flex-col md:flex-row">
      {/* Left Branding Showcase */}
      <div className="hidden md:flex md:w-1/2 bg-white border-r border-slate-200 p-12 flex-col justify-between relative overflow-hidden">
        <div>
          <div className="flex items-center space-x-3">
            <div className="w-10 h-10 rounded-2xl bg-blue-600 flex items-center justify-center font-black text-white text-xl shadow-md">
              E
            </div>
            <div>
              <span className="font-black tracking-tight text-slate-900 text-lg block">ENTERPRISEPRO</span>
              <span className="text-xs font-bold tracking-wider text-blue-600 uppercase block -mt-1">
                One System. Every Business Operation.
              </span>
            </div>
          </div>

          <div className="mt-16 space-y-5">
            <h2 className="text-3xl font-black text-slate-900 tracking-tight leading-tight">
              Enterprise Resource Planning & Complete Business Operations
            </h2>
            <p className="text-sm text-slate-600 leading-relaxed max-w-md font-medium">
              Seamlessly unify Sales, Procurement, Inventory, Finance, and Human Resources in one single, high-performance platform.
            </p>
          </div>
        </div>

        {/* Highlight Grid */}
        <div className="grid grid-cols-2 gap-4">
          <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200 shadow-2xs">
            <ShieldCheck className="w-5 h-5 text-blue-600 mb-2" />
            <h4 className="text-xs font-bold text-slate-900">Multi-Tenant RBAC</h4>
            <p className="text-[11px] text-slate-500 mt-0.5">Role-based departmental access</p>
          </div>

          <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200 shadow-2xs">
            <BarChart3 className="w-5 h-5 text-emerald-600 mb-2" />
            <h4 className="text-xs font-bold text-slate-900">Financial Ledger</h4>
            <p className="text-[11px] text-slate-500 mt-0.5">Automated invoices & reconciliation</p>
          </div>

          <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200 shadow-2xs">
            <Users2 className="w-5 h-5 text-purple-600 mb-2" />
            <h4 className="text-xs font-bold text-slate-900">HR & Workforce</h4>
            <p className="text-[11px] text-slate-500 mt-0.5">Automated payroll & leaves</p>
          </div>

          <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200 shadow-2xs">
            <Database className="w-5 h-5 text-indigo-600 mb-2" />
            <h4 className="text-xs font-bold text-slate-900">Inventory & Supply</h4>
            <p className="text-[11px] text-slate-500 mt-0.5">Multi-warehouse replenishment</p>
          </div>
        </div>

        <div className="text-xs text-slate-400 font-semibold">
          © 2026 ENTERPRISEPRO ERP System. Elevit IQ Workspace.
        </div>
      </div>

      {/* Right Auth Form */}
      <div className="flex-1 flex items-center justify-center p-6 md:p-12 bg-slate-50">
        <div className="w-full max-w-md">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
