import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Package, Plus, Trash2, AlertTriangle, Boxes } from 'lucide-react';

export function ProductCatalog() {
  const { products, addProduct, deleteProduct } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    sku: '',
    category: 'ENTERPRISE_SOFTWARE',
    unitCost: 1200,
    sellingPrice: 2400,
    stockQuantity: 50,
    reorderLevel: 15
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addProduct({
      ...formData,
      unitCost: parseFloat(formData.unitCost) || 0,
      sellingPrice: parseFloat(formData.sellingPrice) || 0,
      stockQuantity: parseInt(formData.stockQuantity, 10) || 0,
      reorderLevel: parseInt(formData.reorderLevel, 10) || 10
    });
    addToast('Product Added', `SKU ${formData.name} added to catalog.`, 'success');
    setIsModalOpen(false);
    setFormData({
      name: '',
      sku: '',
      category: 'ENTERPRISE_SOFTWARE',
      unitCost: 1200,
      sellingPrice: 2400,
      stockQuantity: 50,
      reorderLevel: 15
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to delete product: ${name}?`)) {
      deleteProduct(id);
      addToast('Product Deleted', `${name} has been removed from inventory.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'SKU Code',
      accessor: 'sku',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'SKU-NEW'}</span>
    },
    {
      header: 'Product Name',
      accessor: 'name',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Category',
      accessor: 'category',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Unit Cost',
      accessor: 'unitCost',
      render: (val) => <span className="font-mono text-slate-700">{formatCurrency(val)}</span>
    },
    {
      header: 'Selling Price',
      accessor: 'sellingPrice',
      render: (val) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val)}</span>
    },
    {
      header: 'Stock Level',
      accessor: 'stockQuantity',
      render: (val, row) => {
        const isLow = Number(val) <= Number(row.reorderLevel || 10);
        return (
          <div className="flex items-center space-x-2">
            <span className={`font-mono font-bold ${isLow ? 'text-rose-600' : 'text-slate-900'}`}>
              {val} units
            </span>
            {isLow && (
              <span className="px-1.5 py-0.5 rounded bg-rose-50 text-rose-600 font-bold text-[10px] border border-rose-200">
                Low Stock
              </span>
            )}
          </div>
        );
      }
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.name)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Product"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Product Catalog & Master Item Registry"
        description="Manage commercial SKUs, bill of materials, cost prices, inventory stock valuations and reorder alerts"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Product SKU
          </button>
        }
      />

      <DataTable
        title="Master Product Registry"
        columns={columns}
        data={products}
        searchPlaceholder="Search products by SKU, name, category..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Add Product SKU to Catalog">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Product / Item Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Cisco Catalyst 9300 Switch"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">SKU Code</label>
              <input
                type="text"
                value={formData.sku}
                onChange={(e) => setFormData({ ...formData, sku: e.target.value })}
                placeholder="SKU-9300-48P"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Product Category</label>
            <select
              value={formData.category}
              onChange={(e) => setFormData({ ...formData, category: e.target.value })}
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
            >
              <option value="ENTERPRISE_SOFTWARE">Enterprise Software & SaaS</option>
              <option value="SERVER_HARDWARE">Server Hardware & Racks</option>
              <option value="NETWORKING_EQUIPMENT">Networking & Switches</option>
              <option value="CLOUD_SERVICES">Managed Cloud Services</option>
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Unit Cost Price ($)</label>
              <input
                type="number"
                required
                min={0}
                value={formData.unitCost}
                onChange={(e) => setFormData({ ...formData, unitCost: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Selling Retail Price ($)</label>
              <input
                type="number"
                required
                min={0}
                value={formData.sellingPrice}
                onChange={(e) => setFormData({ ...formData, sellingPrice: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Initial Stock Quantity</label>
              <input
                type="number"
                required
                min={0}
                value={formData.stockQuantity}
                onChange={(e) => setFormData({ ...formData, stockQuantity: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Low-Stock Reorder Level</label>
              <input
                type="number"
                required
                min={1}
                value={formData.reorderLevel}
                onChange={(e) => setFormData({ ...formData, reorderLevel: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
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
              Add Product SKU
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
