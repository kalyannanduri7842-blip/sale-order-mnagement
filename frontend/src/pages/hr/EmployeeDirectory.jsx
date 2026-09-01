import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useNotification } from '../../context/NotificationContext';
import { UserPlus, Mail, Phone, Building, Briefcase, DollarSign } from 'lucide-react';

export function EmployeeDirectory() {
  const [employees, setEmployees] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    departmentId: '',
    departmentName: '',
    designationTitle: '',
    joiningDate: new Date().toISOString().split('T')[0],
    salary: 75000,
    employmentStatus: 'ACTIVE',
    employmentType: 'FULL_TIME'
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    const empData = await api.get('/employees/active', 'employees');
    setEmployees(Array.isArray(empData) ? empData : empData?.content || []);
    const deptData = await api.get('/departments/active', 'departments');
    setDepartments(Array.isArray(deptData) ? deptData : deptData?.content || []);
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const dept = departments.find((d) => d.id === Number(formData.departmentId));
    const payload = {
      ...formData,
      departmentName: dept ? dept.name : formData.departmentName,
      employeeId: 'EMP-' + (1000 + employees.length + 1)
    };

    await api.post('/employees', payload, 'employees');
    addToast('Employee Created', `${formData.firstName} ${formData.lastName} added to active directory`, 'success');
    setIsModalOpen(false);
    loadData();
  };

  const columns = [
    {
      header: 'Employee Code',
      accessor: 'employeeId',
      render: (val) => <span className="font-mono font-bold text-blue-400">{val}</span>
    },
    {
      header: 'Full Name',
      accessor: 'fullName',
      render: (_, row) => (
        <div className="flex items-center space-x-2.5">
          <div className="w-7 h-7 rounded-lg bg-gradient-to-tr from-blue-600 to-indigo-600 text-white font-bold flex items-center justify-center text-xs">
            {row.firstName?.[0]}
          </div>
          <div>
            <span className="font-semibold text-white block">{row.firstName} {row.lastName}</span>
            <span className="text-[10px] text-slate-400">{row.email}</span>
          </div>
        </div>
      )
    },
    {
      header: 'Department',
      accessor: 'departmentName',
      render: (val) => <Badge variant="primary">{val || 'General'}</Badge>
    },
    {
      header: 'Designation',
      accessor: 'designationTitle',
      render: (val) => <span className="text-slate-300 font-medium">{val || 'Specialist'}</span>
    },
    {
      header: 'Annual Salary',
      accessor: 'salary',
      render: (val) => <span className="font-mono font-semibold text-emerald-400">${Number(val || 0).toLocaleString()}</span>
    },
    {
      header: 'Status',
      accessor: 'employmentStatus',
      render: (val) => <Badge variant="success">{val || 'ACTIVE'}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Employee Directory & Roster"
        description="Manage organization workforce profiles, compensation, departments and designations"
        actions={
          <button
            onClick={() => {
              setFormData({
                firstName: '',
                lastName: '',
                email: '',
                phone: '',
                departmentId: departments[0]?.id || 1,
                departmentName: departments[0]?.name || 'Engineering',
                designationTitle: 'Software Engineer',
                joiningDate: new Date().toISOString().split('T')[0],
                salary: 80000,
                employmentStatus: 'ACTIVE',
                employmentType: 'FULL_TIME'
              });
              setIsModalOpen(true);
            }}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <UserPlus className="w-3.5 h-3.5 mr-1.5" /> Add New Employee
          </button>
        }
      />

      <DataTable
        title="Active Workforce Roster"
        columns={columns}
        data={employees}
        searchPlaceholder="Search by name, ID, department..."
      />

      {/* Add Employee Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Register New Employee">
        <form onSubmit={handleSave} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">First Name</label>
              <input
                type="text"
                required
                value={formData.firstName}
                onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Last Name</label>
              <input
                type="text"
                required
                value={formData.lastName}
                onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Work Email</label>
              <input
                type="email"
                required
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Phone Number</label>
              <input
                type="text"
                value={formData.phone}
                onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Department</label>
              <select
                value={formData.departmentId}
                onChange={(e) => setFormData({ ...formData, departmentId: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              >
                {departments.map((d) => (
                  <option key={d.id} value={d.id}>
                    {d.name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Designation</label>
              <input
                type="text"
                required
                value={formData.designationTitle}
                onChange={(e) => setFormData({ ...formData, designationTitle: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Annual Salary ($)</label>
              <input
                type="number"
                required
                value={formData.salary}
                onChange={(e) => setFormData({ ...formData, salary: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">Joining Date</label>
              <input
                type="date"
                value={formData.joiningDate}
                onChange={(e) => setFormData({ ...formData, joiningDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
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
              Save Employee Record
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
