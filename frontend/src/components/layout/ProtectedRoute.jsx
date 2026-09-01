import React from 'react';
import { Outlet } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

export function ProtectedRoute({ allowedRoles }) {
  const { user, checkRole } = useAuth();

  if (allowedRoles && !checkRole(allowedRoles)) {
    return (
      <div className="p-8 text-center bg-white border border-slate-200 rounded-3xl max-w-lg mx-auto mt-12 shadow-sm">
        <h3 className="text-lg font-bold text-rose-600">Access Restricted</h3>
        <p className="text-xs text-slate-500 mt-2 font-medium">
          Your current enterprise role does not have authorization to view this module.
          Switch roles using the top navigation role switcher to test permissions.
        </p>
      </div>
    );
  }

  return <Outlet />;
}
