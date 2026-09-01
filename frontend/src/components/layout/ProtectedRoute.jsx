import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

export function ProtectedRoute({ allowedRoles }) {
  const { isAuthenticated, checkRole } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles && !checkRole(allowedRoles)) {
    return (
      <div className="p-8 text-center bg-slate-900 border border-slate-800 rounded-2xl max-w-lg mx-auto mt-12">
        <h3 className="text-lg font-bold text-rose-400">Access Restricted</h3>
        <p className="text-xs text-slate-400 mt-2">
          Your current enterprise role does not have authorization to view this module.
          Switch roles using the sidebar role switcher demo to test permissions.
        </p>
      </div>
    );
  }

  return <Outlet />;
}
