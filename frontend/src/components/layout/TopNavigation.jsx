import React, { useState, useRef, useEffect } from 'react';
import { NavLink, useNavigate, useLocation } from 'react-router-dom';
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
  Layers,
  UserCheck,
  FileText,
  Search,
  Bell,
  LogOut,
  CheckCheck,
  Sparkles,
  HelpCircle,
  Activity,
  Globe
} from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { MODULE_PERMISSIONS } from '../../constants/roles';

export function TopNavigation() {
  const { user, logout, checkRole, switchRoleDemo } = useAuth();
  const { notifications, unreadCount, markAsRead, markAllAsRead } = useNotification();
  const [activeDropdown, setActiveDropdown] = useState(null);
  const [showNotifications, setShowNotifications] = useState(false);
  const [showUserMenu, setShowUserMenu] = useState(false);
  const [showCompanyMenu, setShowCompanyMenu] = useState(false);
  const [selectedCompany, setSelectedCompany] = useState('Elevit IQ');
  const [searchQuery, setSearchQuery] = useState('');
  
  const navigate = useNavigate();
  const location = useLocation();
  const navRef = useRef(null);

  // Close dropdown on outside click or route change
  useEffect(() => {
    setActiveDropdown(null);
    setShowNotifications(false);
    setShowUserMenu(false);
    setShowCompanyMenu(false);
  }, [location.pathname]);

  useEffect(() => {
    function handleClickOutside(event) {
      if (navRef.current && !navRef.current.contains(event.target)) {
        setActiveDropdown(null);
        setShowNotifications(false);
        setShowUserMenu(false);
        setShowCompanyMenu(false);
      }
    }
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const navModules = [
    {
      id: 'dashboard',
      label: 'Dashboard',
      icon: LayoutDashboard,
      path: '/dashboard',
      permission: MODULE_PERMISSIONS.DASHBOARD
    },
    {
      id: 'sales',
      label: 'Sales & CRM',
      icon: Contact2,
      basePath: '/sales',
      permission: MODULE_PERMISSIONS.SALES,
      items: [
        { path: '/sales/customers', label: 'Customer Directory', desc: 'Enterprise client CRM & accounts', icon: Contact2 },
        { path: '/sales/crm', label: 'Leads & Pipeline', desc: 'Opportunity Kanban & deals', icon: Flame },
        { path: '/sales/orders', label: 'Sales Orders', desc: 'Order processing & fulfillment', icon: BadgeDollarSign },
        { path: '/sales/invoices', label: 'Invoices & Billing', desc: 'Receivables & payment collection', icon: FileCheck2 }
      ]
    },
    {
      id: 'procurement',
      label: 'Procurement',
      icon: ShoppingCart,
      basePath: '/procurement',
      permission: MODULE_PERMISSIONS.PROCUREMENT,
      items: [
        { path: '/procurement/vendors', label: 'Vendor Directory', desc: 'Supplier database & scorecards', icon: Truck },
        { path: '/procurement/orders', label: 'Purchase Orders', desc: 'Requisitions, approvals & POs', icon: ShoppingCart }
      ]
    },
    {
      id: 'inventory',
      label: 'Inventory',
      icon: Package,
      basePath: '/inventory',
      permission: MODULE_PERMISSIONS.INVENTORY,
      items: [
        { path: '/inventory/products', label: 'Products Catalog', desc: 'SKUs, pricing & reorder thresholds', icon: Package },
        { path: '/inventory/movements', label: 'Stock Movements', desc: 'Inbound, outbound & adjustments', icon: ArrowLeftRight },
        { path: '/inventory/warehouses', label: 'Warehouses', desc: 'Multi-location inventory tracking', icon: Warehouse }
      ]
    },
    {
      id: 'finance',
      label: 'Finance',
      icon: DollarSign,
      basePath: '/finance',
      permission: MODULE_PERMISSIONS.FINANCE,
      items: [
        { path: '/finance/accounts', label: 'Chart of Accounts', desc: 'General ledger & account balances', icon: BookOpen },
        { path: '/finance/journal', label: 'Journal Entries', desc: 'Double-entry accounting records', icon: DollarSign },
        { path: '/finance/expenses', label: 'Expense Ledger', desc: 'Operating & travel disbursements', icon: Receipt },
        { path: '/finance/reports', label: 'Financial Reports', desc: 'P&L, cash flow & balance sheets', icon: FileSpreadsheet }
      ]
    },
    {
      id: 'hr',
      label: 'Human Resources',
      icon: Users,
      basePath: '/hr',
      permission: MODULE_PERMISSIONS.HR,
      items: [
        { path: '/hr/employees', label: 'Employees Directory', desc: 'Personnel records & profiles', icon: Users },
        { path: '/hr/departments', label: 'Departments & Org', desc: 'Headcount & budget allocation', icon: Building2 },
        { path: '/hr/attendance', label: 'Attendance Tracker', desc: 'Daily clock-in & overtime', icon: CalendarCheck },
        { path: '/hr/leaves', label: 'Leave Requests', desc: 'Time-off requests & approvals', icon: CalendarDays },
        { path: '/hr/payroll', label: 'Payroll & Salaries', desc: 'Payslips & salary disbursements', icon: CreditCard }
      ]
    },
    {
      id: 'operations',
      label: 'Operations',
      icon: KanbanSquare,
      basePath: '/operations',
      permission: MODULE_PERMISSIONS.OPERATIONS,
      items: [
        { path: '/operations/projects', label: 'Project Management', desc: 'Milestones & task delivery', icon: KanbanSquare },
        { path: '/operations/manufacturing', label: 'Manufacturing & BOM', desc: 'Bills of materials & assembly', icon: Cpu },
        { path: '/operations/supply-chain', label: 'Supply Chain Tracking', desc: 'Logistics & freight tracking', icon: Boxes },
        { path: '/operations/assets', label: 'Fixed Assets', desc: 'Equipment & depreciation ledger', icon: Layers }
      ]
    },
    {
      id: 'system',
      label: 'Compliance',
      icon: ShieldAlert,
      basePath: '/system',
      permission: MODULE_PERMISSIONS.AUDIT,
      items: [
        { path: '/system/audit', label: 'Security Audit Log', desc: 'Immutable SOC2 system trail', icon: ShieldAlert },
        { path: '/system/settings', label: 'Company Settings', desc: 'Tenant security & workspace profile', icon: Settings }
      ]
    }
  ];

  return (
    <header ref={navRef} className="sticky top-0 z-50 bg-[#0c1322] text-white shadow-xl border-b border-slate-800">
      {/* Tier 1: Main Brand Bar & Executive Utilities */}
      <div className="px-6 h-16 flex items-center justify-between border-b border-slate-800/80 bg-gradient-to-r from-[#0a0f1d] via-[#0d1627] to-[#0a0f1d]">
        {/* Brand Logo & Tagline */}
        <div className="flex items-center space-x-4 min-w-0">
          <Link to="/dashboard" className="flex items-center space-x-3 group">
            <div className="w-9 h-9 rounded-2xl bg-gradient-to-tr from-blue-600 via-indigo-600 to-cyan-400 flex items-center justify-center font-black text-white text-base shadow-lg shadow-blue-500/25 group-hover:scale-105 transition-transform">
              E
            </div>
            <div>
              <div className="flex items-center space-x-2">
                <span className="font-black tracking-tight text-white text-base leading-none">ENTERPRISEPRO</span>
                <span className="px-1.5 py-0.5 rounded-md bg-blue-500/20 text-cyan-300 font-extrabold text-[9px] border border-blue-400/30">
                  ERP OS
                </span>
              </div>
              <p className="text-[10px] font-semibold text-slate-400 tracking-wider uppercase mt-0.5">
                One System. Every Business Operation.
              </p>
            </div>
          </Link>

          {/* Company Workspace Picker */}
          <div className="relative hidden md:block">
            <button
              onClick={() => setShowCompanyMenu(!showCompanyMenu)}
              className="flex items-center space-x-2 px-3 py-1.5 rounded-xl bg-slate-900/90 border border-slate-800 hover:border-slate-700 text-xs font-bold text-slate-200 transition-colors"
            >
              <Building2 className="w-3.5 h-3.5 text-cyan-400" />
              <span className="truncate max-w-[130px]">{selectedCompany}</span>
              <ChevronDown className="w-3 h-3 text-slate-400" />
            </button>

            {showCompanyMenu && (
              <div className="absolute left-0 mt-2 w-56 rounded-2xl bg-[#0f172a] border border-slate-800 shadow-2xl p-2 z-50 animate-in fade-in text-xs">
                <div className="px-2 py-1 text-[10px] font-bold text-slate-400 uppercase">Switch Workspace:</div>
                {['Elevit IQ', 'Elevit Technologies Inc', 'Acme Global Operations'].map((comp) => (
                  <button
                    key={comp}
                    onClick={() => {
                      setSelectedCompany(comp);
                      setShowCompanyMenu(false);
                    }}
                    className={`w-full text-left px-2.5 py-1.5 rounded-xl font-bold transition flex items-center justify-between ${
                      selectedCompany === comp ? 'bg-blue-600/30 text-cyan-300 border border-blue-500/40' : 'text-slate-300 hover:bg-slate-800'
                    }`}
                  >
                    <span>{comp}</span>
                    {selectedCompany === comp && <span className="text-[9px] text-cyan-400 font-extrabold">Active</span>}
                  </button>
                ))}
              </div>
            )}
          </div>
        </div>

        {/* Global Fast Search Input */}
        <div className="hidden lg:flex items-center w-80 relative">
          <Search className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder="Global search (customers, products, orders)..."
            className="w-full bg-slate-900/80 border border-slate-800 rounded-xl pl-9 pr-8 py-2 text-xs text-white placeholder:text-slate-500 focus:outline-none focus:border-blue-500 focus:bg-slate-900 transition-all"
          />
          <kbd className="absolute right-2.5 top-1/2 -translate-y-1/2 px-1.5 py-0.5 rounded bg-slate-800 text-[10px] font-mono text-slate-400">
            ⌘K
          </kbd>
        </div>

        {/* Right Action Tools: Role Selector, Notifications, Profile */}
        <div className="flex items-center space-x-3">
          {/* Active Role Persona Dropdown */}
          <div className="hidden sm:flex items-center bg-slate-900/90 border border-slate-800 rounded-xl px-2.5 py-1 text-xs">
            <UserCheck className="w-3.5 h-3.5 mr-1.5 text-cyan-400 shrink-0" />
            <select
              onChange={(e) => switchRoleDemo(e.target.value)}
              className="bg-transparent text-xs font-bold text-cyan-300 focus:outline-none cursor-pointer pr-1"
            >
              <option value="SUPER_ADMIN" className="bg-[#0f172a] text-white">👑 Super Admin / Owner</option>
              <option value="SALES_MANAGER" className="bg-[#0f172a] text-white">📈 Sales Manager</option>
              <option value="PROCUREMENT_MANAGER" className="bg-[#0f172a] text-white">🛒 Procurement Lead</option>
              <option value="INVENTORY_MANAGER" className="bg-[#0f172a] text-white">📦 Inventory Lead</option>
              <option value="FINANCE_MANAGER" className="bg-[#0f172a] text-white">💰 Finance CFO</option>
              <option value="HR_MANAGER" className="bg-[#0f172a] text-white">👥 HR Manager</option>
              <option value="PROJECT_MANAGER" className="bg-[#0f172a] text-white">👤 Staff Employee</option>
            </select>
          </div>

          {/* Notifications Dropdown */}
          <div className="relative">
            <button
              onClick={() => setShowNotifications(!showNotifications)}
              className="p-2 rounded-xl bg-slate-900/80 border border-slate-800 text-slate-300 hover:text-white hover:border-slate-700 transition-colors relative"
            >
              <Bell className="w-4 h-4" />
              {unreadCount > 0 && (
                <span className="absolute -top-1 -right-1 w-4 h-4 bg-gradient-to-r from-blue-500 to-cyan-400 text-white rounded-full text-[10px] font-black flex items-center justify-center shadow-md animate-pulse">
                  {unreadCount}
                </span>
              )}
            </button>

            {showNotifications && (
              <div className="absolute right-0 mt-2 w-80 rounded-2xl bg-[#0f172a] border border-slate-800 shadow-2xl p-4 z-50 animate-in fade-in">
                <div className="flex items-center justify-between pb-2.5 border-b border-slate-800">
                  <span className="text-xs font-bold text-white uppercase tracking-wider">Enterprise Alerts</span>
                  <button
                    onClick={markAllAsRead}
                    className="text-[11px] font-bold text-cyan-400 hover:underline flex items-center"
                  >
                    <CheckCheck className="w-3.5 h-3.5 mr-1" /> Mark read
                  </button>
                </div>

                <div className="divide-y divide-slate-800 max-h-72 overflow-y-auto mt-1">
                  {notifications.length > 0 ? (
                    notifications.map((n) => (
                      <div
                        key={n.id}
                        onClick={() => markAsRead(n.id)}
                        className={`p-2.5 rounded-xl cursor-pointer hover:bg-slate-800/60 transition-colors ${
                          !n.isRead ? 'bg-blue-950/40 border border-blue-800/30' : 'opacity-70'
                        }`}
                      >
                        <p className="text-xs font-bold text-white">{n.title}</p>
                        <p className="text-[11px] text-slate-400 mt-0.5 line-clamp-2">{n.message}</p>
                      </div>
                    ))
                  ) : (
                    <p className="text-xs text-slate-500 text-center py-4">No notifications</p>
                  )}
                </div>
              </div>
            )}
          </div>

          {/* User Profile Menu */}
          <div className="relative">
            <button
              onClick={() => setShowUserMenu(!showUserMenu)}
              className="flex items-center space-x-2 pl-1.5 pr-2.5 py-1 rounded-xl bg-slate-900/90 border border-slate-800 hover:border-slate-700 transition-colors"
            >
              <div className="w-7 h-7 rounded-lg bg-gradient-to-tr from-blue-600 to-indigo-600 text-white font-bold flex items-center justify-center text-xs shadow-sm">
                {user?.fullName?.charAt(0) || 'A'}
              </div>
              <span className="text-xs font-bold text-slate-200 hidden sm:inline-block">
                {user?.fullName || 'Administrator'}
              </span>
              <ChevronDown className="w-3 h-3 text-slate-400" />
            </button>

            {showUserMenu && (
              <div className="absolute right-0 mt-2 w-52 rounded-2xl bg-[#0f172a] border border-slate-800 shadow-2xl p-2 z-50 animate-in fade-in text-xs">
                <div className="px-3 py-2 border-b border-slate-800">
                  <p className="text-xs font-bold text-white truncate">{user?.fullName}</p>
                  <p className="text-[10px] text-slate-400 truncate">{user?.email}</p>
                  <span className="inline-block mt-1 px-1.5 py-0.5 rounded bg-blue-500/20 text-cyan-300 text-[9px] font-bold">
                    {user?.roles?.[0]?.replace('ROLE_', '') || 'SUPER_ADMIN'}
                  </span>
                </div>
                <button
                  onClick={handleLogout}
                  className="w-full mt-1 flex items-center px-3 py-2 text-xs font-bold text-rose-400 hover:bg-rose-950/40 rounded-xl transition-colors"
                >
                  <LogOut className="w-4 h-4 mr-2" /> Sign Out
                </button>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* Tier 2: Horizontal Department Modules & Sub-Nav Menu */}
      <div className="px-6 bg-[#0a0f1d] border-b border-slate-800/80 overflow-x-auto">
        <nav className="flex items-center space-x-1.5 py-1.5">
          {navModules.map((mod) => {
            if (mod.permission && !checkRole(mod.permission)) return null;

            const isDirectActive = mod.path ? location.pathname === mod.path : false;
            const isChildActive = mod.basePath ? location.pathname.startsWith(mod.basePath) : false;
            const isActive = isDirectActive || isChildActive;
            const isOpen = activeDropdown === mod.id;
            const Icon = mod.icon;

            if (mod.path) {
              return (
                <NavLink
                  key={mod.id}
                  to={mod.path}
                  className={`flex items-center space-x-2 px-3.5 py-2 rounded-xl text-xs font-bold transition-all ${
                    isActive
                      ? 'bg-gradient-to-r from-blue-600 to-indigo-600 text-white shadow-md shadow-blue-500/20'
                      : 'text-slate-400 hover:text-white hover:bg-slate-850'
                  }`}
                >
                  <Icon className="w-3.5 h-3.5" />
                  <span>{mod.label}</span>
                </NavLink>
              );
            }

            return (
              <div key={mod.id} className="relative">
                <button
                  type="button"
                  onClick={() => setActiveDropdown(isOpen ? null : mod.id)}
                  className={`flex items-center space-x-1.5 px-3.5 py-2 rounded-xl text-xs font-bold transition-all ${
                    isActive || isOpen
                      ? 'bg-blue-600/20 text-cyan-300 border border-blue-500/30'
                      : 'text-slate-400 hover:text-white hover:bg-slate-900/60'
                  }`}
                >
                  <Icon className="w-3.5 h-3.5" />
                  <span>{mod.label}</span>
                  <ChevronDown className={`w-3 h-3 transition-transform ${isOpen ? 'rotate-180 text-cyan-400' : 'text-slate-500'}`} />
                </button>

                {/* Dropdown Mega Menu */}
                {isOpen && (
                  <div className="absolute left-0 mt-2 w-72 rounded-2xl bg-[#0f172a] border border-slate-800 shadow-2xl p-2 z-50 animate-in fade-in space-y-1">
                    <div className="px-3 py-1.5 text-[10px] font-extrabold text-slate-500 uppercase tracking-wider border-b border-slate-800 mb-1">
                      {mod.label} Sub-Modules:
                    </div>
                    {mod.items.map((subItem) => {
                      const SubIcon = subItem.icon;
                      const isSubActive = location.pathname === subItem.path;
                      return (
                        <Link
                          key={subItem.path}
                          to={subItem.path}
                          onClick={() => setActiveDropdown(null)}
                          className={`flex items-start space-x-2.5 p-2.5 rounded-xl transition-all ${
                            isSubActive
                              ? 'bg-blue-600 text-white shadow-sm'
                              : 'text-slate-300 hover:bg-slate-800/80 hover:text-white'
                          }`}
                        >
                          <div className={`p-1.5 rounded-lg mt-0.5 ${isSubActive ? 'bg-white/20' : 'bg-slate-800 text-cyan-400'}`}>
                            <SubIcon className="w-3.5 h-3.5" />
                          </div>
                          <div className="min-w-0 flex-1">
                            <div className="font-bold text-xs leading-tight truncate">{subItem.label}</div>
                            <div className={`text-[10px] leading-tight truncate mt-0.5 ${isSubActive ? 'text-blue-100' : 'text-slate-400'}`}>
                              {subItem.desc}
                            </div>
                          </div>
                        </Link>
                      );
                    })}
                  </div>
                )}
              </div>
            );
          })}
        </nav>
      </div>
    </header>
  );
}
