import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useNotification } from '../../context/NotificationContext';
import { Layers, Plus, DollarSign, Wrench, ShieldCheck, MapPin } from 'lucide-react';

export function AssetManager() {
  const [assets, setAssets] = useState([
    { id: 1, assetCode: 'AST-1001', name: 'Dell PowerEdge Enterprise Cluster Rack (8 Nodes)', category: 'IT_EQUIPMENT', serialNumber: 'DELL-PE-8849102', purchaseDate: '2023-01-15', purchaseCost: 84000.0, currentValuation: 67200.0, usefulLifeYears: 5, location: 'Austin Data Center Room 3B', status: 'OPERATIONAL', assignedEmployeeName: 'Alex Rivers' },
    { id: 2, assetCode: 'AST-1002', name: 'Automated Laser SMT Pick & Place Machine', category: 'MACHINERY', serialNumber: 'SMT-AUTO-2024', purchaseDate: '2024-05-10', purchaseCost: 145000.0, currentValuation: 128000.0, usefulLifeYears: 10, location: 'Assembly Bay 2', status: 'OPERATIONAL', assignedEmployeeName: 'Marcus Vance' }
  ]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    category: 'IT_EQUIPMENT',
    serialNumber: '',
    purchaseDate: new Date().toISOString().split('T')[0],
    purchaseCost: 25000,
    usefulLifeYears: 5,
    location: 'Austin HQ Floor 2',
    assignedEmployeeName: 'Alex Rivers'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      id: Date.now(),
      assetCode: 'AST-' + (1000 + assets.length + 1),
      currentValuation: formData.purchaseCost,
      status: 'OPERATIONAL'
    };
    setAssets([payload, ...assets]);
    addToast('Capital Asset Registered', `Asset ${payload.name} added to fixed asset register.`, 'success');
    setIsModalOpen(false);
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'Asset Code',
      accessor: 'assetCode',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Asset Name & Serial',
      accessor: 'name',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] font-mono text-slate-400">SN: {row.serialNumber || 'N/A'}</span>
        </div>
      )
    },
    {
      header: 'Category',
      accessor: 'category',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Purchase Cost',
      accessor: 'purchaseCost',
      render: (val) => <span className="font-mono text-slate-300">{formatCurrency(val)}</span>
    },
    {
      header: 'Current Book Value',
      accessor: 'currentValuation',
      render: (val) => <span className="font-mono font-bold text-emerald-400">{formatCurrency(val)}</span>
    },
    {
      header: 'Location & Custodian',
      accessor: 'location',
      render: (val, row) => (
        <div className="text-xs text-slate-300">
          <div>{val}</div>
          <div className="text-[10px] text-slate-500">Custodian: {row.assignedEmployeeName}</div>
        </div>
      )
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant="success">{val}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Fixed Asset Registry & Depreciation"
        description="Track enterprise capital assets, hardware depreciation amortization, maintenance schedules and asset assignments"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Register Asset
          </button>
        }
      />

      <DataTable
        title="Corporate Asset Ledger"
        columns={columns}
        data={assets}
        searchPlaceholder="Search assets..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Capital Asset">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Asset Name / Model</label>
            <input
              type="text"
              required
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              placeholder="e.g. Cisco Nexus 9000 Spine Switch"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Category</label>
              <select
                value={formData.category}
                onChange={(e) => setFormData({ ...formData, category: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="IT_EQUIPMENT">IT & Server Hardware</option>
                <option value="MACHINERY">Assembly & Production Machinery</option>
                <option value="VEHICLE">Fleet & Logistics Vehicles</option>
                <option value="FURNITURE">Office Fixtures & Furniture</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Serial / Asset Tag Number</label>
              <input
                type="text"
                required
                value={formData.serialNumber}
                onChange={(e) => setFormData({ ...formData, serialNumber: e.target.value })}
                placeholder="SN-99410"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Purchase Capital Cost ($)</label>
              <input
                type="number"
                required
                value={formData.purchaseCost}
                onChange={(e) => setFormData({ ...formData, purchaseCost: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Useful Life (Years)</label>
              <input
                type="number"
                value={formData.usefulLifeYears}
                onChange={(e) => setFormData({ ...formData, usefulLifeYears: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Location / Room</label>
              <input
                type="text"
                value={formData.location}
                onChange={(e) => setFormData({ ...formData, location: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Assigned Custodian</label>
              <input
                type="text"
                value={formData.assignedEmployeeName}
                onChange={(e) => setFormData({ ...formData, assignedEmployeeName: e.target.value })}
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
              Register Asset
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
