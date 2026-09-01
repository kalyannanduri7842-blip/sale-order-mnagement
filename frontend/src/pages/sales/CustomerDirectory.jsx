import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Contact2, Plus, Trash2, Building, Mail, Phone } from 'lucide-react';

export function CustomerDirectory() {
  const { customers, addCustomer, deleteCustomer } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    company: '',
    email: '',
    phone: '',
    address: '',
    city: '',
    country: 'USA',
    customerType: 'ENTERPRISE',
    creditStatus: 'EXCELLENT'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addCustomer(formData);
    addToast('Customer Created', `Customer ${formData.name} added successfully.`, 'success');
    setIsModalOpen(false);
    setFormData({
      name: '',
      company: '',
      email: '',
      phone: '',
      address: '',
      city: '',
      country: 'USA',
      customerType: 'ENTERPRISE',
      creditStatus: 'EXCELLENT'
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to delete customer: ${name}?`)) {
      deleteCustomer(id);
      addToast('Customer Deleted', `${name} has been removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Customer Code',
      accessor: 'customerCode',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'CUST-NEW'}</span>
    },
    {
      header: 'Account / Company Name',
      accessor: 'name',
      render: (val, row) => (
        <div>
          <span className="font-bold text-slate-900 block">{val}</span>
          <span className="text-[10px] text-slate-500 font-medium">{row.company || val} • {row.city || 'USA'}</span>
        </div>
      )
    },
    {
      header: 'Contact Info',
      accessor: 'email',
      render: (val, row) => (
        <div className="text-xs text-slate-700">
          <div>{val}</div>
          <div className="text-[10px] text-slate-400 font-medium">{row.phone || 'N/A'}</div>
        </div>
      )
    },
    {
      header: 'Tier',
      accessor: 'customerType',
      render: (val) => <Badge variant="primary">{val || 'ENTERPRISE'}</Badge>
    },
    {
      header: 'Credit Rating',
      accessor: 'creditStatus',
      render: (val) => <Badge variant="success">{val || 'EXCELLENT'}</Badge>
    },
    {
      header: 'Total Spend',
      accessor: 'totalSpend',
      render: (val) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val)}</span>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={(e) => {
            e.stopPropagation();
            handleDelete(id, row.name);
          }}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Customer"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Customer Accounts & CRM Directory"
        description="Enterprise client accounts, credit limits, contact directory and historical spend"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Customer Account
          </button>
        }
      />

      <DataTable
        title="Enterprise Client Directory"
        columns={columns}
        data={customers}
        searchPlaceholder="Search customers by name, code, email..."
      />

      {/* Add Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Add Customer Account">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Company / Client Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Acme Global Logistics"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Short Brand Name</label>
              <input
                type="text"
                value={formData.company}
                onChange={(e) => setFormData({ ...formData, company: e.target.value })}
                placeholder="Acme Global"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Billing Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                placeholder="billing@acmeglobal.com"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Contact Phone</label>
              <input
                type="text"
                value={formData.phone}
                onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                placeholder="+1-555-0182"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Account Tier</label>
              <select
                value={formData.customerType}
                onChange={(e) => setFormData({ ...formData, customerType: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="ENTERPRISE">Enterprise Account</option>
                <option value="MID_MARKET">Mid-Market Corporate</option>
                <option value="SMB">Small & Medium Business</option>
                <option value="GOVERNMENT">Government & Public Sector</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Credit Assessment</label>
              <select
                value={formData.creditStatus}
                onChange={(e) => setFormData({ ...formData, creditStatus: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="EXCELLENT">Excellent (Unlimited Credit)</option>
                <option value="GOOD">Good ($100k Limit)</option>
                <option value="FAIR">Fair ($25k Limit)</option>
                <option value="PREPAID_ONLY">Prepayment Required</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Office Address</label>
            <input
              type="text"
              value={formData.address}
              onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              placeholder="e.g. 100 Financial Center Blvd, New York, NY"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>

          <div className="pt-4 border-t border-slate-100 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 rounded-xl text-xs font-bold"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="btn-primary text-xs"
            >
              Save Customer Account
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
