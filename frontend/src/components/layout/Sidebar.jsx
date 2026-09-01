import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import {
  LayoutDashboard,
  Users,
  Building2,
  CalendarCheck,
  CalendarDays,
  CreditCard,
  BookOpen,
  DollarSign,
  Receipt,
  FileSpreadsheet,
  Package,
  ArrowLeftRight,
  Warehouse,
  Truck,
  ShoppingCart,
  Contact2,
  BadgeDollarSign,
  FileCheck2,
  Flame,
  KanbanSquare,
  Cpu,
  Boxes,
  ShieldAlert,
  Settings,
  ChevronDown,
  ChevronRight,
  Layers,
  ChevronLeft,
  UserCheck,
  FileText
} from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { MODULE_PERMISSIONS, hasPermission } from '../../constants/roles';

export function Sidebar() {
  const { user, checkRole, switchRoleDemo } = useAuth();
  const [collapsed, setCollapsed] = useState(false);
  const [openSections, setOpenSections] = useState({
    sales: true,
    procurement: true,
    inventory: true,
    finance: true,
    hr: true,
    reports: true,
    system: true
  });

  const toggleSection = (section) => {
    setOpenSections((prev) => ({ ...prev, [section]: !prev[section] }));
  };

  const navGroups = [
    {
      id: 'core',
      title: 'CORE',
      items: [
        { path: '/dashboard', label: 'Executive Dashboard', icon: LayoutDashboard, permission: MODULE_PERMISSIONS.DASHBOARD }
      ]
    },
    {
      id: 'sales',
      title: 'SALES',
      permission: MODULE_PERMISSIONS.SALES,
      items: [
        { path: '/sales/customers', label: 'Customers', icon: Contact2 },
        { path: '/sales/crm', label: 'Leads & Pipeline', icon: Flame },
        { path: '/sales/orders', label: 'Sales Orders', icon: BadgeDollarSign },
        { path: '/sales/invoices', label: 'Invoices & Billing', icon: FileCheck2 }
      ]
    },
    {
      id: 'procurement',
      title: 'PROCUREMENT',
      permission: MODULE_PERMISSIONS.PROCUREMENT,
      items: [
        { path: '/procurement/vendors', label: 'Suppliers & Vendors', icon: Truck },
        { path: '/procurement/orders', label: 'Purchase Orders', icon: ShoppingCart }
      ]
    },
    {
      id: 'inventory',
      title: 'INVENTORY',
      permission: MODULE_PERMISSIONS.INVENTORY,
      items: [
        { path: '/inventory/products', label: 'Products Catalog', icon: Package },
        { path: '/inventory/movements', label: 'Stock Movements', icon: ArrowLeftRight },
        { path: '/inventory/warehouses', label: 'Warehouses', icon: Warehouse }
      ]
    },
    {
      id: 'finance',
      title: 'FINANCE',
      permission: MODULE_PERMISSIONS.FINANCE,
      items: [
        { path: '/finance/accounts', label: 'Chart of Accounts', icon: BookOpen },
        { path: '/finance/journal', label: 'Transactions & Journal', icon: DollarSign },
        { path: '/finance/expenses', label: 'Expense Ledger', icon: Receipt },
        { path: '/finance/reports', label: 'Financial Reports', icon: FileSpreadsheet }
      ]
    },
    {
      id: 'hr',
      title: 'HUMAN RESOURCES',
      permission: MODULE_PERMISSIONS.HR,
      items: [
        { path: '/hr/employees', label: 'Employees Directory', icon: Users },
        { path: '/hr/departments', label: 'Departments & Org', icon: Building2 },
        { path: '/hr/attendance', label: 'Attendance Tracker', icon: CalendarCheck },
        { path: '/hr/leaves', label: 'Leave Requests', icon: CalendarDays },
        { path: '/hr/payroll', label: 'Payroll & Salaries', icon: CreditCard }
      ]
    },
    {
      id: 'system',
      title: 'REPORTS & SETTINGS',
      permission: MODULE_PERMISSIONS.AUDIT,
      items: [
        { path: '/finance/reports', label: 'Executive Reports', icon: FileText },
        { path: '/system/audit', label: 'Security Audit Log', icon: ShieldAlert },
        { path: '/system/settings', label: 'Company Settings', icon: Settings }
      ]
    }
  ];

  return (
    <aside
      className={`fixed left-0 top-0 bottom-0 z-40 bg-white border-r border-slate-200 flex flex-col transition-all duration-300 shadow-sm ${
        collapsed ? 'w-20' : 'w-64'
      }`}
    >
      {/* Brand Header */}
      <div className="h-16 px-4 border-b border-slate-200 flex items-center justify-between bg-slate-50/50">
        {!collapsed && (
          <div className="flex items-center space-x-2.5">
            <div className="w-8 h-8 rounded-xl bg-blue-600 flex items-center justify-center font-black text-white text-base shadow-sm">
              E
            </div>
            <div>
              <span className="font-black tracking-tight text-slate-900 text-sm block">ENTERPRISEPRO</span>
              <span className="text-[10px] font-bold tracking-wider text-blue-600 uppercase block -mt-1">
                ERP System
              </span>
            </div>
          </div>
        )}

        {collapsed && (
          <div className="w-8 h-8 rounded-xl bg-blue-600 flex items-center justify-center font-black text-white text-base shadow-sm mx-auto">
            E
          </div>
        )}

        <button
          onClick={() => setCollapsed(!collapsed)}
          className="p-1.5 rounded-lg text-slate-400 hover:text-slate-700 hover:bg-slate-100 transition-colors"
          title="Toggle Sidebar"
        >
          <ChevronLeft className={`w-4 h-4 transition-transform duration-300 ${collapsed ? 'rotate-180' : ''}`} />
        </button>
      </div>

      {/* Role Persona Switcher Widget */}
      {!collapsed && (
        <div className="p-3 mx-3 my-2 rounded-xl bg-slate-50 border border-slate-200 text-xs">
          <div className="flex items-center justify-between mb-1.5 text-slate-500 font-bold">
            <span className="flex items-center text-[10px] uppercase tracking-wider">
              <UserCheck className="w-3.5 h-3.5 mr-1 text-blue-600" /> Active Role Persona
            </span>
          </div>
          <select
            onChange={(e) => switchRoleDemo(e.target.value)}
            className="w-full bg-white border border-slate-200 rounded-lg px-2 py-1 text-xs font-bold text-slate-800 focus:outline-none focus:border-blue-500 shadow-2xs"
          >
            <option value="SUPER_ADMIN">👑 Super Admin / Owner</option>
            <option value="SALES_MANAGER">📈 Sales Manager</option>
            <option value="PROCUREMENT_MANAGER">🛒 Procurement Manager</option>
            <option value="INVENTORY_MANAGER">📦 Inventory Manager</option>
            <option value="FINANCE_MANAGER">💰 Finance Manager</option>
            <option value="HR_MANAGER">👥 HR Manager</option>
            <option value="PROJECT_MANAGER">👤 Employee</option>
          </select>
        </div>
      )}

      {/* Navigation Links */}
      <div className="flex-1 overflow-y-auto px-3 py-2 space-y-3">
        {navGroups.map((group) => {
          if (group.permission && !checkRole(group.permission)) return null;

          return (
            <div key={group.id} className="space-y-0.5">
              {!collapsed && (
                <div
                  onClick={() => group.id !== 'core' && toggleSection(group.id)}
                  className="flex items-center justify-between px-3 py-1 text-[10px] font-black text-slate-400 tracking-wider uppercase cursor-pointer hover:text-slate-600 select-none"
                >
                  <span>{group.title}</span>
                  {group.id !== 'core' && (
                    <ChevronDown
                      className={`w-3 h-3 transition-transform duration-200 ${
                        openSections[group.id] ? '' : '-rotate-90'
                      }`}
                    />
                  )}
                </div>
              )}

              {(collapsed || group.id === 'core' || openSections[group.id]) && (
                <div className="space-y-0.5">
                  {group.items.map((item) => {
                    const Icon = item.icon;
                    return (
                      <NavLink
                        key={item.path}
                        to={item.path}
                        className={({ isActive }) =>
                          `flex items-center px-3 py-2 rounded-xl text-xs font-bold transition-all ${
                            isActive
                              ? 'bg-blue-600 text-white shadow-sm'
                              : 'text-slate-600 hover:text-slate-900 hover:bg-slate-100'
                          } ${collapsed ? 'justify-center' : ''}`
                        }
                        title={collapsed ? item.label : undefined}
                      >
                        <Icon className={`w-4 h-4 flex-shrink-0 ${collapsed ? '' : 'mr-3'}`} />
                        {!collapsed && <span className="truncate">{item.label}</span>}
                      </NavLink>
                    );
                  })}
                </div>
              )}
            </div>
          );
        })}
      </div>

      {/* User Footer Profile */}
      <div className="p-3 border-t border-slate-200 bg-slate-50 flex items-center justify-between">
        <div className="flex items-center space-x-2.5 overflow-hidden">
          <div className="w-8 h-8 rounded-xl bg-blue-600 text-white font-bold flex items-center justify-center text-xs flex-shrink-0 shadow-2xs">
            {user?.fullName?.charAt(0) || 'A'}
          </div>
          {!collapsed && (
            <div className="overflow-hidden">
              <p className="text-xs font-bold text-slate-900 truncate">{user?.fullName || 'Administrator'}</p>
              <p className="text-[10px] text-slate-500 font-semibold truncate">{user?.roles?.[0]?.replace('ROLE_', '') || 'SUPER_ADMIN'}</p>
            </div>
          )}
        </div>
      </div>
    </aside>
  );
}
