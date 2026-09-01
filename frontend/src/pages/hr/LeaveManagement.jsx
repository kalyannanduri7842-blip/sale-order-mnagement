import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { CalendarDays, Plus, Trash2, CheckCircle2, XCircle } from 'lucide-react';

export function LeaveManagement() {
  const { leaveRequests, addLeaveRequest, updateLeaveStatus, deleteLeaveRequest, employees } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    employeeName: employees[0]?.firstName ? `${employees[0].firstName} ${employees[0].lastName}` : 'Alex Rivers',
    leaveType: 'ANNUAL',
    startDate: new Date().toISOString().split('T')[0],
    endDate: new Date(Date.now() + 3 * 86400000).toISOString().split('T')[0],
    totalDays: 3,
    reason: 'Personal vacation'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addLeaveRequest(formData);
    addToast('Leave Requested', `Leave application submitted for ${formData.employeeName}.`, 'success');
    setIsModalOpen(false);
  };

  const handleApprove = (id, emp) => {
    updateLeaveStatus(id, 'APPROVED');
    addToast('Leave Approved', `Leave approved for ${emp}.`, 'success');
  };

  const handleReject = (id, emp) => {
    updateLeaveStatus(id, 'REJECTED');
    addToast('Leave Rejected', `Leave rejected for ${emp}.`, 'info');
  };

  const handleDelete = (id, emp) => {
    if (window.confirm(`Delete leave application for ${emp}?`)) {
      deleteLeaveRequest(id);
      addToast('Application Deleted', 'Leave application removed.', 'info');
    }
  };

  const columns = [
    {
      header: 'Staff Member',
      accessor: 'employeeName',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Leave Category',
      accessor: 'leaveType',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Start Date',
      accessor: 'startDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val}</span>
    },
    {
      header: 'End Date',
      accessor: 'endDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val}</span>
    },
    {
      header: 'Duration',
      accessor: 'totalDays',
      render: (val) => <span className="font-mono font-bold text-slate-900">{val} days</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => (
        <Badge variant={val === 'APPROVED' ? 'success' : val === 'REJECTED' ? 'danger' : 'warning'}>
          {val}
        </Badge>
      )
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <div className="flex items-center space-x-2">
          {row.status === 'PENDING' && (
            <>
              <button
                onClick={() => handleApprove(id, row.employeeName)}
                className="px-2 py-1 bg-emerald-50 text-emerald-700 hover:bg-emerald-100 rounded-lg text-xs font-bold transition-colors"
                title="Approve"
              >
                Approve
              </button>
              <button
                onClick={() => handleReject(id, row.employeeName)}
                className="px-2 py-1 bg-rose-50 text-rose-700 hover:bg-rose-100 rounded-lg text-xs font-bold transition-colors"
                title="Reject"
              >
                Reject
              </button>
            </>
          )}
          <button
            onClick={() => handleDelete(id, row.employeeName)}
            className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
            title="Delete Application"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Leave Management & Time-Off Approvals"
        description="Employee time-off requests, annual vacation allotments, medical leaves and manager approval workflow"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Submit Leave Request
          </button>
        }
      />

      <DataTable
        title="Time-Off Applications"
        columns={columns}
        data={leaveRequests}
        searchPlaceholder="Search leave requests..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Submit Leave Request">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Staff Member</label>
            <select
              value={formData.employeeName}
              onChange={(e) => setFormData({ ...formData, employeeName: e.target.value })}
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
            >
              {employees.map((emp) => (
                <option key={emp.id} value={`${emp.firstName} ${emp.lastName}`}>
                  {emp.firstName} {emp.lastName} ({emp.departmentName})
                </option>
              ))}
              {employees.length === 0 && <option value="Alex Rivers">Alex Rivers</option>}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Leave Type</label>
              <select
                value={formData.leaveType}
                onChange={(e) => setFormData({ ...formData, leaveType: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="ANNUAL">Annual Paid Vacation</option>
                <option value="SICK">Sick & Medical Leave</option>
                <option value="CASUAL">Casual Day-Off</option>
                <option value="UNPAID">Unpaid Personal Leave</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Total Days</label>
              <input
                type="number"
                required
                min={1}
                value={formData.totalDays}
                onChange={(e) => setFormData({ ...formData, totalDays: Number(e.target.value) })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500 focus:bg-white"
              />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Start Date</label>
              <input
                type="date"
                required
                value={formData.startDate}
                onChange={(e) => setFormData({ ...formData, startDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">End Date</label>
              <input
                type="date"
                required
                value={formData.endDate}
                onChange={(e) => setFormData({ ...formData, endDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Reason / Justification</label>
            <textarea
              rows={3}
              value={formData.reason}
              onChange={(e) => setFormData({ ...formData, reason: e.target.value })}
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
              Submit Application
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
