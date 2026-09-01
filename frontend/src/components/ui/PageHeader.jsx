import React from 'react';

export function PageHeader({ title, description, actions, children }) {
  return (
    <div className="mb-6 flex flex-col md:flex-row md:items-center md:justify-between gap-4 pb-4 border-b border-slate-200">
      <div>
        <h1 className="text-2xl font-black text-slate-900 tracking-tight">{title}</h1>
        {description && <p className="text-xs text-slate-500 font-medium mt-1">{description}</p>}
      </div>
      {(actions || children) && (
        <div className="flex flex-wrap items-center gap-2.5">
          {actions}
          {children}
        </div>
      )}
    </div>
  );
}
