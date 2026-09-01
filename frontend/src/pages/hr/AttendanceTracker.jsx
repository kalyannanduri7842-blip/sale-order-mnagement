import React, { useState } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { Modal } from '../../components/ui/Modal';
import { useErpData } from '../../context/ErpDataContext';
import { useNotification } from '../../context/NotificationContext';
import { CalendarCheck, Plus, Trash2, Clock } from 'lucide-react';

export function AttendanceTracker() {
  const { attendance, addAttendanceRecord, deleteAttendanceRecord, employees } = useErpData();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    employeeName: employees[0]?.firstName ? `${employees[0].firstName} ${employees[0].lastName}` : 'Alex Rivers',
    employeeCode: employees[0]?.employeeId || 'EMP-1001',
    attendanceDate: new Date().toISOString().split('T')[0],
    checkInTime: '09:00:00',
    checkOutTime: '17:30:00',
    totalHours: 8.5,
    status: 'PRESENT'
  });
  const { addToast } = useNotification();

  const handleSave = (e) => {
    e.preventDefault();
    addAttendanceRecord(formData);
    addToast('Attendance Logged', `Recorded punch-in for ${formData.employeeName}.`, 'success');
    setIsModalOpen(false);
  };

  const handleDelete = (id, emp) => {
    if (window.confirm(`Delete attendance record for ${emp}?`)) {
      deleteAttendanceRecord(id);
      addToast('Record Deleted', 'Attendance record deleted.', 'info');
    }
  };

  const columns = [
    {
      header: 'EMP Code',
      accessor: 'employeeCode',
      render: (val) => <span className="font-mono font-bold text-blue-600">{val || 'EMP-1001'}</span>
    },
    {
      header: 'Staff Member',
      accessor: 'employeeName',
      render: (val) => <span className="font-bold text-slate-900">{val}</span>
    },
    {
      header: 'Date',
      accessor: 'attendanceDate',
      render: (val) => <span className="font-mono text-slate-600 text-xs">{val || '2026-08-30'}</span>
    },
    {
      header: 'Clock In',
      accessor: 'checkInTime',
      render: (val) => <span className="font-mono text-slate-700">{val || '09:00:00'}</span>
    },
    {
      header: 'Clock Out',
      accessor: 'checkOutTime',
      render: (val) => <span className="font-mono text-slate-700">{val || '17:30:00'}</span>
    },
    {
      header: 'Hours Worked',
      accessor: 'totalHours',
      render: (val) => <span className="font-mono font-bold text-slate-900">{val || 8.5} hrs</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant={val === 'PRESENT' ? 'success' : 'warning'}>{val || 'PRESENT'}</Badge>
    },
    {
      header: 'Actions',
      accessor: 'id',
      sortable: false,
      render: (id, row) => (
        <button
          onClick={() => handleDelete(id, row.employeeName)}
          className="p-1.5 rounded-lg text-rose-500 hover:bg-rose-50 hover:text-rose-700 transition-colors"
          title="Delete Record"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Attendance Tracking & Biometric Time Logs"
        description="Daily employee punch-in records, overtime hours calculation, shift tracking and absence monitoring"
        actions={
          <button
            onClick={() => setIsModalOpen(true)}
            className="btn-primary flex items-center text-xs shadow-sm"
          >
            <Plus className="w-3.5 h-3.5 mr-1.5" /> Clock In / Add Record
          </button>
        }
      />

      <DataTable
        title="Daily Attendance Log"
        columns={columns}
        data={attendance}
        searchPlaceholder="Search attendance..."
      />

      {/* Modal */}
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} title="Log Attendance Punch Record">
        <form onSubmit={handleSave} className="space-y-4">
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">Select Employee</label>
            <select
              value={formData.employeeName}
              onChange={(e) => {
                const emp = employees.find(emp => `${emp.firstName} ${emp.lastName}` === e.target.value);
                setFormData({
                  ...formData,
                  employeeName: e.target.value,
                  employeeCode: emp?.employeeId || 'EMP-1001'
                });
              }}
              className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
            >
              {employees.map((emp) => (
                <option key={emp.id} value={`${emp.firstName} ${emp.lastName}`}>
                  {emp.firstName} {emp.lastName} ({emp.employeeId})
                </option>
              ))}
              {employees.length === 0 && <option value="Alex Rivers">Alex Rivers (EMP-1001)</option>}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Attendance Date</label>
              <input
                type="date"
                required
                value={formData.attendanceDate}
                onChange={(e) => setFormData({ ...formData, attendanceDate: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Status</label>
              <select
                value={formData.status}
                onChange={(e) => setFormData({ ...formData, status: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              >
                <option value="PRESENT">PRESENT</option>
                <option value="LATE">LATE</option>
                <option value="HALF_DAY">HALF DAY</option>
                <option value="ABSENT">ABSENT</option>
              </select>
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Check In Time</label>
              <input
                type="time"
                value={formData.checkInTime}
                onChange={(e) => setFormData({ ...formData, checkInTime: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Check Out Time</label>
              <input
                type="time"
                value={formData.checkOutTime}
                onChange={(e) => setFormData({ ...formData, checkOutTime: e.target.value })}
                className="w-full bg-slate-50 border border-slate-200 rounded-xl px-3 py-2 text-xs text-slate-900 font-medium focus:outline-none focus:border-blue-500"
              />
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
              Log Record
            </button>
          </div>
        </form>
      </Modal>
    </div>
  );
}
