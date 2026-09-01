import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Flame, Plus, Trash2, ArrowRight } from 'lucide-react';

export function CrmPipeline() {
  const { leads, addLead, deleteLead } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    title: '',
    companyName: '',
    contactName: '',
    email: '',
    phone: '',
    source: 'WEBSITE',
    stage: 'NEW',
    expectedValue: 50000
  });
  const { addToast } = useNotification();

  const stages = [
    { id: 'NEW', label: 'New Inquiries', color: 'border-blue-200 bg-blue-50/40' },
    { id: 'QUALIFIED', label: 'Qualified Needs', color: 'border-indigo-200 bg-indigo-50/40' },
    { id: 'PROPOSAL', label: 'Proposal Sent', color: 'border-amber-200 bg-amber-50/40' },
    { id: 'WON', label: 'Closed Won 🎉', color: 'border-emerald-200 bg-emerald-50/40' }
  ];

  const handleSave = (e) => {
    e.preventDefault();
    addLead({
      ...formData,
      expectedValue: parseFloat(formData.expectedValue) || 50000
    });
    addToast('Opportunity Created', `Opportunity ${formData.title} added to pipeline.`, 'success');
    setIsModalOpen(false);
    setFormData({
      title: '',
      companyName: '',
      contactName: '',
      email: '',
      phone: '',
      source: 'WEBSITE',
      stage: 'NEW',
      expectedValue: 50000
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Delete opportunity: ${name}?`)) {
      deleteLead(id);
      addToast('Opportunity Removed', 'Opportunity deleted.', 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  return (
    <div className="space-y-6">
      <PageHeader
        title="CRM Opportunities & Pipeline Funnel"
        description="Track commercial sales prospects from initial qualification to closed contracts"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Opportunity
          </button>
        }
      />

      {/* Kanban Pipeline Board */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-5">
        {stages.map((stage) => {
          const stageLeads = leads.filter((l) => l.stage === stage.id);
          const totalVal = stageLeads.reduce((s, l) => s + (Number(l.expectedValue || l.estimatedValue) || 0), 0);

          return (
            <div
              key={stage.id}
              className={`rounded-2xl border ${stage.color} p-4 flex flex-col bg-white shadow-sm min-h-[480px]`}
            >
              {/* Stage Header */}
              <div className="flex items-center justify-between pb-3 border-b border-slate-100 mb-3">
                <div>
                  <h4 className="text-xs font-bold text-slate-800 uppercase tracking-wider">{stage.label}</h4>
                  <span className="text-[10px] text-slate-400 font-medium">{stageLeads.length} Deals</span>
                </div>
                <span className="font-mono font-bold text-emerald-600 text-xs">{formatCurrency(totalVal)}</span>
              </div>

              {/* Lead Cards List */}
              <div className="space-y-3 flex-1 overflow-y-auto">
                {stageLeads.map((lead) => (
                  <div
                    key={lead.id}
                    className="p-3.5 rounded-xl bg-white border border-slate-200 hover:border-blue-300 shadow-2xs space-y-2.5 transition-all"
                  >
                    <div className="flex items-start justify-between">
                      <div>
                        <h5 className="text-xs font-bold text-slate-900 line-clamp-1">{lead.title || lead.name}</h5>
                        <span className="text-[10px] text-slate-500 font-medium block">{lead.companyName || lead.company}</span>
                      </div>
                      <button
                        onClick={() => handleDelete(lead.id, lead.title || lead.companyName)}
                        className="text-slate-400 hover:text-rose-600 p-1"
                        title="Delete Opportunity"
                      >
                        <Trash2 className="w-3.5 h-3.5" />
                      </button>
                    </div>

                    <div className="flex items-center justify-between text-xs pt-2 border-t border-slate-100">
                      <span className="font-mono font-bold text-emerald-600">{formatCurrency(lead.expectedValue || lead.estimatedValue || 50000)}</span>
                      <Badge variant="primary">{lead.source || 'INBOUND'}</Badge>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          );
        })}
      </div>

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Create Sales Opportunity">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Opportunity Title</label>
            <input
              type="text"
              required
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
              placeholder="e.g. Enterprise Cloud ERP Migration"
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Company / Organization</label>
              <input
                type="text"
                required
                value={formData.companyName}
                onChange={(e) => setFormData({ ...formData, companyName: e.target.value })}
                placeholder="OmniRetail Inc"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Lead Source</label>
              <select
                value={formData.source}
                onChange={(e) => setFormData({ ...formData, source: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="WEBSITE">Website Inbound</option>
                <option value="CONFERENCE">Industry Conference</option>
                <option value="REFERRAL">Client Referral</option>
                <option value="OUTREACH">Outbound Sales</option>
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Estimated Deal Value ($)</label>
              <input
                type="number"
                required
                min={100}
                value={formData.expectedValue}
                onChange={(e) => setFormData({ ...formData, expectedValue: Number(e.target.value) })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Pipeline Stage</label>
              <select
                value={formData.stage}
                onChange={(e) => setFormData({ ...formData, stage: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="NEW">New Inquiries</option>
                <option value="QUALIFIED">Qualified Needs</option>
                <option value="PROPOSAL">Proposal Sent</option>
                <option value="WON">Closed Won</option>
              </select>
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
              Save Opportunity
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
