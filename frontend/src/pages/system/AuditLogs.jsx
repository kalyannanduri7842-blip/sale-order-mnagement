import React, { useState, useEffect } from 'react';
import { PageHeader } from '../../components/ui/PageHeader';
import { DataTable } from '../../components/ui/DataTable';
import { Badge } from '../../components/ui/Badge';
import { api } from '../../services/api';
import { ShieldAlert, Terminal, Lock, UserCheck, Shield } from 'lucide-react';

export function AuditLogs() {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    loadLogs();
  }, []);

  const loadLogs = async () => {
    const data = await api.get('/audit/logs', 'auditLogs');
    setLogs(Array.isArray(data) ? data : data?.content || []);
  };

  const columns = [
    {
      header: 'Timestamp',
      accessor: 'timestamp',
      render: (val) => <span className="font-mono text-slate-400 text-xs">{new Date(val).toLocaleString()}</span>
    },
    {
      header: 'User Principal',
      accessor: 'username',
      render: (val) => (
        <div className="flex items-center space-x-1.5 font-semibold text-white">
          <UserCheck className="w-3.5 h-3.5 text-blue-400" />
          <span>{val}</span>
        </div>
      )
    },
    {
      header: 'Event Action',
      accessor: 'action',
      render: (val) => <span className="font-mono font-bold text-slate-200">{val}</span>
    },
    {
      header: 'Module Scope',
      accessor: 'module',
      render: (val) => <Badge variant="primary">{val}</Badge>
    },
    {
      header: 'Activity Description',
      accessor: 'description',
      render: (val) => <span className="text-slate-300 text-xs">{val}</span>
    },
    {
      header: 'Origin IP',
      accessor: 'ipAddress',
      render: (val) => <span className="font-mono text-slate-400 text-xs">{val || '127.0.0.1'}</span>
    }
  ];

  return (
    <div className="space-y-6">
      <PageHeader
        title="Security Audit Trail & Compliance Log"
        description="Immutable real-time audit ledger logging all administrative events, role changes, financial postings and logins"
      />

      <DataTable
        title="Security & Activity Audit Stream"
        columns={columns}
        data={logs}
        searchPlaceholder="Search audit events by user, action, module..."
      />
    </div>
  );
}
