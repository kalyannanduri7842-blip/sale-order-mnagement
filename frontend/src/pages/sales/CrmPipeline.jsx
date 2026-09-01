import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Flame, Plus, DollarSign, ArrowRight, User } from 'lucide-react';

export function CrmPipeline() {
  const [leads, setLeads] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    company: '',
    email: '',
    phone: '',
    source: 'WEBSITE',
    stage: 'NEW',
    estimatedValue: 50000,
    probability: 40
  });
  const { addToast } = useNotification();

  const stages = [
    { id: 'NEW', label: 'New Inquiries', color: 'border-blue-500/40 bg-blue-950/10' },
    { id: 'QUALIFIED', label: 'Qualified Needs', color: 'border-indigo-500/40 bg-indigo-950/10' },
    { id: 'PROPOSAL', label: 'Proposal Sent', color: 'border-amber-500/40 bg-amber-950/10' },
    { id: 'WON', label: 'Closed Won 🎉', color: 'border-emerald-500/40 bg-emerald-950/10' }
  ];

  useEffect(() => {
    loadLeads();
  }, []);

  const loadLeads = async () => {
    const data = await api.get('/crm/leads', 'leads');
    setLeads(Array.isArray(data) ? data : data?.content || []);
  };

  const handleAdvanceStage = async (id, currentStage) => {
    const stageFlow = ['NEW', 'QUALIFIED', 'PROPOSAL', 'WON'];
    const currIdx = stageFlow.indexOf(currentStage);
    if (currIdx < stageFlow.length - 1) {
      const nextStage = stageFlow[currIdx + 1];
      await api.put(`/crm/leads/${id}/stage`, { stage: nextStage }, 'leads');
      setLeads(leads.map((l) => (l.id === id ? { ...l, stage: nextStage } : l)));
      addToast('Pipeline Updated', `Lead moved to ${nextStage}`, 'success');
    }
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      id: Date.now(),
      assignedToUsername: 'salesmanager'
    };
    await api.post('/crm/leads', payload, 'leads');
    addToast('Opportunity Created', `Opportunity ${formData.name} added to pipeline.`, 'success');
    setIsModalOpen(false);
    loadLeads();
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(val || 0);

  return (
    <div className="space-y-6">
      <PageHeader
        title="CRM Opportunities & Pipeline Funnel"
        description="Track commercial sales prospects from initial qualification to closed contracts"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Opportunity
          </button>
        }
      />

      {/* Kanban Pipeline Board */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-5">
        {stages.map((stage) => {
          const stageLeads = leads.filter((l) => l.stage === stage.id);
          const totalVal = stageLeads.reduce((s, l) => s + (Number(l.estimatedValue) || 0), 0);

          return (
            <div
              key={stage.id}
              className={`rounded-3xl border ${stage.color} p-4 flex flex-col bg-slate-900/60 shadow-xl backdrop-blur-md min-h-[500px]`}
            >
              {/* Stage Header */}
              <div className="flex items-center justify-between pb-3 border-b border-slate-800/80 mb-3">
                <div>
                  <h4 className="text-xs font-bold text-white uppercase tracking-wider">{stage.label}</h4>
                  <span className="text-[10px] text-slate-400">{stageLeads.length} Deals</span>
                </div>
                <span className="font-mono font-bold text-emerald-400 text-xs">{formatCurrency(totalVal)}</span>
              </div>

              {/* Lead Cards List */}
              <div className="space-y-3 flex-1 overflow-y-auto">
                {stageLeads.map((lead) => (
                  <div
                    key={lead.id}
                    className="p-4 rounded-2xl bg-slate-950/80 border border-slate-800 hover:border-slate-700 shadow-md space-y-3 transition-all"
                  >
                    <div>
                      <h5 className="text-xs font-bold text-white line-clamp-1">{lead.name}</h5>
                      <span className="text-[10px] text-slate-400 block">{lead.company}</span>
                    </div>

                    <div className="flex items-center justify-between text-xs pt-2 border-t border-slate-800/60">
                      <span className="font-mono font-bold text-emerald-400">{formatCurrency(lead.estimatedValue)}</span>
                      <Badge variant="primary">{lead.source}</Badge>
                    </div>

                    {stage.id !== 'WON' && (
                      <button
                        onClick={() => handleAdvanceStage(lead.id, lead.stage)}
                        className="w-full mt-2 py-1.5 px-3 rounded-lg bg-slate-800 hover:bg-slate-700 text-blue-400 text-[11px] font-semibold flex items-center justify-center transition-colors"
                      >
                        Advance Stage <ArrowRight className="w-3 h-3 ml-1" />
                      </button>
                    )}
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
            <label className="block text-xs font-semibold text-slate-300 mb-1">Opportunity Title</label>
            <input
              type="text"
              required
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              placeholder="e.g. Enterprise Cloud ERP Migration"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Company / Organization</label>
              <input
                type="text"
                required
                value={formData.company}
                onChange={(e) => setFormData({ ...formData, company: e.target.value })}
                placeholder="OmniRetail Inc"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Lead Source</label>
              <select
                value={formData.source}
                onChange={(e) => setFormData({ ...formData, source: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                <option value="WEBSITE">Website Inbound</option>
                <option value="CONFERENCE">Industry Conference</option>
                <option value="REFERRAL">Client Referral</option>
                <option value="COLD_OUTREACH">Outbound Sales</option>
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Est. Deal Value ($)</label>
              <input
                type="number"
                required
                value={formData.estimatedValue}
                onChange={(e) => setFormData({ ...formData, estimatedValue: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Close Probability (%)</label>
              <input
                type="number"
                value={formData.probability}
                onChange={(e) => setFormData({ ...formData, probability: Number(e.target.value) })}
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
              Save Opportunity
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
