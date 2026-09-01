import React from 'react';
import { Outlet } from 'react-router-dom';
import { TopNavigation } from './TopNavigation';

export function MainLayout() {
  return (
    <div className="min-h-screen bg-[#f4f6fb] text-slate-900 flex flex-col antialiased">
      {/* 2-Tier Horizontal Top Command Header */}
      <TopNavigation />

      {/* Main Full-Width Content Container */}
      <main className="flex-1 w-full max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 sm:py-8 animate-in fade-in duration-200">
        <Outlet />
      </main>

      {/* Footer */}
      <footer className="border-t border-slate-200 bg-white py-4 px-6 text-center text-xs text-slate-500 font-medium">
        <div className="max-w-7xl mx-auto flex flex-col sm:flex-row items-center justify-between gap-2">
          <span>EnterprisePro ERP System • Elevit IQ Tenant</span>
          <span className="text-[11px] text-slate-400">One System. Every Business Operation.</span>
        </div>
      </footer>
    </div>
  );
}
