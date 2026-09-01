import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { Users, Plus, Trash2, Mail, Phone } from 'lucide-react';

export function EmployeeDirectory() {
  const { employees, addEmployee, deleteEmployee } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    departmentName: 'Engineering & IT',
    designationTitle: 'Software Engineer',
    salary: 110000,
    employmentType: 'FULL_TIME'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addEmployee({
      ...formData,
      salary: parseFloat(formData.salary) || 50000
    });
    addToast('Employee Registered', `${formData.firstName} ${formData.lastName} added to organization.`, 'success');
    setIsModalOpen(false);
    setFormData({
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
      departmentName: 'Engineering & IT',
      designationTitle: 'Software Engineer',
      salary: 110000,
      employmentType: 'FULL_TIME'
    });
  };

  const handleDelete = (id, name) => {
    if (window.confirm(`Are you sure you want to remove staff member: ${name}?`)) {
      deleteEmployee(id);
      addToast('Employee Removed', `${name} removed from active roster.`, 'info');
    }
  };

  const formatCurrency = (val) =>
    new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val || 0);

  const columns = [
    {
      header: 'EMP ID',
      accessor: 'employeeId',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'EMP-NEW'}</span>
    },
    {
      header: 'Full Name',
      accessor: 'firstName',
      render: (val, row) => (
        <div>
          <span className="font-bold text-slate-900 block">{val} {row.lastName}</span>
          <span className="text-[10px] text-slate-500 font-medium">{row.designationTitle || 'Staff Member'}</span>
        </div>
      )
    },
    {
      header: 'Department',
      accessor: 'departmentName',
      render: (val) => <Badge variant="primary">{val || 'Operations'}</Badge>
    },
    {
      header: 'Contact',
      accessor: 'email',
      render: (val, row) => (
        <div className="text-xs text-slate-700">
          <div>{val}</div>
          <div className="text-[10px] text-slate-400 font-medium">{row.phone || 'N/A'}</div>
        </div>
      )
    },
    {
      header: 'Annual Salary',
      accessor: 'salary',
      render: (val) => <span className="font-mono font-bold text-emerald-600">{formatCurrency(val)}</span>
    },
    {
      header: 'Status',
      accessor: 'employmentStatus',
      render: (val) => <Badge variant="success">{val || 'ACTIVE'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, `${row.firstName} ${row.lastName}`)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Employee"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Human Resources & Employee Directory"
        description="Comprehensive personnel directory, job titles, department allocations, compensation and employment terms"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Add Staff Member
          </button>
        }
      />

      <DataTable
        title="Active Personnel Roster"
        columns={columns}
        data={employees}
        searchPlaceholder="Search employees by name, department..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register Staff Member">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">First Name</label>
              <input
                type="text"
                required
                value={formData.firstName}
                onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                placeholder="e.g. Marcus"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Last Name</label>
              <input
                type="text"
                required
                value={formData.lastName}
                onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
                placeholder="e.g. Vance"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Corporate Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                placeholder="marcus.vance@elevitiq.com"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Contact Phone</label>
              <input
                type="text"
                value={formData.phone}
                onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                placeholder="+1-555-0199"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Department</label>
              <select
                value={formData.departmentName}
                onChange={(e) => setFormData({ ...formData, departmentName: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="Engineering & IT">Engineering & IT</option>
                <option value="Human Resources">Human Resources</option>
                <option value="Finance & Accounts">Finance & Accounts</option>
                <option value="Sales & Marketing">Sales & Marketing</option>
                <option value="Operations & Logistics">Operations & Logistics</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Designation / Title</label>
              <input
                type="text"
                required
                value={formData.designationTitle}
                onChange={(e) => setFormData({ ...formData, designationTitle: e.target.value })}
                placeholder="e.g. Lead Logistics Engineer"
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Annual Basic Salary ($)</label>
            <input
              type="number"
              required
              min={1000}
              value={formData.salary}
              onChange={(e) => setFormData({ ...formData, salary: e.target.value })}
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
              Register Employee
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
