import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Truck, Plus, Trash2, Mail, Phone } from 'lucide-react';

export function VendorDirectory() {
  const { vendors, addVendor, deleteVendor } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    category: 'HARDWARE',
    outstandingPayables: 0,
    address: '',
    country: 'USA'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addVendor(formData);
    addToast('Vendor Registered', `Supplier ${formData.name} added to procurement base.`, 'success');
    setIsModalOpen(false);
    setFormData({
      name: '',
      email: '',
      phone: '',
      category: 'HARDWARE',
      outstandingPayables: 0,
      address: '',
      country: 'USA'
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to delete supplier: ${name}?`)) {
      deleteVendor(id);
      addToast('Vendor Deleted', `${name} has been removed.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Vendor Code',
      accessor: 'vendorCode',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'VEN-NEW'}</span>
    },
    {
      header: 'Supplier / Company',
      accessor: 'name',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Category',
      accessor: 'category',
      render: (val) => <Badge variant="primary">{val}</Badge>
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
      header: 'Rating',
      accessor: 'rating',
      render: (val) => <span className="text-amber-500 font-bold">★ {val || '5.0'}</span>
    },
    {
      header: 'Payables Balance',
      accessor: 'outstandingPayables',
      render: (val, row) => <span className="font-mono font-bold text-rose-600">{formatCurrency(val || row.outstandingPay || 0)}</span>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.name)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Supplier"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Supplier & Vendor Directory"
        description="Manage approved supply vendors, contracts, procurement terms and outstanding payables"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Register Supplier
          </button>
        }
      />

      <DataTable
        title="Approved Vendor Registry"
        columns={columns}
        data={vendors}
        searchPlaceholder="Search vendors by name, category..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Supply Vendor">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Supplier / Corporate Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Cisco Systems Global"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Supply Category</label>
              <select
                value={formData.category}
                onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="HARDWARE">Server & Network Hardware</option>
                <option value="SOFTWARE_LICENSES">Enterprise Software Licenses</option>
                <option value="LOGISTICS">Logistics & Warehousing</option>
                <option value="OFFICE_SUPPLIES">Corporate Supplies</option>
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Contact Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                placeholder="orders@cisco.com"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Phone Number</label>
              <input
                type="text"
                value={formData.phone}
                onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                placeholder="+1-800-555-0199"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Physical Address</label>
            <input
              type="text"
              value={formData.address}
              onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              placeholder="e.g. 170 West Tasman Dr, San Jose, CA"
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
              Register Supplier
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
