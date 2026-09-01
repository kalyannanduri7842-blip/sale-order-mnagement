import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useNotification } from '../../context/NotificationContext';
import { Boxes, Plus, Truck, MapPin, CheckCircle2 } from 'lucide-react';

export function SupplyChainLogistics() {
  const [shipments, setShipments] = useState([
    { id: 1, shipmentNumber: 'SHP-1001', trackingNumber: 'TRK992019481', salesOrderNumber: 'SO-1001', customerName: 'Acme Global Technologies Inc', carrierName: 'FedEx Enterprise Freight', shipmentDate: '2026-08-29', estimatedDeliveryDate: '2026-09-02', status: 'IN_TRANSIT', destinationAddress: '100 Innovation Blvd, San Jose, CA' },
    { id: 2, shipmentNumber: 'SHP-1002', trackingNumber: 'TRK992019482', salesOrderNumber: 'SO-1002', customerName: 'Apex Health Systems LLC', carrierName: 'UPS Express Heavy', shipmentDate: '2026-08-25', estimatedDeliveryDate: '2026-08-28', actualDeliveryDate: '2026-08-28', status: 'DELIVERED', destinationAddress: '450 Medical Center Dr, Boston, MA' }
  ]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    salesOrderNumber: 'SO-1003',
    customerName: 'Acme Global Corp',
    carrierName: 'FedEx Freight',
    destinationAddress: '100 Innovation Blvd, Suite 400',
    estimatedDeliveryDate: new Date(Date.now() + 4 * 86400000).toISOString().split('T')[0]
  });
  const { addToast } = useNotification();

  const handleCreateShipment = (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      id: Date.now(),
      shipmentNumber: 'SHP-' + (1000 + shipments.length + 1),
      trackingNumber: 'TRK' + Date.now().toString().slice(-9),
      shipmentDate: new Date().toISOString().split('T')[0],
      status: 'IN_TRANSIT'
    };
    setShipments([payload, ...shipments]);
    addToast('Freight Consignment Dispatched', `Shipment ${payload.shipmentNumber} generated.`, 'success');
    setIsModalOpen(false);
  };

  const handleMarkDelivered = (id) => {
    setShipments(shipments.map((s) => (s.id === id ? { ...s, status: 'DELIVERED', actualDeliveryDate: new Date().toISOString().split('T')[0] } : s)));
    addToast('Proof of Delivery Confirmed', 'Shipment marked as safely delivered.', 'success');
  };

  const columns = [
    {
      header: 'Shipment #',
      accessor: 'shipmentNumber',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Carrier & Tracking',
      accessor: 'trackingNumber',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{row.carrierName}</span>
          <span className="text-[10px] font-mono text-emerald-400">Tracking: {val}</span>
        </div>
      )
    },
    {
      header: 'Customer Destination',
      accessor: 'customerName',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">{row.destinationAddress}</span>
        </div>
      )
    },
    {
      header: 'Dispatched Date',
      accessor: 'shipmentDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Estimated Delivery',
      accessor: 'estimatedDeliveryDate',
      render: (val, row) => <span className="font-mono text-slate-400">{row.actualDeliveryDate || val}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'DELIVERED' ? 'success' : 'warning'}>{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div>
          {row.status !== 'DELIVERED' && (
            <button
              onClick={() => handleMarkDelivered(val)}
              className="px-2.5 py-1 bg-emerald-600 hover:bg-emerald-500 text-white rounded-lg text-xs font-semibold flex items-center shadow transition-colors"
            >
              <CheckCircle2 className="w-3.5 h-3.5 mr-1" /> Mark Delivered
            </button>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Supply Chain & Freight Logistics"
        description="Monitor multi-carrier distribution, live shipping waybills, tracking numbers and proof of delivery"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Dispatch Freight Shipment
          </button>
        }
      />

      <DataTable
        title="Active Waybills & Consignments"
        columns={columns}
        data={shipments}
        searchPlaceholder="Search shipments..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Dispatch Consignment Shipment">
        <form onSubmit={handleCreateShipment} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Sales Order Reference</label>
              <input
                type="text"
                required
                value={formData.salesOrderNumber}
                onChange={(e) => setFormData({ ...formData, salesOrderNumber: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Customer Name</label>
              <input
                type="text"
                required
                value={formData.customerName}
                onChange={(e) => setFormData({ ...formData, customerName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Carrier Provider</label>
              <select
                value={formData.carrierName}
                onChange={(e) => setFormData({ ...formData, carrierName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="FedEx Enterprise Freight">FedEx Enterprise Freight</option>
                <option value="UPS Express Heavy">UPS Express Heavy</option>
                <option value="DHL Global Forwarding">DHL Global Forwarding</option>
                <option value="Maersk Inland Logistics">Maersk Inland Logistics</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Est. Delivery Date</label>
              <input
                type="date"
                required
                value={formData.estimatedDeliveryDate}
                onChange={(e) => setFormData({ ...formData, estimatedDeliveryDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Delivery Destination Address</label>
            <input
              type="text"
              required
              value={formData.destinationAddress}
              onChange={(e) => setFormData({ ...formData, destinationAddress: e.target.value })}
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
              Create Waybill
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
