import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Warehouse, Plus, MapPin, Phone, Mail, Box, ShieldCheck } from 'lucide-react';

export function WarehouseManager() {
  const [warehouses, setWarehouses] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    code: '',
    address: '',
    city: '',
    state: 'TX',
    country: 'USA',
    managerName: '',
    contactPhone: '',
    capacity: 50000
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadWarehouses();
  }, []);

  const loadWarehouses = async () => {
    const data = await api.get('/warehouses', 'warehouses');
    setWarehouses(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = { ...formData, active: true };
    await api.post('/warehouses', payload, 'warehouses');
    addToast('Facility Created', `Warehouse ${formData.name} added to logistics network.`, 'success');
    setIsModalOpen(false);
    loadWarehouses();
  };

  return (
    <div className="space-y-6">
      <PageHeader
        title="Warehouse Facilities & Storage Nodes"
        description="Multi-facility logistics management, storage capacity tracking and regional distribution hubs"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Warehouse Facility
          </button>
        }
      />

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {warehouses.map((wh) => (
          <div
            key={wh.id}
            className="bg-slate-900/70 border border-slate-800 rounded-3xl p-6 shadow-xl backdrop-blur-md space-y-4 transition-all hover:border-slate-700"
          >
            <div className="flex items-start justify-between">
              <div className="flex items-center space-x-3">
                <div className="p-3 rounded-2xl bg-blue-600/20 border border-blue-500/30 text-blue-400">
                  <Warehouse className="w-6 h-6" />
                </div>
                <div>
                  <h3 className="text-base font-bold text-white tracking-tight">{wh.name}</h3>
                  <span className="font-mono text-xs text-blue-400 font-bold">{wh.code}</span>
                </div>
              </div>
              <Badge variant="success">OPERATIONAL</Badge>
            </div>

            {/* Address and details */}
            <div className="space-y-2 text-xs text-slate-300">
              <div className="flex items-center text-slate-400">
                <MapPin className="w-4 h-4 mr-2 text-slate-500 flex-shrink-0" />
                <span>{wh.address}, {wh.city}, {wh.state} {wh.country}</span>
              </div>
              <div className="flex items-center text-slate-400">
                <Phone className="w-4 h-4 mr-2 text-slate-500 flex-shrink-0" />
                <span>{wh.contactPhone || '+1-800-555-0199'}</span>
              </div>
              <div className="flex items-center text-slate-400">
                <ShieldCheck className="w-4 h-4 mr-2 text-slate-500 flex-shrink-0" />
                <span>Facility Manager: <b className="text-slate-200">{wh.managerName || 'Operations Lead'}</b></span>
              </div>
            </div>

            {/* Capacity Meter */}
            <div className="pt-2 border-t border-slate-800/80">
              <div className="flex justify-between text-xs mb-1.5">
                <span className="text-slate-400 font-medium">Storage Capacity Allocation</span>
                <span className="font-mono font-bold text-white">68% Utilized ({(wh.capacity * 0.68).toLocaleString()} / {Number(wh.capacity || 50000).toLocaleString()} units)</span>
              </div>
              <div className="w-full bg-slate-950 rounded-full h-2.5 overflow-hidden border border-slate-800">
                <div className="bg-gradient-to-r from-blue-500 to-indigo-500 h-2.5 rounded-full" style={{ width: '68%' }} />
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Logistics Warehouse Node">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Facility Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Newark East Coast Distribution"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Facility Code</label>
              <input
                type="text"
                required
                value={formData.code}
                onChange={(e) => setFormData({ ...formData, code: e.target.value })}
                placeholder="WH-EAST"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Street Address</label>
            <input
              type="text"
              required
              value={formData.address}
              onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              placeholder="e.g. 500 Route 1 South"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-3 gap-3">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">City</label>
              <input
                type="text"
                required
                value={formData.city}
                onChange={(e) => setFormData({ ...formData, city: e.target.value })}
                placeholder="Newark"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">State / Province</label>
              <input
                type="text"
                required
                value={formData.state}
                onChange={(e) => setFormData({ ...formData, state: e.target.value })}
                placeholder="NJ"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Country</label>
              <input
                type="text"
                required
                value={formData.country}
                onChange={(e) => setFormData({ ...formData, country: e.target.value })}
                placeholder="USA"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Facility Manager</label>
              <input
                type="text"
                required
                value={formData.managerName}
                onChange={(e) => setFormData({ ...formData, managerName: e.target.value })}
                placeholder="e.g. John Miller"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Total Unit Storage Capacity</label>
              <input
                type="number"
                required
                value={formData.capacity}
                onChange={(e) => setFormData({ ...formData, capacity: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
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
              Register Facility
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
