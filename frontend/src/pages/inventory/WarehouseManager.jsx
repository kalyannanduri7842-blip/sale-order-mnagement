import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Warehouse, Plus, Trash2, MapPin } from 'lucide-react';

export function WarehouseManager() {
  const { warehouses, addWarehouse, deleteWarehouse } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    code: '',
    location: '',
    managerName: '',
    capacity: 25000,
    currentOccupancyPercentage: 45
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addWarehouse(formData);
    addToast('Warehouse Added', `Location ${formData.name} added to supply network.`, 'success');
    setIsModalOpen(false);
    setFormData({
      name: '',
      code: '',
      location: '',
      managerName: '',
      capacity: 25000,
      currentOccupancyPercentage: 45
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to delete warehouse: ${name}?`)) {
      deleteWarehouse(id);
      addToast('Warehouse Deleted', `${name} has been removed.`, 'info');
    }
  };

  const columns = [
    {
      header: 'Code',
      accessor: 'code',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'WH-NEW'}</span>
    },
    {
      header: 'Warehouse Name',
      accessor: 'name',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'City / Location',
      accessor: 'location',
      render: (val) => (
        <div className="flex items-center text-slate-700">
          <MapPin className="w-3.5 h-3.5 mr-1 text-slate-400" />
          <span>{val}</span>
        </div>
      )
    },
    {
      header: 'Facility Manager',
      accessor: 'managerName',
      render: (val) => <span className="text-slate-700 font-medium">{val || 'Unassigned'}</span>
    },
    {
      header: 'Capacity',
      accessor: 'capacity',
      render: (val) => <span className="font-mono font-bold text-slate-900">{val?.toLocaleString()} sq.ft</span>
    },
    {
      header: 'Occupancy',
      accessor: 'currentOccupancyPercentage',
      render: (val) => (
        <div className="flex items-center space-x-2">
          <div className="w-16 bg-slate-100 rounded-full h-2 overflow-hidden border border-slate-200">
            <div
              className="bg-blue-600 h-full rounded-full"
              style={{ width: `${val || 45}%` }}
            />
          </div>
          <span className="font-mono font-bold text-xs text-slate-700">{val || 45}%</span>
        </div>
      )
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.name)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Warehouse"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Multi-Warehouse & Facility Management"
        description="Monitor physical warehouse hubs, square footage utilization, facility managers and inventory distribution"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Warehouse
          </button>
        }
      />

      <DataTable
        title="Warehouse Facilities"
        columns={columns}
        data={warehouses}
        searchPlaceholder="Search warehouses..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Add Warehouse Facility">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Facility Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Hyderabad Central Distribution Hub"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Warehouse Code</label>
              <input
                type="text"
                value={formData.code}
                onChange={(e) => setFormData({ ...formData, code: e.target.value })}
                placeholder="WH-HYD-01"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">City / Region</label>
              <input
                type="text"
                required
                value={formData.location}
                onChange={(e) => setFormData({ ...formData, location: e.target.value })}
                placeholder="e.g. Hyderabad, Telangana"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Facility Manager</label>
              <input
                type="text"
                value={formData.managerName}
                onChange={(e) => setFormData({ ...formData, managerName: e.target.value })}
                placeholder="e.g. Marcus Vance"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Total Storage Capacity (sq. ft)</label>
            <input
              type="number"
              min={100}
              value={formData.capacity}
              onChange={(e) => setFormData({ ...formData, capacity: Number(e.target.value) })}
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
              Add Warehouse
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
