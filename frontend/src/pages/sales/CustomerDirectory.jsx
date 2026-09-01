import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Contact2, Plus, DollarSign, Building } from 'lucide-react';

export function CustomerDirectory() {
  const [customers, setCustomers] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    company: '',
    email: '',
    phone: '',
    address: '',
    city: '',
    country: 'USA',
    taxNumber: '',
    customerType: 'ENTERPRISE',
    creditStatus: 'EXCELLENT'
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadCustomers();
  }, []);

  const loadCustomers = async () => {
    const data = await api.get('/customers', 'customers');
    setCustomers(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      customerCode: 'CUST-' + (1000 + customers.length + 1),
      totalSpend: 0,
      active: true
    };
    await api.post('/customers', payload, 'customers');
    addToast('Customer Account Created', `Client ${formData.name} added to enterprise CRM.`, 'success');
    setIsModalOpen(false);
    loadCustomers();
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Customer Code',
      accessor: 'customerCode',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Account / Company Name',
      accessor: 'name',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">{row.company} • {row.city}</span>
        </div>
      )
    },
    {
      header: 'Contact Info',
      accessor: 'email',
      render: (val, row) => (
        <div className="text-xs text-slate-300">
          <div>{val}</div>
          <div className="text-[10px] text-slate-500">{row.phone}</div>
        </div>
      )
    },
    {
      header: 'Account Tier',
      accessor: 'customerType',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Credit Rating',
      accessor: 'creditStatus',
      render: (val) => <Badge variant="success">{val}</Badge>
    },
    {
      header: 'Lifetime Spend',
      accessor: 'totalSpend',
      render: (val) => <span className="font-mono font-bold text-emerald-400">{formatCurrency(val)}</span>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Customer Accounts & Client Directory"
        description="Enterprise client relationship management, credit risk scoring, historical spend volume and billing details"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Customer Account
          </button>
        }
      />

      <DataTable
        title="Enterprise Client Directory"
        columns={columns}
        data={customers}
        searchPlaceholder="Search customers..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Enterprise Customer">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Company / Organization</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Apex Health Systems LLC"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Short Brand Name</label>
              <input
                type="text"
                value={formData.company}
                onChange={(e) => setFormData({ ...formData, company: e.target.value })}
                placeholder="Apex Health"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Billing Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Contact Phone</label>
              <input
                type="text"
                value={formData.phone}
                onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Account Tier</label>
              <select
                value={formData.customerType}
                onChange={(e) => setFormData({ ...formData, customerType: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="ENTERPRISE">Enterprise Account</option>
                <option value="MID_MARKET">Mid-Market Corporate</option>
                <option value="SMB">Small & Medium Business</option>
                <option value="GOVERNMENT">Government & Public Sector</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Credit Assessment</label>
              <select
                value={formData.creditStatus}
                onChange={(e) => setFormData({ ...formData, creditStatus: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="EXCELLENT">Excellent (Unlimited Credit)</option>
                <option value="GOOD">Good ($100k Limit)</option>
                <option value="FAIR">Fair ($25k Limit)</option>
                <option value="PREPAID_ONLY">Prepayment Required Only</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Headquarters Address</label>
            <input
              type="text"
              value={formData.address}
              onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              placeholder="e.g. 450 Medical Center Dr, Boston, MA"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30"
            >
              Save Customer Account
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
