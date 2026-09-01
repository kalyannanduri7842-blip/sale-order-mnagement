import React, { createContext, useContext, useState, useEffect } from 'react';
import { INITIAL_MOCK_DATA } from '../constants/mockData';

const ErpDataContext = createContext();

export function ErpDataProvider({ children }) {
  // Safe loader with Array / Object guarantee
  const loadInitialArray = (key, fallback = []) => {
    try {
      const saved = localStorage.getItem(`erp_${key}`);
      if (saved) {
        const parsed = JSON.parse(saved);
        if (Array.isArray(parsed)) return parsed;
      }
    } catch (e) {
      console.warn(`Error loading erp_${key}`, e);
    }
    return Array.isArray(fallback) ? fallback : [];
  };

  const loadInitialObject = (key, fallback = {}) => {
    try {
      const saved = localStorage.getItem(`erp_${key}`);
      if (saved) {
        const parsed = JSON.parse(saved);
        if (parsed && typeof parsed === 'object') return parsed;
      }
    } catch (e) {
      console.warn(`Error loading erp_${key}`, e);
    }
    return fallback && typeof fallback === 'object' ? fallback : {};
  };

  const [dashboard, setDashboard] = useState(() => loadInitialObject('dashboard', INITIAL_MOCK_DATA.dashboard));
  const [customers, setCustomers] = useState(() => loadInitialArray('customers', INITIAL_MOCK_DATA.customers || []));
  const [employees, setEmployees] = useState(() => loadInitialArray('employees', INITIAL_MOCK_DATA.employees || []));
  const [departments, setDepartments] = useState(() => loadInitialArray('departments', INITIAL_MOCK_DATA.departments || []));
  const [attendance, setAttendance] = useState(() => loadInitialArray('attendance', INITIAL_MOCK_DATA.attendance || []));
  const [leaveRequests, setLeaveRequests] = useState(() => loadInitialArray('leaveRequests', INITIAL_MOCK_DATA.leaveRequests || []));
  const [payroll, setPayroll] = useState(() => loadInitialArray('payroll', INITIAL_MOCK_DATA.payroll || []));
  const [accounts, setAccounts] = useState(() => loadInitialArray('accounts', INITIAL_MOCK_DATA.accounts || []));
  const [expenses, setExpenses] = useState(() => loadInitialArray('expenses', INITIAL_MOCK_DATA.expenses || []));
  const [products, setProducts] = useState(() => loadInitialArray('products', INITIAL_MOCK_DATA.products || []));
  const [warehouses, setWarehouses] = useState(() => loadInitialArray('warehouses', INITIAL_MOCK_DATA.warehouses || []));
  const [stockMovements, setStockMovements] = useState(() => loadInitialArray('stockMovements', INITIAL_MOCK_DATA.stockMovements || []));
  const [vendors, setVendors] = useState(() => loadInitialArray('vendors', INITIAL_MOCK_DATA.vendors || []));
  const [purchaseOrders, setPurchaseOrders] = useState(() => loadInitialArray('purchaseOrders', INITIAL_MOCK_DATA.purchaseOrders || []));
  const [salesOrders, setSalesOrders] = useState(() => loadInitialArray('salesOrders', INITIAL_MOCK_DATA.salesOrders || []));
  const [invoices, setInvoices] = useState(() => loadInitialArray('invoices', INITIAL_MOCK_DATA.invoices || []));
  const [leads, setLeads] = useState(() => loadInitialArray('leads', INITIAL_MOCK_DATA.crmOpportunities || []));
  const [auditLogs, setAuditLogs] = useState(() => loadInitialArray('auditLogs', INITIAL_MOCK_DATA.auditLogs || []));

  // Sync state to localStorage safely
  useEffect(() => {
    try {
      localStorage.setItem('erp_dashboard', JSON.stringify(dashboard || INITIAL_MOCK_DATA.dashboard));
      localStorage.setItem('erp_customers', JSON.stringify(customers || []));
      localStorage.setItem('erp_employees', JSON.stringify(employees || []));
      localStorage.setItem('erp_departments', JSON.stringify(departments || []));
      localStorage.setItem('erp_attendance', JSON.stringify(attendance || []));
      localStorage.setItem('erp_leaveRequests', JSON.stringify(leaveRequests || []));
      localStorage.setItem('erp_payroll', JSON.stringify(payroll || []));
      localStorage.setItem('erp_accounts', JSON.stringify(accounts || []));
      localStorage.setItem('erp_expenses', JSON.stringify(expenses || []));
      localStorage.setItem('erp_products', JSON.stringify(products || []));
      localStorage.setItem('erp_warehouses', JSON.stringify(warehouses || []));
      localStorage.setItem('erp_stockMovements', JSON.stringify(stockMovements || []));
      localStorage.setItem('erp_vendors', JSON.stringify(vendors || []));
      localStorage.setItem('erp_purchaseOrders', JSON.stringify(purchaseOrders || []));
      localStorage.setItem('erp_salesOrders', JSON.stringify(salesOrders || []));
      localStorage.setItem('erp_invoices', JSON.stringify(invoices || []));
      localStorage.setItem('erp_leads', JSON.stringify(leads || []));
      localStorage.setItem('erp_auditLogs', JSON.stringify(auditLogs || []));
    } catch (e) {
      console.warn('Storage sync error', e);
    }
  }, [
    dashboard, customers, employees, departments, attendance, leaveRequests,
    payroll, accounts, expenses, products, warehouses, stockMovements,
    vendors, purchaseOrders, salesOrders, invoices, leads, auditLogs
  ]);

  // Log Audit Action
  const logAudit = (action, moduleName, description, user = 'Elevit Owner') => {
    const newLog = {
      id: Date.now(),
      user,
      action,
      module: moduleName,
      description,
      ipAddress: '192.168.1.104',
      timestamp: new Date().toISOString()
    };
    setAuditLogs(prev => [newLog, ...(Array.isArray(prev) ? prev : [])]);
  };

  // 1-Click Load Demo Data
  const loadDemoData = () => {
    setDashboard(INITIAL_MOCK_DATA.dashboard);
    setCustomers(INITIAL_MOCK_DATA.customers || []);
    setEmployees(INITIAL_MOCK_DATA.employees || []);
    setDepartments(INITIAL_MOCK_DATA.departments || []);
    setAttendance(INITIAL_MOCK_DATA.attendance || []);
    setLeaveRequests(INITIAL_MOCK_DATA.leaveRequests || []);
    setPayroll(INITIAL_MOCK_DATA.payroll || []);
    setAccounts(INITIAL_MOCK_DATA.accounts || []);
    setExpenses(INITIAL_MOCK_DATA.expenses || []);
    setProducts(INITIAL_MOCK_DATA.products || []);
    setWarehouses(INITIAL_MOCK_DATA.warehouses || []);
    setStockMovements(INITIAL_MOCK_DATA.stockMovements || []);
    setVendors(INITIAL_MOCK_DATA.vendors || []);
    setPurchaseOrders(INITIAL_MOCK_DATA.purchaseOrders || []);
    setSalesOrders(INITIAL_MOCK_DATA.salesOrders || []);
    setInvoices(INITIAL_MOCK_DATA.invoices || []);
    setLeads(INITIAL_MOCK_DATA.crmOpportunities || []);
    setAuditLogs(INITIAL_MOCK_DATA.auditLogs || []);
    logAudit('SYSTEM_RESTORE', 'SYSTEM', 'Loaded Elevit IQ Enterprise demo dataset');
  };

  // 1-Click Clear All Data (Start Fresh)
  const clearAllData = () => {
    setCustomers([]);
    setEmployees([]);
    setAttendance([]);
    setLeaveRequests([]);
    setPayroll([]);
    setExpenses([]);
    setProducts([]);
    setStockMovements([]);
    setVendors([]);
    setPurchaseOrders([]);
    setSalesOrders([]);
    setInvoices([]);
    setLeads([]);
    logAudit('PORTFOLIO_CLEARED', 'SYSTEM', 'Cleared all workspace demo data for fresh setup');
  };

  // CRUD Helpers
  const addCustomer = (item) => {
    const newItem = { id: Date.now(), customerCode: `CUST-${1000 + (customers?.length || 0) + 1}`, totalSpend: 0, active: true, ...item };
    setCustomers(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_CUSTOMER', 'SALES', `Added customer account: ${newItem.name}`);
    return newItem;
  };
  const deleteCustomer = (id) => {
    setCustomers(prev => (Array.isArray(prev) ? prev.filter(c => c.id !== id) : []));
    logAudit('DELETE_CUSTOMER', 'SALES', `Deleted customer record ID: ${id}`);
  };

  const addSalesOrder = (item) => {
    const newItem = { id: Date.now(), orderNumber: `SO-${8000 + (salesOrders?.length || 0) + 1}`, orderDate: new Date().toISOString().split('T')[0], status: 'CONFIRMED', paymentStatus: 'UNPAID', ...item };
    setSalesOrders(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_SALES_ORDER', 'SALES', `Created sales order: ${newItem.orderNumber}`);
    return newItem;
  };
  const deleteSalesOrder = (id) => {
    setSalesOrders(prev => (Array.isArray(prev) ? prev.filter(o => o.id !== id) : []));
    logAudit('DELETE_SALES_ORDER', 'SALES', `Deleted sales order ID: ${id}`);
  };

  const addInvoice = (item) => {
    const newItem = { id: Date.now(), invoiceNumber: `INV-${9000 + (invoices?.length || 0) + 1}`, issueDate: new Date().toISOString().split('T')[0], status: 'SENT', ...item };
    setInvoices(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_INVOICE', 'FINANCE', `Issued invoice: ${newItem.invoiceNumber}`);
    return newItem;
  };
  const deleteInvoice = (id) => {
    setInvoices(prev => (Array.isArray(prev) ? prev.filter(i => i.id !== id) : []));
    logAudit('DELETE_INVOICE', 'FINANCE', `Deleted invoice ID: ${id}`);
  };

  const addLead = (item) => {
    const newItem = { id: Date.now(), stage: 'NEW', priority: 'MEDIUM', ...item };
    setLeads(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_LEAD', 'CRM', `Added CRM opportunity: ${newItem.companyName || newItem.title}`);
    return newItem;
  };
  const deleteLead = (id) => {
    setLeads(prev => (Array.isArray(prev) ? prev.filter(l => l.id !== id) : []));
    logAudit('DELETE_LEAD', 'CRM', `Deleted CRM lead ID: ${id}`);
  };

  const addVendor = (item) => {
    const newItem = { id: Date.now(), vendorCode: `VEN-${100 + (vendors?.length || 0) + 1}`, rating: 5.0, active: true, ...item };
    setVendors(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_VENDOR', 'PROCUREMENT', `Added supplier: ${newItem.name}`);
    return newItem;
  };
  const deleteVendor = (id) => {
    setVendors(prev => (Array.isArray(prev) ? prev.filter(v => v.id !== id) : []));
    logAudit('DELETE_VENDOR', 'PROCUREMENT', `Deleted vendor ID: ${id}`);
  };

  const addPurchaseOrder = (item) => {
    const newItem = { id: Date.now(), poNumber: `PO-${5000 + (purchaseOrders?.length || 0) + 1}`, orderDate: new Date().toISOString().split('T')[0], status: 'SENT', ...item };
    setPurchaseOrders(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_PO', 'PROCUREMENT', `Issued purchase order: ${newItem.poNumber}`);
    return newItem;
  };
  const deletePurchaseOrder = (id) => {
    setPurchaseOrders(prev => (Array.isArray(prev) ? prev.filter(p => p.id !== id) : []));
    logAudit('DELETE_PO', 'PROCUREMENT', `Deleted purchase order ID: ${id}`);
  };

  const addProduct = (item) => {
    const newItem = { id: Date.now(), sku: item.sku || `SKU-${1000 + (products?.length || 0) + 1}`, active: true, ...item };
    setProducts(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_PRODUCT', 'INVENTORY', `Added product catalog item: ${newItem.name}`);
    return newItem;
  };
  const deleteProduct = (id) => {
    setProducts(prev => (Array.isArray(prev) ? prev.filter(p => p.id !== id) : []));
    logAudit('DELETE_PRODUCT', 'INVENTORY', `Deleted product ID: ${id}`);
  };

  const addWarehouse = (item) => {
    const newItem = { id: Date.now(), code: `WH-0${(warehouses?.length || 0) + 1}`, active: true, ...item };
    setWarehouses(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_WAREHOUSE', 'INVENTORY', `Added warehouse location: ${newItem.name}`);
    return newItem;
  };
  const deleteWarehouse = (id) => {
    setWarehouses(prev => (Array.isArray(prev) ? prev.filter(w => w.id !== id) : []));
    logAudit('DELETE_WAREHOUSE', 'INVENTORY', `Deleted warehouse ID: ${id}`);
  };

  const addEmployee = (item) => {
    const newItem = { id: Date.now(), employeeId: `EMP-${1000 + (employees?.length || 0) + 1}`, employmentStatus: 'ACTIVE', ...item };
    setEmployees(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_EMPLOYEE', 'HR', `Registered staff member: ${newItem.firstName} ${newItem.lastName}`);
    return newItem;
  };
  const deleteEmployee = (id) => {
    setEmployees(prev => (Array.isArray(prev) ? prev.filter(e => e.id !== id) : []));
    logAudit('DELETE_EMPLOYEE', 'HR', `Deleted employee ID: ${id}`);
  };

  const addExpense = (item) => {
    const newItem = { id: Date.now(), expenseNumber: `EXP-${1000 + (expenses?.length || 0) + 1}`, expenseDate: new Date().toISOString().split('T')[0], status: 'APPROVED', ...item };
    setExpenses(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('POST_EXPENSE', 'FINANCE', `Posted expense: ${newItem.title} ($${newItem.amount})`);
    return newItem;
  };
  const deleteExpense = (id) => {
    setExpenses(prev => (Array.isArray(prev) ? prev.filter(e => e.id !== id) : []));
    logAudit('DELETE_EXPENSE', 'FINANCE', `Deleted expense ID: ${id}`);
  };

  const addAccount = (item) => {
    const newItem = { id: Date.now(), active: true, balance: parseFloat(item.balance) || 0, ...item };
    setAccounts(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CREATE_ACCOUNT', 'FINANCE', `Created general ledger account: ${newItem.accountName}`);
    return newItem;
  };
  const deleteAccount = (id) => {
    setAccounts(prev => (Array.isArray(prev) ? prev.filter(a => a.id !== id) : []));
    logAudit('DELETE_ACCOUNT', 'FINANCE', `Deleted account ID: ${id}`);
  };

  const addLeaveRequest = (item) => {
    const newItem = { id: Date.now(), status: 'PENDING', createdAt: new Date().toISOString(), ...item };
    setLeaveRequests(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('REQUEST_LEAVE', 'HR', `Submitted leave request for: ${newItem.employeeName}`);
    return newItem;
  };
  const updateLeaveStatus = (id, status) => {
    setLeaveRequests(prev => (Array.isArray(prev) ? prev.map(l => l.id === id ? { ...l, status } : l) : []));
    logAudit('UPDATE_LEAVE', 'HR', `Updated leave request ${id} to ${status}`);
  };
  const deleteLeaveRequest = (id) => {
    setLeaveRequests(prev => (Array.isArray(prev) ? prev.filter(l => l.id !== id) : []));
    logAudit('DELETE_LEAVE', 'HR', `Deleted leave request ID: ${id}`);
  };

  const addAttendanceRecord = (item) => {
    const newItem = { id: Date.now(), attendanceDate: new Date().toISOString().split('T')[0], status: 'PRESENT', totalHours: 8.0, ...item };
    setAttendance(prev => [newItem, ...(Array.isArray(prev) ? prev : [])]);
    logAudit('CLOCK_IN', 'HR', `Logged attendance for: ${newItem.employeeName}`);
    return newItem;
  };
  const deleteAttendanceRecord = (id) => {
    setAttendance(prev => (Array.isArray(prev) ? prev.filter(a => a.id !== id) : []));
    logAudit('DELETE_ATTENDANCE', 'HR', `Deleted attendance record ID: ${id}`);
  };

  return (
    <ErpDataContext.Provider
      value={{
        dashboard: dashboard || INITIAL_MOCK_DATA.dashboard,
        customers: customers || [], addCustomer, deleteCustomer,
        salesOrders: salesOrders || [], addSalesOrder, deleteSalesOrder,
        invoices: invoices || [], addInvoice, deleteInvoice,
        leads: leads || [], addLead, deleteLead,
        vendors: vendors || [], addVendor, deleteVendor,
        purchaseOrders: purchaseOrders || [], addPurchaseOrder, deletePurchaseOrder,
        products: products || [], addProduct, deleteProduct,
        warehouses: warehouses || [], addWarehouse, deleteWarehouse,
        stockMovements: stockMovements || [],
        accounts: accounts || [], addAccount, deleteAccount,
        expenses: expenses || [], addExpense, deleteExpense,
        employees: employees || [], addEmployee, deleteEmployee,
        departments: departments || [],
        attendance: attendance || [], addAttendanceRecord, deleteAttendanceRecord,
        leaveRequests: leaveRequests || [], addLeaveRequest, updateLeaveStatus, deleteLeaveRequest,
        payroll: payroll || [],
        auditLogs: auditLogs || [],
        loadDemoData,
        clearAllData,
        logAudit
      }}
    >
      {children}
    </ErpDataContext.Provider>
  );
}

export function useErpData() {
  const ctx = useContext(ErpDataContext);
  if (!ctx) {
    return {
      dashboard: INITIAL_MOCK_DATA.dashboard,
      customers: [],
      salesOrders: [],
      invoices: [],
      leads: [],
      vendors: [],
      purchaseOrders: [],
      products: [],
      warehouses: [],
      stockMovements: [],
      accounts: [],
      expenses: [],
      employees: [],
      departments: [],
      attendance: [],
      leaveRequests: [],
      payroll: [],
      auditLogs: [],
      addCustomer: () => {},
      deleteCustomer: () => {},
      addSalesOrder: () => {},
      deleteSalesOrder: () => {},
      addInvoice: () => {},
      deleteInvoice: () => {},
      addLead: () => {},
      deleteLead: () => {},
      addVendor: () => {},
      deleteVendor: () => {},
      addPurchaseOrder: () => {},
      deletePurchaseOrder: () => {},
      addProduct: () => {},
      deleteProduct: () => {},
      addWarehouse: () => {},
      deleteWarehouse: () => {},
      addAccount: () => {},
      deleteAccount: () => {},
      addExpense: () => {},
      deleteExpense: () => {},
      addEmployee: () => {},
      deleteEmployee: () => {},
      addAttendanceRecord: () => {},
      deleteAttendanceRecord: () => {},
      addLeaveRequest: () => {},
      updateLeaveStatus: () => {},
      deleteLeaveRequest: () => {},
      loadDemoData: () => {},
      clearAllData: () => {},
      logAudit: () => {}
    };
  }
  return ctx;
}
