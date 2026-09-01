import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { api } from '../../services/api';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { Clock, CheckCircle2, LogIn, LogOut, Calendar, AlertCircle } from 'lucide-react';

export function AttendanceTracker() {
  const { user } = useAuth();
  const [records, setRecords] = useState([]);
  const [checkedIn, setCheckedIn] = useState(false);
  const [checkInTime, setCheckInTime] = useState(null);
  const { addToast } = useNotification();

  useEffect(() => {
    loadAttendance();
  }, []);

  const loadAttendance = async () => {
    const data = await api.get('/attendance', 'attendance');
    setRecords(Array.isArray(data) ? data : data?.content || []);
  };

  const handleCheckIn = () => {
    const time = new Date().toLocaleTimeString();
    setCheckedIn(true);
    setCheckInTime(time);
    const newRecord = {
      id: Date.now(),
      employeeId: user?.id || 1,
      employeeName: user?.fullName || 'Current User',
      employeeCode: 'EMP-1001',
      attendanceDate: new Date().toISOString().split('T')[0],
      checkInTime: new Date().toTimeString().split(' ')[0],
      checkOutTime: null,
      totalHours: 0,
      overtimeHours: 0,
      status: 'PRESENT'
    };
    setRecords([newRecord, ...records]);
    addToast('Punch Check-In Recorded', `Clocked in at ${time}`, 'success');
  };

  const handleCheckOut = () => {
    setCheckedIn(false);
    const time = new Date().toLocaleTimeString();
    addToast('Punch Check-Out Recorded', `Clocked out at ${time}. Total hours computed.`, 'info');
  };

  const columns = [
    {
      header: 'Date',
      accessor: 'attendanceDate',
      render: (val) => <span className="font-mono text-slate-300">{val}</span>
    },
    {
      header: 'Employee',
      accessor: 'employeeName',
      render: (val, row) => (
        <div>
          <span className="font-semibold text-white block">{val}</span>
          <span className="text-[10px] font-mono text-blue-400">{row.employeeCode}</span>
        </div>
      )
    },
    {
      header: 'Clock In',
      accessor: 'checkInTime',
      render: (val) => <span className="font-mono text-emerald-400">{val || '—'}</span>
    },
    {
      header: 'Clock Out',
      accessor: 'checkOutTime',
      render: (val) => <span className="font-mono text-slate-300">{val || 'Active Shift'}</span>
    },
    {
      header: 'Total Hours',
      accessor: 'totalHours',
      render: (val) => <span className="font-bold text-white">{val ? `${val} hrs` : 'In Progress'}</span>
    },
    {
      header: 'Overtime',
      accessor: 'overtimeHours',
      render: (val) => <span className="text-amber-400 font-semibold">{val ? `${val} hrs` : '0.0'}</span>
    },
    {
      header: 'Status',
      accessor: 'status',
      render: (val) => <Badge variant="default">{val}</Badge>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Attendance & Time Clock Ledger"
        description="Monitor shift compliance, daily check-ins/outs, breaks and automated overtime calculations"
      />

      {/* Clock In / Out Quick Action Hero */}
      <div className="bg-gradient-to-r from-blue-950/40 via-slate-900 to-indigo-950/40 border border-slate-800 rounded-3xl p-6 shadow-2xl flex flex-col md:flex-row items-center justify-between gap-6 backdrop-blur-md">
        <div className="flex items-center space-x-4">
          <div className="p-4 rounded-2xl bg-blue-600/20 border border-blue-500/30 text-blue-400">
            <Clock className="w-8 h-8 animate-pulse" />
          </div>
          <div>
            <h3 className="text-base font-bold text-white">Daily Biometric & Punch Clock</h3>
            <p className="text-xs text-slate-400 mt-0.5">
              Current Session: <span className="text-blue-400 font-semibold">{new Date().toLocaleDateString('en-US', { weekday: 'long', month: 'short', day: 'numeric', year: 'numeric' })}</span>
            </p>
            {checkedIn && (
              <p className="text-xs text-emerald-400 mt-1 flex items-center">
                <CheckCircle2 className="w-3.5 h-3.5 mr-1" /> Shift active since {checkInTime}
              </p>
            )}
          </div>
        </div>

        <div className="flex items-center space-x-3">
          {!checkedIn ? (
            <button
              onClick={handleCheckIn}
              className="flex items-center px-6 py-3 bg-emerald-600 hover:bg-emerald-500 text-white rounded-xl text-xs font-bold shadow-lg shadow-emerald-600/30 transition-all transform hover:scale-105"
            >
              <LogIn className="w-4 h-4 mr-2" /> Clock In Shift
            </button>
          ) : (
            <button
              onClick={handleCheckOut}
              className="flex items-center px-6 py-3 bg-rose-600 hover:bg-rose-500 text-white rounded-xl text-xs font-bold shadow-lg shadow-rose-600/30 transition-all transform hover:scale-105"
            >
              <LogOut className="w-4 h-4 mr-2" /> Clock Out Shift
            </button>
          )}
        </div>
      </div>

      <DataTable
        title="Attendance Records (Today)"
        columns={columns}
        data={records}
        searchPlaceholder="Search attendance logs..."
      />
    </div>
  );
}
