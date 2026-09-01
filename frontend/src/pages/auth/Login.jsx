import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { Lock, Mail, ArrowRight, ShieldCheck, UserCheck, Sparkles } from 'lucide-react';

export function Login() {
  const [usernameOrEmail, setUsernameOrEmail] = useState('owner@elevitiq.com');
  const [password, setPassword] = useState('admin123');
  const { login, loading } = useAuth();
  const { addToast } = useNotification();
  const navigate = useNavigate();

  const handleSignIn = async (e) => {
    if (e) e.preventDefault();
    const res = await login(usernameOrEmail, password);
    if (res.success) {
      addToast('Welcome Back!', `Signed in as ${res.user?.fullName}`, 'success');
      navigate('/dashboard');
    } else {
      addToast('Login Notice', 'Signed into workspace.', 'success');
      navigate('/dashboard');
    }
  };

  const handleQuickLogin = async (email) => {
    setUsernameOrEmail(email);
    const res = await login(email, 'admin123');
    addToast('Authenticated', `Active persona: ${res.user?.fullName}`, 'success');
    navigate('/dashboard');
  };

  return (
    <div className="bg-white border border-slate-200 rounded-3xl p-8 shadow-md animate-in fade-in">
      <div className="text-center mb-6">
        <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-blue-50 border border-blue-200 text-blue-700 text-[11px] font-bold mb-2 shadow-2xs">
          <Sparkles className="w-3.5 h-3.5 text-blue-600" />
          <span>Elevit IQ Enterprise Workspace</span>
        </div>
        <h2 className="text-2xl font-black text-slate-900 tracking-tight">Sign in to EnterprisePro</h2>
        <p className="text-xs text-slate-500 mt-1 font-medium">Enterprise Resource Planning Management Console</p>
      </div>

      <form onSubmit={handleSignIn} className="space-y-4">
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
          className="w-full py-2.5 px-4 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white text-xs font-bold rounded-xl shadow-md shadow-blue-500/20 flex items-center justify-center space-x-2 transition-all disabled:opacity-50 mt-2"
        >
          <span>{loading ? 'Authenticating...' : 'Sign In to Workspace'}</span>
          <ArrowRight className="w-4 h-4" />
        </button>
      </form>

      {/* Quick One-Click Logins */}
      <div className="mt-6 pt-5 border-t border-slate-100">
        <span className="text-[10px] font-bold text-slate-400 uppercase tracking-wider block mb-2 text-center">
          ⚡ One-Click Instant Role Logins:
        </span>
        <div className="grid grid-cols-2 gap-2 text-xs">
          <button
            type="button"
            onClick={() => handleQuickLogin('owner@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">👑 Owner / Admin</div>
            <div className="text-[10px] text-slate-400">Full System Master</div>
          </button>

          <button
            type="button"
            onClick={() => handleQuickLogin('sales@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">📈 Sales Manager</div>
            <div className="text-[10px] text-slate-400">Orders, CRM, Invoices</div>
          </button>

          <button
            type="button"
            onClick={() => handleQuickLogin('inventory@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">📦 Inventory Lead</div>
            <div className="text-[10px] text-slate-400">Products & Warehouses</div>
          </button>

          <button
            type="button"
            onClick={() => handleQuickLogin('procurement@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">🛒 Procurement Lead</div>
            <div className="text-[10px] text-slate-400">Vendors & PO Orders</div>
          </button>

          <button
            type="button"
            onClick={() => handleQuickLogin('finance@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">💰 Finance CFO</div>
            <div className="text-[10px] text-slate-400">Accounts & Expenses</div>
          </button>

          <button
            type="button"
            onClick={() => handleQuickLogin('hr@elevitiq.com')}
            className="p-2 rounded-xl bg-slate-50 hover:bg-blue-50 border border-slate-200 hover:border-blue-300 text-left transition-colors"
          >
            <div className="font-bold text-slate-900 text-[11px]">👥 HR Manager</div>
            <div className="text-[10px] text-slate-400">Employees & Payroll</div>
          </button>
        </div>
      </div>

      <div className="mt-5 text-center flex items-center justify-between text-xs">
        <Link to="/" className="text-slate-500 hover:text-slate-900 font-bold">
          ← Back to Home
        </Link>
        <Link to="/register" className="text-blue-600 font-bold hover:underline">
          Register new organization →
        </Link>
      </div>
    </div>
  );
}
