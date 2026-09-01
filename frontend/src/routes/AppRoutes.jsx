import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

// Layouts
import { MainLayout } from '../components/layout/MainLayout';
import { AuthLayout } from '../components/layout/AuthLayout';
import { ProtectedRoute } from '../components/layout/ProtectedRoute';

// Auth Pages
import { Login } from '../pages/auth/Login';
import { Register } from '../pages/auth/Register';

// Core Dashboard
import { ExecutiveDashboard } from '../pages/dashboard/ExecutiveDashboard';

// HR & Payroll
import { EmployeeDirectory } from '../pages/hr/EmployeeDirectory';
import { DepartmentManagement } from '../pages/hr/DepartmentManagement';
import { AttendanceTracker } from '../pages/hr/AttendanceTracker';
import { LeaveManagement } from '../pages/hr/LeaveManagement';
import { PayrollManagement } from '../pages/hr/PayrollManagement';

// Finance & Accounting
import { ChartOfAccounts } from '../pages/finance/ChartOfAccounts';
import { JournalEntries } from '../pages/finance/JournalEntries';
import { ExpenseTracker } from '../pages/finance/ExpenseTracker';
import { FinancialReports } from '../pages/finance/FinancialReports';

// Inventory & Warehouse
import { ProductCatalog } from '../pages/inventory/ProductCatalog';
import { StockMovements } from '../pages/inventory/StockMovements';
import { WarehouseManager } from '../pages/inventory/WarehouseManager';

// Procurement
import { VendorDirectory } from '../pages/procurement/VendorDirectory';
import { PurchaseOrders } from '../pages/procurement/PurchaseOrders';

// Sales & CRM
import { CustomerDirectory } from '../pages/sales/CustomerDirectory';
import { SalesOrders } from '../pages/sales/SalesOrders';
import { Invoices } from '../pages/sales/Invoices';
import { CrmPipeline } from '../pages/sales/CrmPipeline';

// Operations
import { ProjectManager } from '../pages/operations/ProjectManager';
import { ManufacturingBom } from '../pages/operations/ManufacturingBom';
import { SupplyChainLogistics } from '../pages/operations/SupplyChainLogistics';
import { AssetManager } from '../pages/operations/AssetManager';

// System & Audit
import { AuditLogs } from '../pages/system/AuditLogs';
import { SystemSettings } from '../pages/system/SystemSettings';

export function AppRoutes() {
  return (
    <Routes>
      {/* Auth Public Routes */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Route>

      {/* Authenticated Workspace Routes */}
      <Route element={<ProtectedRoute />}>
        <Route element={<MainLayout />}>
          {/* Default Redirect */}
          <Route path="/" element={<Navigate to="/dashboard" replace />} />

          {/* Executive Dashboard */}
          <Route path="/dashboard" element={<ExecutiveDashboard />} />

          {/* HR & Payroll */}
          <Route path="/hr/employees" element={<EmployeeDirectory />} />
          <Route path="/hr/departments" element={<DepartmentManagement />} />
          <Route path="/hr/attendance" element={<AttendanceTracker />} />
          <Route path="/hr/leaves" element={<LeaveManagement />} />
          <Route path="/hr/payroll" element={<PayrollManagement />} />

          {/* Finance & Accounting */}
          <Route path="/finance/accounts" element={<ChartOfAccounts />} />
          <Route path="/finance/journal" element={<JournalEntries />} />
          <Route path="/finance/expenses" element={<ExpenseTracker />} />
          <Route path="/finance/reports" element={<FinancialReports />} />

          {/* Inventory & Warehousing */}
          <Route path="/inventory/products" element={<ProductCatalog />} />
          <Route path="/inventory/movements" element={<StockMovements />} />
          <Route path="/inventory/warehouses" element={<WarehouseManager />} />

          {/* Procurement & Vendors */}
          <Route path="/procurement/vendors" element={<VendorDirectory />} />
          <Route path="/procurement/orders" element={<PurchaseOrders />} />

          {/* Sales & CRM */}
          <Route path="/sales/customers" element={<CustomerDirectory />} />
          <Route path="/sales/orders" element={<SalesOrders />} />
          <Route path="/sales/invoices" element={<Invoices />} />
          <Route path="/sales/crm" element={<CrmPipeline />} />

          {/* Operations & Assets */}
          <Route path="/operations/projects" element={<ProjectManager />} />
          <Route path="/operations/manufacturing" element={<ManufacturingBom />} />
          <Route path="/operations/supply-chain" element={<SupplyChainLogistics />} />
          <Route path="/operations/assets" element={<AssetManager />} />

          {/* System Admin */}
          <Route path="/system/audit" element={<AuditLogs />} />
          <Route path="/system/settings" element={<SystemSettings />} />
        </Route>
      </Route>

      {/* Catch-all 404 redirect */}
      <Route path="*" element={<Navigate to="/dashboard" replace />} />
    </Routes>
  );
}
