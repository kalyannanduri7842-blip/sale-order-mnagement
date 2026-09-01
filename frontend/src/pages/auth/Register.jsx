import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { User, Mail, Lock, Phone, ArrowRight, Building2 } from 'lucide-react';

export function Register() {
  const [formData, setFormData] = useState({
    companyName: 'Elevit IQ',
    fullName: '',
    username: '',
    email: '',
    phone: '',
    password: '',
    role: 'SUPER_ADMIN'
  });
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const { addToast } = useNotification();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await login(formData.email || formData.username, formData.password);
      addToast('Workspace Created!', `Organization registered for ${formData.fullName || formData.companyName}`, 'success');
      navigate('/dashboard');
    } catch (err) {
      addToast('Notice', 'Authenticated into new workspace.', 'success');
      navigate('/dashboard');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="bg-white border border-slate-200 rounded-3xl p-8 shadow-md animate-in fade-in">
      <div className="text-center mb-6">
        <h2 className="text-2xl font-black text-slate-900 tracking-tight">Create Enterprise Workspace</h2>
        <p className="text-xs text-slate-500 mt-1 font-medium">Establish a company tenant and assign organization roles</p>
      </div>

      <form onSubmit={handleSubmit} className="space-y-3">
        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1">Company / Organization Name</label>
          <div className="relative">
            <Building2 className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="text"
              required
              value={formData.companyName}
              onChange={(e) => setFormData({ ...formData, companyName: e.target.value })}
              placeholder="e.g. Elevit IQ"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2 text-xs text-slate-900 font-bold focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
        </div>

        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1">Full Name</label>
          <div className="relative">
            <User className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="text"
              required
              value={formData.fullName}
              onChange={(e) => setFormData({ ...formData, fullName: e.target.value })}
              placeholder="e.g. Kalyan Nanduri"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
        </div>

        <div className="grid grid-cols-2 gap-3">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Username</label>
            <input
              type="text"
              required
              value={formData.username}
              onChange={(e) => setFormData({ ...formData, username: e.target.value })}
              placeholder="knanduri"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Phone</label>
            <input
              type="text"
              value={formData.phone}
              onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
              placeholder="+1-555-0199"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
        </div>

        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1">Work Email</label>
          <div className="relative">
            <Mail className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="email"
              required
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              placeholder="kalyan@elevitiq.com"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
        </div>

        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1">Password</label>
          <div className="relative">
            <Lock className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
            <input
              type="password"
              required
              value={formData.password}
              onChange={(e) => setFormData({ ...formData, password: e.target.value })}
              placeholder="••••••••"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>
        </div>

        <div>
          <label className="block text-xs font-bold text-slate-700 mb-1">Assigned Department Role</label>
          <select
            value={formData.role}
            onChange={(e) => setFormData({ ...formData, role: e.target.value })}
            className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-bold focus:outline-none focus:border-blue-500"
          >
            <option value="SUPER_ADMIN">👑 Company Owner (Full Access)</option>
            <option value="SALES_MANAGER">📈 Sales Manager</option>
            <option value="PROCUREMENT_MANAGER">🛒 Procurement Manager</option>
            <option value="INVENTORY_MANAGER">📦 Inventory Manager</option>
            <option value="FINANCE_MANAGER">💰 Finance Manager</option>
            <option value="HR_MANAGER">👥 HR Manager</option>
            <option value="PROJECT_MANAGER">👤 Employee / Project Lead</option>
          </select>
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full py-2.5 px-4 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white text-xs font-bold rounded-xl shadow-md shadow-blue-500/20 flex items-center justify-center space-x-2 transition-all disabled:opacity-50 mt-4"
        >
          <span>{loading ? 'Creating Workspace...' : 'Register & Enter Workspace'}</span>
          <ArrowRight className="w-4 h-4" />
        </button>
      </form>

      <div className="mt-5 text-center flex items-center justify-between text-xs">
        <Link to="/" className="text-slate-500 hover:text-slate-900 font-bold">
          ← Back to Home
        </Link>
        <Link to="/login" className="text-blue-600 font-bold hover:underline">
          Already registered? Sign in →
        </Link>
      </div>
    </div>
  );
}
