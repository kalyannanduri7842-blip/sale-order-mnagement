import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Settings, Save, Shield, Globe, Landmark, BellRing, Database } from 'lucide-react';

export function SystemSettings() {
  const [settings, setSettings] = useState({
    company_name: 'ENTERPRISEPRO ERP Systems Inc',
    system_currency: 'USD ($)',
    tax_default_rate: '8.25',
    fiscal_year_start: '01-01',
    security_mfa_required: 'true',
    low_stock_threshold: '10',
    automatic_po_generation: 'true',
    system_backup_frequency: 'DAILY'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addToast('Configuration Saved', 'System enterprise parameters updated successfully.', 'success');
  };

  return (
    <div className="space-y-6">
      <PageHeader
        title="Enterprise System Configuration & Rules"
        description="Global parameters, taxation defaults, fiscal reporting calendars and multi-factor security policies"
      />

      <form onSubmit={handleSave} className="space-y-6">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* General Company Settings */}
          <div className="bg-slate-900/70 border border-slate-800 rounded-3xl p-6 shadow-xl backdrop-blur-md space-y-4">
            <div className="flex items-center space-x-3 pb-3 border-b border-slate-800">
              <Globe className="w-5 h-5 text-blue-400" />
              <h3 className="text-sm font-bold text-white uppercase tracking-wider">Organization Profile</h3>
            </div>

            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Company Registered Name</label>
              <input
                type="text"
                value={settings.company_name}
                onChange={(e) => setSettings({ ...settings, company_name: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-semibold text-slate-300 mb-1">Base Currency</label>
                <input
                  type="text"
                  value={settings.system_currency}
                  onChange={(e) => setSettings({ ...settings, system_currency: e.target.value })}
                  className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
                />
              </div>
              <div>
                <label className="block text-xs font-semibold text-slate-300 mb-1">Default Sales Tax Rate (%)</label>
                <input
                  type="text"
                  value={settings.tax_default_rate}
                  onChange={(e) => setSettings({ ...settings, tax_default_rate: e.target.value })}
                  className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
                />
              </div>
            </div>
          </div>

          {/* Security & Compliance Settings */}
          <div className="bg-slate-900/70 border border-slate-800 rounded-3xl p-6 shadow-xl backdrop-blur-md space-y-4">
            <div className="flex items-center space-x-3 pb-3 border-b border-slate-800">
              <Shield className="w-5 h-5 text-emerald-400" />
              <h3 className="text-sm font-bold text-white uppercase tracking-wider">Security & Authentication</h3>
            </div>

            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Enforce Multi-Factor Authentication (MFA)</label>
              <select
                value={settings.security_mfa_required}
                onChange={(e) => setSettings({ ...settings, security_mfa_required: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="true">Enforced for all Manager & Admin roles</option>
                <option value="false">Optional for standard employees</option>
              </select>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-semibold text-slate-300 mb-1">Session Timeout</label>
                <select className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500">
                  <option value="60">60 Minutes</option>
                  <option value="120">2 Hours</option>
                  <option value="480">8 Hours</option>
                </select>
              </div>
              <div>
                <label className="block text-xs font-semibold text-slate-300 mb-1">Backup Frequency</label>
                <select
                  value={settings.system_backup_frequency}
                  onChange={(e) => setSettings({ ...settings, system_backup_frequency: e.target.value })}
                  className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
                >
                  <option value="HOURLY">Hourly Snapshots</option>
                  <option value="DAILY">Daily Automated Backup</option>
                  <option value="WEEKLY">Weekly Full Dump</option>
                </select>
              </div>
            </div>
          </div>
        </div>

        <div className="flex justify-end">
          <button
            type="submit"
            className="flex items-center px-6 py-3 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white rounded-xl text-xs font-bold shadow-lg shadow-blue-600/30 transition-all transform hover:scale-105"
          >
            <Save className="w-4 h-4 mr-2" /> Save Global Configuration
          </button>
        </div>
      </form>
    </div>
  );
}
