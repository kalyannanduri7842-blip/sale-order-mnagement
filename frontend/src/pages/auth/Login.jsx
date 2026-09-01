import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { Lock, Mail, ArrowRight, ShieldCheck, UserCheck } from 'lucide-react';

export function Login() {
  const [usernameOrEmail, setUsernameOrEmail] = useState('owner@elevitiq.com');
  const [password, setPassword] = useState('admin123');
  const { login, loading } = useAuth();
  const { addToast } = useNotification();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await login(usernameOrEmail, password);
    if (res.success) {
      addToast('Welcome back!', 'Authenticated into EnterprisePro ERP', 'success');
      navigate('/dashboard');
    } else {
      addToast('Login Failed', res.error || 'Invalid credentials', 'error');
    }
  };

  const setDemoCredentials = (user, pass) => {
    setUsernameOrEmail(user);
    setPassword(pass);
  };

  return (
    <div className="bg-white border border-slate-200 rounded-3xl p-8 shadow-sm">
      <div className="text-center mb-6">
        <h2 className="text-2xl font-black text-slate-900 tracking-tight">Sign in to EnterprisePro</h2>
        <p className="text-xs text-slate-500 mt-1 font-medium">Enterprise Resource Planning Management Console</p>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1.5">Work Email or Username</label>
          <div className="relative">
            <Mail className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="text"
              required
              value={usernameOrEmail}
              onChange={(e) => setUsernameOrEmail(e.target.value)}
              placeholder="e.g. owner@elevitiq.com"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2.5 text-xs text-slate-900 placeholder-slate-400 focus:outline-none focus:border-blue-500 focus:bg-white transition-colors font-medium"
            />
          </div>
        </div>

        <div>
          <div className="flex items-center justify-between mb-1.5">
            <label className="block text-xs font-bold text-slate-700">Password</label>
            <span className="text-[11px] font-bold text-blue-600 hover:underline cursor-pointer">Forgot password?</span>
          </div>
          <div className="relative">
            <Lock className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2.5 text-xs text-slate-900 placeholder-slate-400 focus:outline-none focus:border-blue-500 focus:bg-white transition-colors font-medium"
            />
          </div>
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full py-2.5 px-4 bg-blue-600 hover:bg-blue-700 text-white text-xs font-bold rounded-xl shadow-sm flex items-center justify-center space-x-2 transition-all disabled:opacity-50 mt-2"
        >
          <span>{loading ? 'Authenticating...' : 'Sign In to Workspace'}</span>
          <ArrowRight className="w-4 h-4" />
        </button>
      </form>

      {/* Quick Demo Logins */}
      <div className="mt-6 pt-5 border-t border-slate-100">
        <span className="text-[10px] font-bold text-slate-400 uppercase tracking-wider block mb-2 text-center">
          ⚡ One-Click Department Logins:
        </span>
        <div className="grid grid-cols-2 gap-1.5 text-xs">
          <button
            type="button"
            onClick={() => setDemoCredentials('owner@elevitiq.com', 'admin123')}
            className="px-2.5 py-1.5 rounded-xl bg-slate-50 hover:bg-slate-100 border border-slate-200 text-left transition-colors"
          >
            <div className="font-bold text-slate-800 text-[11px]">👑 Owner / Admin</div>
            <div className="text-[9px] text-slate-400">Full System Access</div>
          </button>
          <button
            type="button"
            onClick={() => setDemoCredentials('sales@elevitiq.com', 'admin123')}
            className="px-2.5 py-1.5 rounded-xl bg-slate-50 hover:bg-slate-100 border border-slate-200 text-left transition-colors"
          >
            <div className="font-bold text-slate-800 text-[11px]">📈 Sales Manager</div>
            <div className="text-[9px] text-slate-400">Orders, CRM, Invoices</div>
          </button>
          <button
            type="button"
            onClick={() => setDemoCredentials('inventory@elevitiq.com', 'admin123')}
            className="px-2.5 py-1.5 rounded-xl bg-slate-50 hover:bg-slate-100 border border-slate-200 text-left transition-colors"
          >
            <div className="font-bold text-slate-800 text-[11px]">📦 Inventory Lead</div>
            <div className="text-[9px] text-slate-400">Products, Stock, Warehouses</div>
          </button>
          <button
            type="button"
            onClick={() => setDemoCredentials('finance@elevitiq.com', 'admin123')}
            className="px-2.5 py-1.5 rounded-xl bg-slate-50 hover:bg-slate-100 border border-slate-200 text-left transition-colors"
          >
            <div className="font-bold text-slate-800 text-[11px]">💰 Finance CFO</div>
            <div className="text-[9px] text-slate-400">Accounts, Ledgers, Reports</div>
          </button>
        </div>
      </div>

      <div className="mt-5 text-center">
        <Link to="/register" className="text-xs text-blue-600 font-bold hover:underline">
          Create new company workspace →
        </Link>
      </div>
    </div>
  );
}
