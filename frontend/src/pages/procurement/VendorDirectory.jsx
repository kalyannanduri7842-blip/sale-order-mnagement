import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Truck, Plus, Star, Phone, Mail, MapPin } from 'lucide-react';

export function VendorDirectory() {
  const [vendors, setVendors] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    companyName: '',
    contactPerson: '',
    email: '',
    phone: '',
    address: '',
    city: '',
    country: 'USA',
    paymentTerms: 'NET_30',
    rating: 5.0
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadVendors();
  }, []);

  const loadVendors = async () => {
    const data = await api.get('/vendors', 'vendors');
    setVendors(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      vendorCode: 'VEN-' + (1000 + vendors.length + 1),
      active: true
    };
    await api.post('/vendors', payload, 'vendors');
    addToast('Vendor Added', `Supplier ${formData.companyName} registered.`, 'success');
    setIsModalOpen(false);
    loadVendors();
  };

  const columns = [
    {
      header: 'Vendor Code',
      accessor: 'vendorCode',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Supplier Company',
      accessor: 'companyName',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">Contact: {row.contactPerson}</span>
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
      header: 'Payment Terms',
      accessor: 'paymentTerms',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Quality Rating',
      accessor: 'rating',
      render: (val) => (
        <div className="flex items-center space-x-1 text-amber-400 font-semibold">
          <Star className="w-3.5 h-3.5 fill-amber-400" />
          <span>{Number(val || 5.0).toFixed(1)} / 5.0</span>
        </div>
      )
    },
    {
      header: 'Status',
      accessor: 'active',
      render: (val) => <Badge variant={val !== false ? 'success' : 'danger'}>{val !== false ? 'APPROVED' : 'INACTIVE'}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Supplier & Vendor Directory"
        description="Maintain authorized supply chain vendors, commercial credit terms and vendor performance scorecards"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Supplier
          </button>
        }
      />

      <DataTable
        title="Approved Vendor Catalog"
        columns={columns}
        data={vendors}
        searchPlaceholder="Search vendors..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Approved Supplier">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Company / Entity Name</label>
              <input
                type="text"
                required
                value={formData.companyName}
                onChange={(e) => setFormData({ ...formData, companyName: e.target.value })}
                placeholder="e.g. Micron Technology Corp"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Primary Contact Person</label>
              <input
                type="text"
                required
                value={formData.contactPerson}
                onChange={(e) => setFormData({ ...formData, contactPerson: e.target.value })}
                placeholder="e.g. Sarah Connor"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Phone</label>
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
              <label className="block text-xs font-semibold text-slate-300 mb-1">Payment Terms</label>
              <select
                value={formData.paymentTerms}
                onChange={(e) => setFormData({ ...formData, paymentTerms: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="NET_15">Net 15 Days</option>
                <option value="NET_30">Net 30 Days</option>
                <option value="NET_45">Net 45 Days</option>
                <option value="NET_60">Net 60 Days</option>
                <option value="DUE_ON_RECEIPT">Due Upon Receipt</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">City & Country</label>
              <input
                type="text"
                value={formData.city}
                onChange={(e) => setFormData({ ...formData, city: e.target.value })}
                placeholder="Santa Clara, USA"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
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
              Save Supplier
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
