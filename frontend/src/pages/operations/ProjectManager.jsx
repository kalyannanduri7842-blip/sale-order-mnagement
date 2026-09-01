import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { KanbanSquare, Plus, DollarSign, Calendar, CheckSquare, Clock } from 'lucide-react';

export function ProjectManager() {
  const [projects, setProjects] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    clientName: 'Acme Global Technologies',
    projectManagerName: 'Alex Rivers',
    startDate: new Date().toISOString().split('T')[0],
    endDate: new Date(Date.now() + 90 * 86400000).toISOString().split('T')[0],
    budget: 150000,
    priority: 'HIGH'
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadProjects();
  }, []);

  const loadProjects = async () => {
    const data = await api.get('/projects', 'projects');
    setProjects(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      projectCode: 'PRJ-' + (1000 + projects.length + 1),
      actualCost: 0,
      progressPercentage: 0,
      status: 'IN_PROGRESS',
      totalTasks: 5,
      completedTasks: 0
    };
    await api.post('/projects', payload, 'projects');
    addToast('Project Initialized', `Project ${payload.name} created.`, 'success');
    setIsModalOpen(false);
    loadProjects();
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }).format(val || 0);

  return (
    <div className="space-y-6">
      <PageHeader
        title="Project Lifecycle & Milestone Tracker"
        description="Oversee enterprise customer engagements, project budgets, task allocations and sprint progress"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Initialize Project
          </button>
        }
      />

      {/* Projects Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {projects.map((proj) => (
          <div
            key={proj.id}
            className="bg-slate-900/70 border border-slate-800 rounded-3xl p-6 shadow-xl backdrop-blur-md space-y-4 transition-all hover:border-slate-700"
          >
            <div className="flex items-start justify-between">
              <div>
                <span className="font-mono text-xs text-blue-400 font-bold">{proj.projectCode}</span>
                <h3 className="text-base font-bold text-white tracking-tight mt-0.5">{proj.name}</h3>
                <p className="text-xs text-slate-400 mt-1">{proj.description}</p>
              </div>
              <Badge variant={proj.status === 'COMPLETED' ? 'success' : 'warning'}>{proj.status}</Badge>
            </div>

            {/* Client & PM info */}
            <div className="grid grid-cols-2 gap-2 text-xs bg-slate-950/60 p-3 rounded-xl border border-slate-800">
              <div>
                <span className="text-[10px] text-slate-500 block">Client Account:</span>
                <span className="font-semibold text-slate-200">{proj.clientName}</span>
              </div>
              <div>
                <span className="text-[10px] text-slate-500 block">Project Lead:</span>
                <span className="font-semibold text-slate-200">{proj.projectManagerName}</span>
              </div>
            </div>

            {/* Financial and Tasks stats */}
            <div className="flex items-center justify-between text-xs pt-1">
              <div>
                <span className="text-[10px] text-slate-400 block">Allocated Budget</span>
                <span className="font-mono font-bold text-emerald-400">{formatCurrency(proj.budget)}</span>
              </div>
              <div className="text-right">
                <span className="text-[10px] text-slate-400 block">Milestones & Tasks</span>
                <span className="font-semibold text-white">
                  {proj.completedTasks || 0} / {proj.totalTasks || 0} Done
                </span>
              </div>
            </div>

            {/* Progress Bar */}
            <div>
              <div className="flex justify-between text-xs mb-1">
                <span className="text-slate-400">Milestone Progress</span>
                <span className="font-bold text-white">{proj.progressPercentage || 0}%</span>
              </div>
              <div className="w-full bg-slate-950 rounded-full h-2 overflow-hidden border border-slate-800">
                <div
                  className="bg-gradient-to-r from-blue-500 to-indigo-500 h-2 rounded-full"
                  style={{ width: `${proj.progressPercentage || 0}%` }}
                />
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Initialize Enterprise Project">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Project Name</label>
            <input
              type="text"
              required
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              placeholder="e.g. NextGen Microservices Scaling"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Description</label>
            <textarea
              rows={2}
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              placeholder="Project objective and scope..."
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Client Entity</label>
              <input
                type="text"
                required
                value={formData.clientName}
                onChange={(e) => setFormData({ ...formData, clientName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Project Manager</label>
              <input
                type="text"
                required
                value={formData.projectManagerName}
                onChange={(e) => setFormData({ ...formData, projectManagerName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-3 gap-3">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Budget ($)</label>
              <input
                type="number"
                required
                value={formData.budget}
                onChange={(e) => setFormData({ ...formData, budget: Number(e.target.value) })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white font-mono focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Start Date</label>
              <input
                type="date"
                required
                value={formData.startDate}
                onChange={(e) => setFormData({ ...formData, startDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Target End Date</label>
              <input
                type="date"
                required
                value={formData.endDate}
                onChange={(e) => setFormData({ ...formData, endDate: e.target.value })}
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
              Launch Project
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
