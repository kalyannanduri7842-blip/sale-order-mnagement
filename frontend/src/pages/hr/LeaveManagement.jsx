import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { api } from '../../services/api';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { CalendarPlus, CheckCheck, XCircle, Clock } from 'lucide-react';

export function LeaveManagement() {
  const { user } = useAuth();
  const [leaves, setLeaves] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    leaveType: 'ANNUAL',
    startDate: new Date().toISOString().split('T')[0],
    endDate: new Date().toISOString().split('T')[0],
    reason: ''
  });
  const { addToast } = useNotification();

  useEffect(() => {
    loadLeaves();
  }, []);

  const loadLeaves = async () => {
    const data = await api.get('/leaves', 'leaveRequests');
    setLeaves(Array.isArray(data) ? data : data?.content || []);
  };

  const handleApply = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      employeeId: user?.id || 1,
      employeeName: user?.fullName || 'Current User',
      employeeCode: 'EMP-1001',
      totalDays: 2,
      status: 'PENDING',
      createdAt: new Date().toISOString()
    };
    await api.post('/leaves', payload, 'leaveRequests');
    addToast('Leave Request Submitted', 'Forwarded to HR department for approval.', 'success');
    setIsModalOpen(false);
    loadLeaves();
  };

  const handleApprove = async (id) => {
    await api.put(`/leaves/${id}/approve`, { approverEmployeeId: user?.id || 1 }, 'leaveRequests');
    addToast('Leave Approved', 'Request marked as approved.', 'success');
    setLeaves(leaves.map((l) => (l.id === id ? { ...l, status: 'APPROVED', approvedByName: user?.fullName || 'Manager' } : l)));
  };

  const handleReject = async (id) => {
    await api.put(`/leaves/${id}/reject`, { approverEmployeeId: user?.id || 1, reason: 'Department requirements' }, 'leaveRequests');
    addToast('Leave Rejected', 'Request marked as rejected.', 'warning');
    setLeaves(leaves.map((l) => (l.id === id ? { ...l, status: 'REJECTED' } : l)));
  };

  const columns = [
    {
      header: 'Employee',
      accessor: 'employeeName',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <span className="text-[10px] text-slate-400">{row.departmentName}</span>
        </div>
      )
    },
    {
      header: 'Leave Type',
      accessor: 'leaveType',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Dates',
      accessor: 'startDate',
      render: (val, row) => (
        <span className="text-xs text-slate-300 font-mono">
          {val} to {row.endDate} ({row.totalDays} days)
        </span>
      )
    },
    {
      header: 'Reason',
      accessor: 'reason',
      render: (val) => <span className="text-xs text-slate-400 italic">"{val}"</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant="default">{val}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (val, row) => (
        <div className="flex items-center space-x-2">
          {row.status === 'PENDING' && (
            <>
              <button
                onClick={() => handleApprove(val)}
                className="p-1.5 rounded-lg bg-emerald-950 hover:bg-emerald-900 border border-emerald-700/50 text-emerald-300 text-xs font-semibold flex items-center transition-colors"
                title="Approve"
              >
                <CheckCheck className="w-3.5 h-3.5 mr-1" /> Approve
              </button>
              <button
                onClick={() => handleReject(val)}
                className="p-1.5 rounded-lg bg-rose-950 hover:bg-rose-900 border border-rose-700/50 text-rose-300 text-xs font-semibold flex items-center transition-colors"
                title="Reject"
              >
                <XCircle className="w-3.5 h-3.5 mr-1" /> Reject
              </button>
            </>
          )}
          {row.status === 'APPROVED' && (
            <span className="text-[10px] text-slate-400">Approved by {row.approvedByName || 'HR'}</span>
          )}
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Leave Management & Paid Time Off (PTO)"
        description="Review vacation, sick leave and casual applications with multi-tier managerial approval workflows"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="flex items-center px-3 py-2 bg-blue-600 hover:bg-blue-500 text-white rounded-xl text-xs font-semibold shadow-lg shadow-blue-600/20 transition-all"
          >
            <CalendarPlus className="w-3.5 h-3.5 mr-1.5" /> Apply for Leave
          </button>
        }
      />

      <DataTable
        title="Leave Applications Feed"
        columns={columns}
        data={leaves}
        searchPlaceholder="Search leaves..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Submit Leave Request">
        <form onSubmit={handleApply} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Leave Category</label>
            <select
              value={formData.leaveType}
              onChange={(e) => setFormData({ ...formData, leaveType: e.target.value })}
              className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
            >
              <option value="ANNUAL">Annual Vacation Leave</option>
              <option value="SICK">Medical & Sick Leave</option>
              <option value="CASUAL">Casual Personal Leave</option>
              <option value="MATERNITY_PATERNITY">Parental Leave</option>
              <option value="UNPAID">Unpaid Leave</option>
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">From Date</label>
              <input
                type="date"
                required
                value={formData.startDate}
                onChange={(e) => setFormData({ ...formData, startDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-semibold text-slate-300 mb-1">To Date</label>
              <input
                type="date"
                required
                value={formData.endDate}
                onChange={(e) => setFormData({ ...formData, endDate: e.target.value })}
                className="w-full bg-slate-950 border border-slate-700/80 rounded-xl px-3 py-2 text-xs text-white focus:outline-none focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold text-slate-300 mb-1">Reason for Absence</label>
            <textarea
              required
              rows={3}
              value={formData.reason}
              onChange={(e) => setFormData({ ...formData, reason: e.target.value })}
              placeholder="State the reason..."
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
              Submit Application
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
