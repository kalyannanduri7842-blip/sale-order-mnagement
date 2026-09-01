export const INITIAL_MOCK_DATA = {
  dashboard: {
    totalEmployees: 48,
    activeEmployees: 46,
    pendingLeaves: 3,
    monthlyRevenue: 142500.0,
    monthlyExpenses: 58300.0,
    netProfit: 84200.0,
    totalSales: 842000.0,
    pendingOrders: 7,
    inventoryValuation: 354000.0,
    lowStockCount: 4,
    totalCustomers: 124,
    totalVendors: 38,
    pendingReceivables: 45200.0,
    pendingPayables: 18900.0,
    activeProjects: 12,
    completedProjects: 29,
    unreadNotifications: 2,
    monthlyRevenueChart: [
      { month: 'Jan', revenue: 65000, expenses: 38000, profit: 27000 },
      { month: 'Feb', revenue: 72000, expenses: 42000, profit: 30000 },
      { month: 'Mar', revenue: 89000, expenses: 45000, profit: 44000 },
      { month: 'Apr', revenue: 95000, expenses: 49000, profit: 46000 },
      { month: 'May', revenue: 110000, expenses: 52000, profit: 58000 },
      { month: 'Jun', revenue: 125000, expenses: 56000, profit: 69000 },
      { month: 'Jul', revenue: 132000, expenses: 54000, profit: 78000 },
      { month: 'Aug', revenue: 142500, expenses: 58300, profit: 84200 },
      { month: 'Sep', revenue: 138000, expenses: 61000, profit: 77000 },
      { month: 'Oct', revenue: 155000, expenses: 64000, profit: 91000 },
      { month: 'Nov', revenue: 168000, expenses: 67000, profit: 101000 },
      { month: 'Dec', revenue: 185000, expenses: 72000, profit: 113000 }
    ],
    departmentEmployeeDistribution: [
      { name: 'Engineering & IT', employees: 18 },
      { name: 'Sales & Marketing', employees: 12 },
      { name: 'Operations & Logistics', employees: 9 },
      { name: 'Finance & Accounts', employees: 5 },
      { name: 'Human Resources', employees: 4 }
    ],
    salesByProductCategory: [
      { category: 'Enterprise Software', value: 42 },
      { category: 'Server Hardware', value: 28 },
      { category: 'Managed Cloud Services', value: 18 },
      { category: 'Networking Equipment', value: 12 }
    ],
    recentActivities: [
      { id: 1, user: 'alex.rivers', action: 'PO_RECEIVED', module: 'PROCUREMENT', description: 'Received PO-1002 (45x Cisco Routers)', timestamp: '2026-08-30T10:15:00' },
      { id: 2, user: 'robert.sterling', action: 'JOURNAL_POSTED', module: 'FINANCE', description: 'Posted monthly depreciation journal entry ($12,400)', timestamp: '2026-08-30T09:40:00' },
      { id: 3, user: 'elena.rostova', action: 'ORDER_CONFIRMED', module: 'SALES', description: 'Confirmed Enterprise ERP license order for Acme Corp ($85,000)', timestamp: '2026-08-29T16:20:00' },
      { id: 4, user: 'sarah.jenkins', action: 'PAYROLL_PROCESSED', module: 'PAYROLL', description: 'Processed August Salary cycle for 48 active employees', timestamp: '2026-08-29T14:00:00' }
    ]
  },
  employees: [
    { id: 1, employeeId: 'EMP-1001', firstName: 'Alex', lastName: 'Rivers', email: 'alex.rivers@enterprisepro.com', phone: '+1-555-0101', departmentId: 1, departmentName: 'Engineering & IT', designationTitle: 'Principal Solutions Architect', joiningDate: '2021-03-01', salary: 160000, employmentStatus: 'ACTIVE', employmentType: 'FULL_TIME' },
    { id: 2, employeeId: 'EMP-1002', firstName: 'Sarah', lastName: 'Jenkins', email: 'sarah.j@enterprisepro.com', phone: '+1-555-0102', departmentId: 2, departmentName: 'Human Resources', designationTitle: 'Senior HR Generalist', joiningDate: '2022-01-15', salary: 88000, employmentStatus: 'ACTIVE', employmentType: 'FULL_TIME' },
    { id: 3, employeeId: 'EMP-1003', firstName: 'Robert', lastName: 'Sterling', email: 'robert.s@enterprisepro.com', phone: '+1-555-0103', departmentId: 3, departmentName: 'Finance & Accounts', designationTitle: 'Senior Financial Controller', joiningDate: '2020-06-01', salary: 120000, employmentStatus: 'ACTIVE', employmentType: 'FULL_TIME' },
    { id: 4, employeeId: 'EMP-1004', firstName: 'Elena', lastName: 'Rostova', email: 'elena.r@enterprisepro.com', phone: '+1-555-0104', departmentId: 4, departmentName: 'Sales & Marketing', designationTitle: 'Enterprise Account Executive', joiningDate: '2023-04-10', salary: 95000, employmentStatus: 'ACTIVE', employmentType: 'FULL_TIME' },
    { id: 5, employeeId: 'EMP-1005', firstName: 'Marcus', lastName: 'Vance', email: 'marcus.v@enterprisepro.com', phone: '+1-555-0105', departmentId: 5, departmentName: 'Operations & Logistics', designationTitle: 'Logistics Operations Lead', joiningDate: '2022-09-01', salary: 110000, employmentStatus: 'ACTIVE', employmentType: 'FULL_TIME' }
  ],
  departments: [
    { id: 1, name: 'Engineering & IT', code: 'ENG', description: 'Software engineering, infrastructure & systems', headOfDepartment: 'Alex Rivers', employeeCount: 18, active: true },
    { id: 2, name: 'Human Resources', code: 'HR', description: 'Talent recruitment, culture and operations', headOfDepartment: 'Sarah Jenkins', employeeCount: 4, active: true },
    { id: 3, name: 'Finance & Accounts', code: 'FIN', description: 'Financial planning, accounting and payroll', headOfDepartment: 'Robert Sterling', employeeCount: 5, active: true },
    { id: 4, name: 'Sales & Marketing', code: 'SAL', description: 'Enterprise sales, client acquisition & marketing', headOfDepartment: 'Elena Rostova', employeeCount: 12, active: true },
    { id: 5, name: 'Operations & Logistics', code: 'OPS', description: 'Supply chain, inventory, warehousing and production', headOfDepartment: 'Marcus Vance', employeeCount: 9, active: true }
  ],
  attendance: [
    { id: 1, employeeId: 1, employeeName: 'Alex Rivers', employeeCode: 'EMP-1001', attendanceDate: '2026-08-31', checkInTime: '08:55:00', checkOutTime: '17:30:00', totalHours: 8.58, overtimeHours: 0.58, status: 'PRESENT' },
    { id: 2, employeeId: 2, employeeName: 'Sarah Jenkins', employeeCode: 'EMP-1002', attendanceDate: '2026-08-31', checkInTime: '09:02:00', checkOutTime: '17:00:00', totalHours: 7.97, overtimeHours: 0.0, status: 'PRESENT' },
    { id: 3, employeeId: 3, employeeName: 'Robert Sterling', employeeCode: 'EMP-1003', attendanceDate: '2026-08-31', checkInTime: '09:35:00', checkOutTime: '18:00:00', totalHours: 8.42, overtimeHours: 0.42, status: 'LATE' },
    { id: 4, employeeId: 4, employeeName: 'Elena Rostova', employeeCode: 'EMP-1004', attendanceDate: '2026-08-31', checkInTime: '08:45:00', checkOutTime: '17:15:00', totalHours: 8.5, overtimeHours: 0.5, status: 'PRESENT' },
    { id: 5, employeeId: 5, employeeName: 'Marcus Vance', employeeCode: 'EMP-1005', attendanceDate: '2026-08-31', checkInTime: '08:30:00', checkOutTime: '17:00:00', totalHours: 8.5, overtimeHours: 0.5, status: 'PRESENT' }
  ],
  leaveRequests: [
    { id: 1, employeeId: 1, employeeName: 'Alex Rivers', employeeCode: 'EMP-1001', departmentName: 'Engineering & IT', leaveType: 'ANNUAL', startDate: '2026-09-10', endDate: '2026-09-15', totalDays: 6, reason: 'Family vacation trip', status: 'PENDING', createdAt: '2026-08-28T11:00:00' },
    { id: 2, employeeId: 4, employeeName: 'Elena Rostova', employeeCode: 'EMP-1004', departmentName: 'Sales & Marketing', leaveType: 'CASUAL', startDate: '2026-09-02', endDate: '2026-09-02', totalDays: 1, reason: 'Personal appointment', status: 'APPROVED', approvedByName: 'Alex Rivers', createdAt: '2026-08-27T09:30:00' }
  ],
  payroll: [
    { id: 1, employeeId: 1, employeeName: 'Alex Rivers', employeeCode: 'EMP-1001', departmentName: 'Engineering & IT', month: 8, year: 2026, totalWorkingDays: 22, presentDays: 22, basicSalary: 13333.33, allowancesTotal: 5533.33, overtimePay: 400.0, bonuses: 1000.0, grossSalary: 20266.66, deductionsTotal: 3840.0, taxDeduction: 1621.33, netSalary: 16426.66, paymentStatus: 'PAID', paymentDate: '2026-08-29', paymentMethod: 'BANK_TRANSFER', transactionReference: 'TXN-99410291' },
    { id: 2, employeeId: 2, employeeName: 'Sarah Jenkins', employeeCode: 'EMP-1002', departmentName: 'Human Resources', month: 8, year: 2026, totalWorkingDays: 22, presentDays: 21, basicSalary: 7333.33, allowancesTotal: 3133.33, overtimePay: 0.0, bonuses: 500.0, grossSalary: 10966.66, deductionsTotal: 2100.0, taxDeduction: 877.33, netSalary: 8866.66, paymentStatus: 'PAID', paymentDate: '2026-08-29', paymentMethod: 'BANK_TRANSFER', transactionReference: 'TXN-99410292' }
  ],
  accounts: [
    { id: 1, accountNumber: '1000', accountName: 'Operating Cash Account', accountType: 'ASSET', subType: 'CASH', balance: 150000.0, active: true },
    { id: 2, accountNumber: '1010', accountName: 'Main Commercial Bank Checking', accountType: 'ASSET', subType: 'BANK', balance: 485000.0, active: true },
    { id: 3, accountNumber: '1200', accountName: 'Accounts Receivable', accountType: 'ASSET', subType: 'RECEIVABLE', balance: 45200.0, active: true },
    { id: 4, accountNumber: '1300', accountName: 'Inventory Assets & Stock', accountType: 'ASSET', subType: 'INVENTORY', balance: 354000.0, active: true },
    { id: 5, accountNumber: '1500', accountName: 'Machinery & IT Equipment', accountType: 'ASSET', subType: 'FIXED_ASSET', balance: 220000.0, active: true },
    { id: 6, accountNumber: '2000', accountName: 'Accounts Payable - Suppliers', accountType: 'LIABILITY', subType: 'PAYABLE', balance: 18900.0, active: true },
    { id: 7, accountNumber: '2100', accountName: 'Payroll & Bonus Accruals', accountType: 'LIABILITY', subType: 'ACCRUED', balance: 32000.0, active: true },
    { id: 8, accountNumber: '3000', accountName: 'Owner Paid-In Capital', accountType: 'EQUITY', subType: 'CAPITAL', balance: 700000.0, active: true },
    { id: 9, accountNumber: '3100', accountName: 'Retained Earnings', accountType: 'EQUITY', subType: 'RETAINED_EARNINGS', balance: 419100.0, active: true },
    { id: 10, accountNumber: '4000', accountName: 'Enterprise Software License Revenue', accountType: 'REVENUE', subType: 'SALES', balance: 450000.0, active: true },
    { id: 11, accountNumber: '5000', accountName: 'Cost of Goods Sold (COGS)', accountType: 'EXPENSE', subType: 'COGS', balance: 180000.0, active: true },
    { id: 12, accountNumber: '6000', accountName: 'Salaries & Benefits Expense', accountType: 'EXPENSE', subType: 'OPERATING', balance: 125000.0, active: true }
  ],
  expenses: [
    { id: 1, expenseNumber: 'EXP-1001', title: 'AWS Cloud Hosting & Kubernetes Cluster', category: 'IT & Infrastructure', amount: 4850.0, expenseDate: '2026-08-25', paymentMethod: 'CREDIT_CARD', reference: 'INV-AWS-88391', status: 'APPROVED' },
    { id: 2, expenseNumber: 'EXP-1002', title: 'Office Fiber Internet & VoIP Infrastructure', category: 'Utilities', amount: 950.0, expenseDate: '2026-08-20', paymentMethod: 'BANK_TRANSFER', reference: 'ATT-99201', status: 'APPROVED' },
    { id: 3, expenseNumber: 'EXP-1003', title: 'Q3 Enterprise Marketing & Digital Ads', category: 'Marketing', amount: 12500.0, expenseDate: '2026-08-15', paymentMethod: 'BANK_TRANSFER', reference: 'MKT-Q3-AD', status: 'APPROVED' }
  ],
  products: [
    { id: 1, productCode: 'PRD-1001', name: 'Enterprise Cloud Server Pro X1', sku: 'SRV-X1-48C', barcode: '8901234567890', categoryName: 'Server Hardware', costPrice: 3200.0, sellingPrice: 5400.0, currentStock: 45, minStockAlert: 10, reorderQuantity: 20, active: true, lowStock: false },
    { id: 2, productCode: 'PRD-1002', name: 'Ultra Gigabit Managed Switch 48P', sku: 'NET-48P-SW', barcode: '8901234567891', categoryName: 'Server Hardware', costPrice: 850.0, sellingPrice: 1499.0, currentStock: 80, minStockAlert: 15, reorderQuantity: 30, active: true, lowStock: false },
    { id: 3, productCode: 'PRD-1003', name: 'Enterprise ERP 2026 Core Subscription', sku: 'LIC-ERP-2026', barcode: '8901234567892', categoryName: 'Enterprise Software', costPrice: 400.0, sellingPrice: 1200.0, currentStock: 500, minStockAlert: 20, reorderQuantity: 100, active: true, lowStock: false },
    { id: 4, productCode: 'PRD-1004', name: 'Heavy-Duty Aluminum 2U Chassis', sku: 'RAW-2U-CHAS', barcode: '8901234567893', categoryName: 'Raw Materials & Parts', costPrice: 120.0, sellingPrice: 250.0, currentStock: 8, minStockAlert: 15, reorderQuantity: 50, active: true, lowStock: true }
  ],
  warehouses: [
    { id: 1, code: 'WH-CENTRAL', name: 'Austin Main Central Logistics Hub', address: '7401 Metropolis Dr', city: 'Austin', state: 'TX', country: 'USA', managerName: 'Marcus Vance', contactPhone: '+1-512-555-0199', capacity: 50000, active: true },
    { id: 2, code: 'WH-WEST', name: 'Reno West Coast Fulfillment Center', address: '1200 USA Pkwy', city: 'Reno', state: 'NV', country: 'USA', managerName: 'David Kim', contactPhone: '+1-775-555-0188', capacity: 35000, active: true }
  ],
  customers: [
    { id: 1, customerCode: 'CUST-1001', name: 'Acme Global Technologies Inc', company: 'Acme Corp', email: 'procurement@acmeglobal.com', phone: '+1-800-555-9001', address: '100 Innovation Blvd, Suite 400', city: 'San Jose', country: 'USA', customerType: 'ENTERPRISE', creditStatus: 'EXCELLENT', totalSpend: 245000.0, active: true },
    { id: 2, customerCode: 'CUST-1002', name: 'Apex Health Systems LLC', company: 'Apex Health', email: 'it-director@apexhealth.org', phone: '+1-888-555-3344', address: '450 Medical Center Dr', city: 'Boston', country: 'USA', customerType: 'ENTERPRISE', creditStatus: 'GOOD', totalSpend: 128000.0, active: true }
  ],
  vendors: [
    { id: 1, vendorCode: 'VEN-1001', companyName: 'Intel & Micron Semiconductor Distribution', contactPerson: 'William Vance', email: 'orders@intel-distrib.com', phone: '+1-800-444-2200', address: '2200 Mission College Blvd', city: 'Santa Clara', country: 'USA', paymentTerms: 'NET_30', rating: 4.9, active: true },
    { id: 2, vendorCode: 'VEN-1002', companyName: 'Precision Metal Fab & Enclosures', contactPerson: 'Karen Miller', email: 'sales@precisionfab.com', phone: '+1-877-333-1199', address: '88 Industrial Way', city: 'Detroit', country: 'USA', paymentTerms: 'NET_45', rating: 4.7, active: true }
  ],
  salesOrders: [
    { id: 1, soNumber: 'SO-1001', customerId: 1, customerName: 'Acme Global Technologies Inc', orderDate: '2026-08-28', deliveryDate: '2026-09-05', subTotal: 54000.0, taxAmount: 4320.0, discountAmount: 1000.0, grandTotal: 57320.0, status: 'CONFIRMED', paymentStatus: 'PAID', salesRepName: 'Elena Rostova' },
    { id: 2, soNumber: 'SO-1002', customerId: 2, customerName: 'Apex Health Systems LLC', orderDate: '2026-08-29', deliveryDate: '2026-09-10', subTotal: 18000.0, taxAmount: 1440.0, discountAmount: 0.0, grandTotal: 19440.0, status: 'DELIVERED', paymentStatus: 'UNPAID', salesRepName: 'Elena Rostova' }
  ],
  purchaseOrders: [
    { id: 1, poNumber: 'PO-1001', vendorId: 1, vendorName: 'Intel & Micron Semiconductor Distribution', destinationWarehouseName: 'Austin Main Central Logistics Hub', orderDate: '2026-08-20', expectedDeliveryDate: '2026-08-28', subTotal: 32000.0, taxAmount: 1600.0, discountAmount: 0.0, grandTotal: 33600.0, status: 'RECEIVED', paymentStatus: 'PAID' },
    { id: 2, poNumber: 'PO-1002', vendorId: 2, vendorName: 'Precision Metal Fab & Enclosures', destinationWarehouseName: 'Austin Main Central Logistics Hub', orderDate: '2026-08-27', expectedDeliveryDate: '2026-09-04', subTotal: 12000.0, taxAmount: 600.0, discountAmount: 0.0, grandTotal: 12600.0, status: 'ISSUED', paymentStatus: 'UNPAID' }
  ],
  leads: [
    { id: 1, name: 'Global Retailers Cloud Transformation', company: 'OmniRetail Group', email: 'cio@omniretail.com', phone: '+1-212-555-8833', source: 'CONFERENCE', stage: 'PROPOSAL', estimatedValue: 185000.0, probability: 75, assignedToUsername: 'salesmanager' },
    { id: 2, name: 'Fintech Core Infrastructure Upgrade', company: 'Alpha Pay Global', email: 'tech@alphapay.io', phone: '+1-415-555-0912', source: 'WEBSITE', stage: 'QUALIFIED', estimatedValue: 95000.0, probability: 50, assignedToUsername: 'salesmanager' },
    { id: 3, name: 'Smart Logistics Warehouse IoT', company: 'Swift Cargo Network', email: 'vance@swiftcargo.com', phone: '+1-312-555-7711', source: 'REFERRAL', stage: 'NEW', estimatedValue: 120000.0, probability: 30, assignedToUsername: 'salesmanager' }
  ],
  projects: [
    { id: 1, projectCode: 'PRJ-1001', name: 'Global ERP Architecture & Cloud Scaling', description: 'Migration and multi-region deployment of core ERP microservices', clientName: 'Acme Global Technologies Inc', projectManagerName: 'Alex Rivers', startDate: '2026-06-01', endDate: '2026-12-31', budget: 250000.0, actualCost: 140000.0, progressPercentage: 65, status: 'IN_PROGRESS', priority: 'HIGH', totalTasks: 14, completedTasks: 9 },
    { id: 2, projectCode: 'PRJ-1002', name: 'AI Supply Chain Forecasting Engine', description: 'Demand prediction neural models and inventory automation', clientName: 'Apex Health Systems LLC', projectManagerName: 'Alex Rivers', startDate: '2026-07-15', endDate: '2026-11-30', budget: 180000.0, actualCost: 65000.0, progressPercentage: 40, status: 'IN_PROGRESS', priority: 'HIGH', totalTasks: 10, completedTasks: 4 }
  ],
  shipments: [
    { id: 1, shipmentNumber: 'SHP-1001', trackingNumber: 'TRK992019481', salesOrderNumber: 'SO-1001', customerName: 'Acme Global Technologies Inc', carrierName: 'FedEx Enterprise Freight', shipmentDate: '2026-08-29', estimatedDeliveryDate: '2026-09-02', status: 'IN_TRANSIT', destinationAddress: '100 Innovation Blvd, San Jose, CA' },
    { id: 2, shipmentNumber: 'SHP-1002', trackingNumber: 'TRK992019482', salesOrderNumber: 'SO-1002', customerName: 'Apex Health Systems LLC', carrierName: 'UPS Express Heavy', shipmentDate: '2026-08-25', estimatedDeliveryDate: '2026-08-28', actualDeliveryDate: '2026-08-28', status: 'DELIVERED', destinationAddress: '450 Medical Center Dr, Boston, MA' }
  ],
  assets: [
    { id: 1, assetCode: 'AST-1001', name: 'Dell PowerEdge Enterprise Cluster Rack (8 Nodes)', category: 'IT_EQUIPMENT', serialNumber: 'DELL-PE-8849102', purchaseDate: '2023-01-15', purchaseCost: 84000.0, currentValuation: 67200.0, usefulLifeYears: 5, location: 'Austin Data Center Room 3B', status: 'OPERATIONAL', assignedEmployeeName: 'Alex Rivers' },
    { id: 2, assetCode: 'AST-1002', name: 'Automated Laser SMT Pick & Place Machine', category: 'MACHINERY', serialNumber: 'SMT-AUTO-2024', purchaseDate: '2024-05-10', purchaseCost: 145000.0, currentValuation: 128000.0, usefulLifeYears: 10, location: 'Assembly Bay 2', status: 'OPERATIONAL', assignedEmployeeName: 'Marcus Vance' }
  ],
  auditLogs: [
    { id: 1, username: 'SYSTEM', action: 'DATABASE_SEED', module: 'SYSTEM_INIT', description: 'EnterprisePro ERP initial seed data loaded successfully.', ipAddress: '127.0.0.1', timestamp: '2026-08-31T09:00:00' },
    { id: 2, username: 'admin', action: 'ROLE_ASSIGNED', module: 'SECURITY', description: 'Assigned ROLE_FINANCE_MANAGER to user robert.sterling', ipAddress: '192.168.1.100', timestamp: '2026-08-31T09:15:00' },
    { id: 3, username: 'salesmanager', action: 'SALES_ORDER_CREATE', module: 'SALES', description: 'Generated Sales Order SO-1001 for Acme Global ($57,320)', ipAddress: '192.168.1.104', timestamp: '2026-08-31T09:25:00' }
  ],
  settings: [
    { id: 1, settingKey: 'company_name', settingValue: 'ENTERPRISEPRO ERP Systems Inc', category: 'GENERAL', description: 'Legal registered enterprise name' },
    { id: 2, settingKey: 'system_currency', settingValue: 'USD ($)', category: 'FINANCE', description: 'Base operational currency' },
    { id: 3, settingKey: 'tax_default_rate', settingValue: '8.25%', category: 'FINANCE', description: 'Default sales and value-added tax percentage' },
    { id: 4, settingKey: 'fiscal_year_start', settingValue: 'January 1st', category: 'FINANCE', description: 'Financial fiscal calendar cycle start date' },
    { id: 5, settingKey: 'security_mfa_required', settingValue: 'Enabled', category: 'SECURITY', description: 'Enforce two-factor authentication for managers' }
  ]
};
