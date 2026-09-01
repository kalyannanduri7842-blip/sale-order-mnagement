import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { Building2, Plus, Users, UserCheck } from 'lucide-react';

export function DepartmentManagement() {
  const [departments, setDepartments] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    code: '',
    description: '',
    headOfDepartment: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    const data = await api.get('/departments', 'departments');
    setDepartments(Array.isArray(data) ? data : data?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      employeeCount: 0,
      active: true
    };
    await api.post('/departments', payload, 'departments');
    addToast('Department Created', `Department ${formData.name} registered.`, 'success');
    setIsModalOpen(false);
    loadData();
  };

  const columns = [
    {
      header: 'Code',
      accessor: 'code',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Department Name',
      accessor: 'name',
      render: (val, row) => (
        <div>
          <span className="font-bold text-white block">{val}</span>
          <span className="text-[11px] text-slate-400">{row.description}</span>
        </div>
      )
    },
    {
      header: 'Head of Department',
      accessor: 'headOfDepartment',
      render: (val) => (
        <div className="flex items-center space-x-1.5 text-slate-300 font-medium">
          <UserCheck className="w-3.5 h-3.5 text-blue-400" />
          <span>{val || 'Unassigned'}</span>
        </div>
      )
    },
    {
      header: 'Active Staff',
      accessor: 'employeeCount',
      render: (val) => (
        <div className="flex items-center space-x-1 font-semibold text-emerald-400">
          <Users className="w-3.5 h-3.5" />
          <span>{val || 0} Employees</span>
        </div>
      )
    },
    {
      header: 'Status',
      accessor: 'active',
      render: (val) => <Badge variant={val !== false ? 'success' : 'danger'}>{val !== false ? 'ACTIVE' : 'INACTIVE'}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Departments & Organizational Structure"
        description="Configure enterprise business units, departmental hierarchy and leadership heads"
        actions={
          <button
            onClick={() => {
              setFormData({ name: '', code: '', description: '', headOfDepartment: '' });
              setIsModalOpen(true);
            }}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Create Department
          </button>
        }
      />

      <DataTable
        title="Department Hierarchy"
        columns={columns}
        data={departments}
        searchPlaceholder="Search departments..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Create Department Unit">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Department Name</label>
              <input
                type="text"
                required
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                placeholder="e.g. Quality Assurance"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Department Code</label>
              <input
                type="text"
                required
                value={formData.code}
                onChange={(e) => setFormData({ ...formData, code: e.target.value })}
                placeholder="QA"
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Head of Department</label>
            <input
              type="text"
              required
              value={formData.headOfDepartment}
              onChange={(e) => setFormData({ ...formData, headOfDepartment: e.target.value })}
              placeholder="e.g. Dr. Jane Foster"
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Mission / Scope Description</label>
            <textarea
              rows={3}
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              placeholder="Operational responsibilities and scope..."
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            />
          </div>

          <div className="pt-4 border-t border-slate-800 flex justify-end space-x-3">
            <button
              type="button"
              onClick={() => setIsModalOpen(false)}
              className="px-4 py-2 bg-slate-800 hover:bg-slate-700 text-slate-300 rounded-xl text-xs font-semibold transition-colors"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/30 transition-colors"
            >
              Save Department
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
