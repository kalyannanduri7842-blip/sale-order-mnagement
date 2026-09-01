import React, { createContext, useContext, useState } from 'react';

const NotificationContext = createContext();

export function NotificationProvider({ children }) {
  const [notifications, setNotifications] = useState([
    { id: 1, title: 'Monthly Financial Closing Completed', message: 'General ledger balanced. Financial reports and Trial Balance ready for review.', type: 'SUCCESS', isRead: false, createdAt: new Date().toISOString() },
    { id: 2, title: 'Low Stock Alert: Server Hardware', message: 'Dell PowerEdge units at Austin Hub below threshold (10 units). Auto PO prepared.', type: 'WARNING', isRead: false, createdAt: new Date().toISOString() }
  ]);
  const [toasts, setToasts] = useState([]);

  const addToast = (title, message, type = 'info') => {
    const id = Date.now();
    const toast = { id, title, message, type };
    setToasts((prev) => [...prev, toast]);
    setTimeout(() => {
      removeToast(id);
    }, 4000);
  };

  const removeToast = (id) => {
    setToasts((prev) => prev.filter((t) => t.id !== id));
  };

  const markAsRead = (id) => {
    setNotifications((prev) => prev.map((n) => (n.id === id ? { ...n, isRead: true } : n)));
  };

  const markAllAsRead = () => {
    setNotifications((prev) => prev.map((n) => ({ ...n, isRead: true })));
  };

  const unreadCount = notifications.filter((n) => !n.isRead).length;

  return (
    <NotificationContext.Provider
      value={{
        notifications,
        unreadCount,
        toasts,
        addToast,
        removeToast,
        markAsRead,
        markAllAsRead
      }}
    >
      {children}
      {/* Toast Notification Container */}
      <div className="fixed bottom-5 right-5 z-50 flex flex-col space-y-3 max-w-sm pointer-events-none">
        {toasts.map((toast) => (
          <div
            key={toast.id}
            className={`pointer-events-auto p-4 rounded-xl shadow-2xl border text-sm font-medium transition-all duration-300 transform translate-y-0 ${
              toast.type === 'success'
                ? 'bg-emerald-950/90 border-emerald-500/50 text-emerald-200'
                : toast.type === 'error'
                ? 'bg-rose-950/90 border-rose-500/50 text-rose-200'
                : toast.type === 'warning'
                ? 'bg-amber-950/90 border-amber-500/50 text-amber-200'
                : 'bg-slate-900/90 border-cyan-500/50 text-cyan-200'
            }`}
          >
            <div className="flex items-center justify-between">
              <span className="font-semibold">{toast.title}</span>
              <button
                onClick={() => removeToast(toast.id)}
                className="text-xs opacity-70 hover:opacity-100 ml-3"
              >
                ✕
              </button>
            </div>
            {toast.message && <p className="text-xs mt-1 opacity-80">{toast.message}</p>}
          </div>
        ))}
      </div>
    </NotificationContext.Provider>
  );
}

export function useNotification() {
  return useContext(NotificationContext);
}
