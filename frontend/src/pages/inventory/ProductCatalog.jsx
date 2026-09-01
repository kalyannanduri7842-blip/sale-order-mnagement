import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { PackagePlus, Barcode, AlertTriangle, Layers, DollarSign } from 'lucide-react';

export function ProductCatalog() {
  const [products, setProducts] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    sku: '',
    barcode: '',
    categoryName: 'Server Hardware',
    costPrice: 500,
    sellingPrice: 1200,
    currentStock: 50,
    minStockAlert: 10,
    reorderQuantity: 20
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    const data = await api.get('/products', 'products');
    setProducts(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      productCode: 'PRD-' + (1000 + products.length + 1),
      active: true,
      lowStock: formData.currentStock <= formData.minStockAlert
    };
    await api.post('/products', payload, 'products');
    addToast('Product Added', `Product ${formData.name} registered into catalog.`, 'success');
    setIsModalOpen(false);
    loadProducts();
  };

  const columns = [
    {
      header: 'Product Code',
      accessor: 'productCode',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Item Title & SKU',
      accessor: 'name',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <div className="flex items-center space-x-2 text-[10px] text-slate-400 font-mono mt-0.5">
            <span>SKU: {row.sku}</span>
            <span>•</span>
            <span className="flex items-center"><Barcode className="w-3 h-3 mr-1" /> {row.barcode}</span>
          </div>
        </div>
      )
    },
    {
      header: 'Category',
      accessor: 'categoryName',
      render: (val) => <Badge variant="primary">{val || 'Hardware'}</Badge>
    },
    {
      header: 'Cost Price',
      accessor: 'costPrice',
      render: (val) => <span className="font-mono text-slate-300">${Number(val || 0).toLocaleString()}</span>
    },
    {
      header: 'Selling Price',
      accessor: 'sellingPrice',
      render: (val) => <span className="font-mono font-semibold text-emerald-400">${Number(val || 0).toLocaleString()}</span>
    },
    {
      header: 'In Stock',
      accessor: 'currentStock',
      render: (val, row) => {
        const isLow = val <= (row.minStockAlert || 10);
        return (
          <div className="flex items-center space-x-1.5">
            <span className={`font-mono font-bold ${isLow ? 'text-rose-400' : 'text-white'}`}>{val} units</span>
            {isLow && <Badge variant="danger">LOW</Badge>}
          </div>
        );
      }
    },
    {
      header: 'Status',
      accessor: 'active',
      render: (val) => <Badge variant={val !== false ? 'success' : 'danger'}>{val !== false ? 'ACTIVE' : 'DISCONTINUED'}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Product Catalog & Stock Registry"
        description="Maintain enterprise product items, barcodes, cost structures, retail margins and minimum alert thresholds"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <PackagePlus className="w-3.5 h-3.5 mr-1.5" /> Add Product Item
          </button>
        }
      />

      <DataTable
        title="Active SKU Inventory"
        columns={columns}
        data={products}
        searchPlaceholder="Search product name, SKU or barcode..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Catalog Product">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Product Title</label>
            <input
              type="text"
              required
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              placeholder="e.g. Enterprise 10GbE Switch 24-Port"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">SKU Code</label>
              <input
                type="text"
                required
                value={formData.sku}
                onChange={(e) => setFormData({ ...formData, sku: e.target.value })}
                placeholder="NET-10G-24P"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">UPC / Barcode</label>
              <input
                type="text"
                value={formData.barcode}
                onChange={(e) => setFormData({ ...formData, barcode: e.target.value })}
                placeholder="8901234567899"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Category</label>
              <select
                value={formData.categoryName}
                onChange={(e) => setFormData({ ...formData, categoryName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="Server Hardware">Server Hardware</option>
                <option value="Enterprise Software">Enterprise Software</option>
                <option value="Raw Materials & Parts">Raw Materials & Parts</option>
                <option value="Networking & Telecom">Networking & Telecom</option>
                <option value="Accessories">Accessories</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Initial Stock Count</label>
              <input
                type="number"
                required
                value={formData.currentStock}
                onChange={(e) => setFormData({ ...formData, currentStock: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Unit Cost Price ($)</label>
              <input
                type="number"
                required
                value={formData.costPrice}
                onChange={(e) => setFormData({ ...formData, costPrice: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Unit Selling Price ($)</label>
              <input
                type="number"
                required
                value={formData.sellingPrice}
                onChange={(e) => setFormData({ ...formData, sellingPrice: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Low Stock Warning Alert</label>
              <input
                type="number"
                value={formData.minStockAlert}
                onChange={(e) => setFormData({ ...formData, minStockAlert: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Standard Reorder Quantity</label>
              <input
                type="number"
                value={formData.reorderQuantity}
                onChange={(e) => setFormData({ ...formData, reorderQuantity: Number(e.target.value) })}
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
              Save Product Item
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
