import React, { useState } from 'react';
import { Search, Bell, Moon, Sun, LogOut, CheckCheck, User, Building2, HelpCircle, ChevronDown } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { useNotification } from '../../context/NotificationContext';
import { useNavigate } from 'react-router-dom';

export function Navbar() {
  const { user, logout } = useAuth();
  const { notifications, unreadCount, markAsRead, markAllAsRead } = useNotification();
  const [showNotifications, setShowNotifications] = useState(false);
  const [showUserMenu, setShowUserMenu] = useState(false);
  const [showCompanyMenu, setShowCompanyMenu] = useState(false);
  const [selectedCompany, setSelectedCompany] = useState('Elevit IQ');
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="sticky top-0 z-30 h-16 bg-white/95 backdrop-blur-md border-b border-slate-200 px-6 flex items-center justify-between">
      {/* Left: Global Search */}
      <div className="flex items-center space-x-3">
        <div className="flex items-center w-80 relative">
          <Search className="w-4 h-4 text-slate-400 absolute left-3 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            placeholder="Search customers, products, orders, invoices..."
            className="w-full bg-slate-50 border border-slate-200 rounded-xl pl-9 pr-4 py-2 text-xs text-slate-900 placeholder-slate-400 focus:outline-none focus:border-blue-500 focus:bg-white transition-all font-medium"
          />
        </div>
      </div>

      {/* Right Controls */}
      <div className="flex items-center space-x-3">
        {/* Company Selector Dropdown */}
        <div className="relative">
          <button
            onClick={() => setShowCompanyMenu(!showCompanyMenu)}
            className="flex items-center space-x-2 px-3 py-1.5 rounded-xl border border-slate-200 bg-slate-50 hover:bg-slate-100 text-xs font-bold text-slate-800 transition-colors shadow-2xs"
          >
            <Building2 className="w-3.5 h-3.5 text-blue-600" />
            <span>{selectedCompany}</span>
            <ChevronDown className="w-3 h-3 text-slate-400" />
          </button>

          {showCompanyMenu && (
            <div className="absolute right-0 mt-2 w-48 rounded-2xl bg-white border border-slate-200 shadow-xl p-2 z-50 animate-in fade-in text-xs">
              <div className="px-2 py-1 text-[10px] font-bold text-slate-400 uppercase">Select Workspace:</div>
              {['Elevit IQ', 'Elevit Technologies Ltd', 'Acme Global Corp'].map((comp) => (
                <button
                  key={comp}
                  onClick={() => {
                    setSelectedCompany(comp);
                    setShowCompanyMenu(false);
                  }}
                  className={`w-full text-left px-2.5 py-1.5 rounded-xl font-bold transition flex items-center justify-between ${
                    selectedCompany === comp ? 'bg-blue-50 text-blue-700' : 'text-slate-700 hover:bg-slate-50'
                  }`}
                >
                  <span>{comp}</span>
                  {selectedCompany === comp && <span className="text-[10px] text-blue-600 font-extrabold">Active</span>}
                </button>
              ))}
            </div>
          )}
        </div>

        {/* System Status Pill */}
        <div className="hidden sm:flex items-center px-2.5 py-1 rounded-full bg-emerald-50 border border-emerald-200 text-[11px] font-bold text-emerald-700">
          <span className="w-2 h-2 rounded-full bg-emerald-500 mr-2" />
          ERP Core: Operational
        </div>

        {/* Notifications Dropdown */}
        <div className="relative">
          <button
            onClick={() => setShowNotifications(!showNotifications)}
            className="p-2 rounded-xl border border-slate-200 bg-slate-50 text-slate-600 hover:text-slate-900 hover:bg-slate-100 transition-colors relative"
          >
            <Bell className="w-4 h-4" />
            {unreadCount > 0 && (
              <span className="absolute -top-1 -right-1 w-4 h-4 bg-blue-600 text-white rounded-full text-[10px] font-bold flex items-center justify-center">
                {unreadCount}
              </span>
            )}
          </button>

          {showNotifications && (
            <div className="absolute right-0 mt-2 w-80 rounded-2xl bg-white border border-slate-200 shadow-2xl p-4 z-50 animate-in fade-in">
              <div className="flex items-center justify-between pb-2.5 border-b border-slate-100">
                <span className="text-xs font-bold text-slate-900 uppercase tracking-wider">Enterprise Alerts</span>
                <button
                  onClick={markAllAsRead}
                  className="text-[11px] font-bold text-blue-600 hover:underline flex items-center"
                >
                  <CheckCheck className="w-3.5 h-3.5 mr-1" /> Mark read
                </button>
              </div>

              <div className="divide-y divide-slate-100 max-h-72 overflow-y-auto mt-1">
                {notifications.length > 0 ? (
                  notifications.map((n) => (
                    <div
                      key={n.id}
                      onClick={() => markAsRead(n.id)}
                      className={`p-2.5 rounded-xl cursor-pointer hover:bg-slate-50 transition-colors ${
                        !n.isRead ? 'bg-blue-50/50' : 'opacity-70'
                      }`}
                    >
                      <p className="text-xs font-bold text-slate-900">{n.title}</p>
                      <p className="text-[11px] text-slate-600 mt-0.5 line-clamp-2">{n.message}</p>
                    </div>
                  ))
                ) : (
                  <p className="text-xs text-slate-400 text-center py-4">No notifications</p>
                )}
              </div>
            </div>
          )}
        </div>

        {/* User Profile Menu */}
        <div className="relative">
          <button
            onClick={() => setShowUserMenu(!showUserMenu)}
            className="flex items-center space-x-2 pl-1.5 pr-2.5 py-1 rounded-xl border border-slate-200 bg-slate-50 hover:bg-slate-100 transition-colors"
          >
            <div className="w-7 h-7 rounded-lg bg-blue-600 text-white font-bold flex items-center justify-center text-xs">
              {user?.fullName?.charAt(0) || 'A'}
            </div>
            <span className="text-xs font-bold text-slate-800 hidden md:inline-block">
              {user?.fullName || 'Administrator'}
            </span>
            <ChevronDown className="w-3 h-3 text-slate-400" />
          </button>

          {showUserMenu && (
            <div className="absolute right-0 mt-2 w-48 rounded-2xl bg-white border border-slate-200 shadow-2xl p-2 z-50 animate-in fade-in text-xs">
              <div className="px-3 py-2 border-b border-slate-100">
                <p className="text-xs font-bold text-slate-900 truncate">{user?.fullName}</p>
                <p className="text-[10px] text-slate-500 truncate">{user?.email}</p>
              </div>
              <button
                onClick={handleLogout}
                className="w-full mt-1 flex items-center px-3 py-2 text-xs font-bold text-rose-600 hover:bg-rose-50 rounded-xl transition-colors"
              >
                <LogOut className="w-4 h-4 mr-2" /> Sign Out
              </button>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}
