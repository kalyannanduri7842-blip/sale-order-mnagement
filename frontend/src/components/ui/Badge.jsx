import React from 'react';

export function Badge({ children, variant = 'default', className = '' }) {
  const variantStyles = {
    default: 'bg-slate-100 text-slate-700 border-slate-200',
    primary: 'bg-blue-50 text-blue-700 border-blue-200',
    success: 'bg-emerald-50 text-emerald-700 border-emerald-200',
    warning: 'bg-amber-50 text-amber-700 border-amber-200',
    danger: 'bg-rose-50 text-rose-700 border-rose-200',
    info: 'bg-sky-50 text-sky-700 border-sky-200',
    purple: 'bg-purple-50 text-purple-700 border-purple-200',
  };

  // Status text automatic matching
  const text = typeof children === 'string' ? children.toUpperCase() : '';
  let autoVariant = variant;

  if (['ACTIVE', 'COMPLETED', 'APPROVED', 'PAID', 'DELIVERED', 'OPERATIONAL', 'WON', 'IN STOCK'].includes(text)) {
    autoVariant = 'success';
  } else if (['PENDING', 'IN_PROGRESS', 'IN_TRANSIT', 'ISSUED', 'PROPOSAL', 'QUALIFIED', 'HALF_DAY', 'LOW STOCK'].includes(text)) {
    autoVariant = 'warning';
  } else if (['REJECTED', 'TERMINATED', 'CANCELLED', 'DISPOSED', 'LOST', 'LOW_STOCK', 'OUT OF STOCK'].includes(text)) {
    autoVariant = 'danger';
  } else if (['LATE', 'REVIEW', 'CONTACTED'].includes(text)) {
    autoVariant = 'purple';
  }

  const selected = variantStyles[autoVariant] || variantStyles.default;

  return (
    <span
      className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-[11px] font-bold border ${selected} ${className}`}
    >
      {children}
    </span>
  );
}
