package com.enterprisepro.erp.config;

import com.enterprisepro.erp.entity.*;
import com.enterprisepro.erp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CompanySettingRepository companySettingRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            seedDatabase();
        }
    }

    private void seedDatabase() {
        // 1. Roles
        String[] roleNames = {
                "ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_HR_MANAGER", "ROLE_HR_EXECUTIVE",
                "ROLE_FINANCE_MANAGER", "ROLE_ACCOUNTANT", "ROLE_PAYROLL_MANAGER", "ROLE_INVENTORY_MANAGER",
                "ROLE_WAREHOUSE_MANAGER", "ROLE_PROCUREMENT_MANAGER", "ROLE_PURCHASE_MANAGER", "ROLE_SALES_MANAGER",
                "ROLE_CRM_MANAGER", "ROLE_PROJECT_MANAGER", "ROLE_MANUFACTURING_MANAGER", "ROLE_AUDITOR", "ROLE_EMPLOYEE"
        };

        Map<String, Role> roleMap = new HashMap<>();
        for (String roleName : roleNames) {
            Role role = new Role(roleName, "Role for " + roleName.replace("ROLE_", "").replace("_", " "));
            roleMap.put(roleName, roleRepository.save(role));
        }

        // 2. Users
        User admin = new User("admin", "admin@enterprisepro.com", passwordEncoder.encode("admin123"), "Super Administrator");
        admin.setRoles(new HashSet<>(roleMap.values()));
        admin.setPhone("+1-555-0100");
        userRepository.save(admin);

        User hrManager = new User("hrmanager", "hr@enterprisepro.com", passwordEncoder.encode("admin123"), "Sarah Jenkins (HR Lead)");
        hrManager.setRoles(Set.of(roleMap.get("ROLE_HR_MANAGER"), roleMap.get("ROLE_EMPLOYEE")));
        userRepository.save(hrManager);

        User finManager = new User("financemanager", "finance@enterprisepro.com", passwordEncoder.encode("admin123"), "Robert Sterling (CFO)");
        finManager.setRoles(Set.of(roleMap.get("ROLE_FINANCE_MANAGER"), roleMap.get("ROLE_ACCOUNTANT"), roleMap.get("ROLE_EMPLOYEE")));
        userRepository.save(finManager);

        User invManager = new User("inventorymanager", "inventory@enterprisepro.com", passwordEncoder.encode("admin123"), "Marcus Vance (Inventory Lead)");
        invManager.setRoles(Set.of(roleMap.get("ROLE_INVENTORY_MANAGER"), roleMap.get("ROLE_WAREHOUSE_MANAGER"), roleMap.get("ROLE_EMPLOYEE")));
        userRepository.save(invManager);

        User salesManager = new User("salesmanager", "sales@enterprisepro.com", passwordEncoder.encode("admin123"), "Elena Rostova (Sales Director)");
        salesManager.setRoles(Set.of(roleMap.get("ROLE_SALES_MANAGER"), roleMap.get("ROLE_CRM_MANAGER"), roleMap.get("ROLE_EMPLOYEE")));
        userRepository.save(salesManager);

        // 3. Departments
        Department engineering = departmentRepository.save(new Department("Engineering & IT", "ENG", "Software engineering, infrastructure & systems", "Alex Rivers"));
        Department hr = departmentRepository.save(new Department("Human Resources", "HR", "Talent recruitment, culture and operations", "Sarah Jenkins"));
        Department finance = departmentRepository.save(new Department("Finance & Accounts", "FIN", "Financial planning, accounting and payroll", "Robert Sterling"));
        Department sales = departmentRepository.save(new Department("Sales & Marketing", "SAL", "Enterprise sales, client acquisition & marketing", "Elena Rostova"));
        Department operations = departmentRepository.save(new Department("Operations & Logistics", "OPS", "Supply chain, inventory, warehousing and production", "Marcus Vance"));

        // 4. Designations
        Designation leadArch = designationRepository.save(new Designation("Principal Solutions Architect", engineering, BigDecimal.valueOf(140000), BigDecimal.valueOf(190000), "Technical architecture leader"));
        Designation srDev = designationRepository.save(new Designation("Senior Full Stack Engineer", engineering, BigDecimal.valueOf(100000), BigDecimal.valueOf(145000), "Senior software development"));
        Designation hrSpec = designationRepository.save(new Designation("Senior HR Generalist", hr, BigDecimal.valueOf(70000), BigDecimal.valueOf(95000), "Employee relations & compensation"));
        Designation srAcct = designationRepository.save(new Designation("Senior Financial Controller", finance, BigDecimal.valueOf(90000), BigDecimal.valueOf(125000), "Ledger auditing and tax filings"));
        Designation salesExec = designationRepository.save(new Designation("Enterprise Account Executive", sales, BigDecimal.valueOf(80000), BigDecimal.valueOf(130000), "Enterprise client acquisition"));

        // 5. Employees
        Employee emp1 = new Employee("EMP-1001", "Alex", "Rivers", "alex.rivers@enterprisepro.com", "+1-555-0101",
                engineering, leadArch, LocalDate.of(1988, 4, 12), LocalDate.of(2021, 3, 1), BigDecimal.valueOf(160000), "ACTIVE");
        emp1.setUser(admin);
        employeeRepository.save(emp1);

        Employee emp2 = new Employee("EMP-1002", "Sarah", "Jenkins", "sarah.j@enterprisepro.com", "+1-555-0102",
                hr, hrSpec, LocalDate.of(1991, 8, 22), LocalDate.of(2022, 1, 15), BigDecimal.valueOf(88000), "ACTIVE");
        emp2.setManager(emp1);
        emp2.setUser(hrManager);
        employeeRepository.save(emp2);

        Employee emp3 = new Employee("EMP-1003", "Robert", "Sterling", "robert.s@enterprisepro.com", "+1-555-0103",
                finance, srAcct, LocalDate.of(1985, 11, 5), LocalDate.of(2020, 6, 1), BigDecimal.valueOf(120000), "ACTIVE");
        emp3.setManager(emp1);
        emp3.setUser(finManager);
        employeeRepository.save(emp3);

        Employee emp4 = new Employee("EMP-1004", "Elena", "Rostova", "elena.r@enterprisepro.com", "+1-555-0104",
                sales, salesExec, LocalDate.of(1993, 2, 18), LocalDate.of(2023, 4, 10), BigDecimal.valueOf(95000), "ACTIVE");
        emp4.setManager(emp1);
        emp4.setUser(salesManager);
        employeeRepository.save(emp4);

        Employee emp5 = new Employee("EMP-1005", "Marcus", "Vance", "marcus.v@enterprisepro.com", "+1-555-0105",
                operations, srDev, LocalDate.of(1990, 7, 30), LocalDate.of(2022, 9, 1), BigDecimal.valueOf(110000), "ACTIVE");
        emp5.setManager(emp1);
        emp5.setUser(invManager);
        employeeRepository.save(emp5);

        // 6. Chart of Accounts
        Account a1000 = accountRepository.save(new Account("1000", "Operating Cash Account", "ASSET", "CASH", BigDecimal.valueOf(150000.00)));
        Account a1010 = accountRepository.save(new Account("1010", "Main Commercial Bank Checking", "ASSET", "BANK", BigDecimal.valueOf(485000.00)));
        Account a1200 = accountRepository.save(new Account("1200", "Accounts Receivable", "ASSET", "RECEIVABLE", BigDecimal.valueOf(45200.00)));
        Account a1300 = accountRepository.save(new Account("1300", "Inventory Assets & Stock", "ASSET", "INVENTORY", BigDecimal.valueOf(354000.00)));
        Account a1500 = accountRepository.save(new Account("1500", "Machinery & IT Equipment", "ASSET", "FIXED_ASSET", BigDecimal.valueOf(220000.00)));

        Account a2000 = accountRepository.save(new Account("2000", "Accounts Payable - Suppliers", "LIABILITY", "PAYABLE", BigDecimal.valueOf(18900.00)));
        Account a2100 = accountRepository.save(new Account("2100", "Payroll & Bonus Accruals", "LIABILITY", "ACCRUED", BigDecimal.valueOf(32000.00)));

        Account a3000 = accountRepository.save(new Account("3000", "Owner Paid-In Capital", "EQUITY", "CAPITAL", BigDecimal.valueOf(700000.00)));
        Account a3100 = accountRepository.save(new Account("3100", "Retained Earnings", "EQUITY", "RETAINED_EARNINGS", BigDecimal.valueOf(419100.00)));

        Account a4000 = accountRepository.save(new Account("4000", "Enterprise Software License Revenue", "REVENUE", "SALES", BigDecimal.valueOf(450000.00)));
        Account a4100 = accountRepository.save(new Account("4100", "Cloud Consulting & Managed Services", "REVENUE", "SERVICES", BigDecimal.valueOf(392000.00)));

        Account a5000 = accountRepository.save(new Account("5000", "Cost of Goods Sold (COGS)", "EXPENSE", "COGS", BigDecimal.valueOf(180000.00)));
        Account a6000 = accountRepository.save(new Account("6000", "Salaries & Benefits Expense", "EXPENSE", "OPERATING", BigDecimal.valueOf(125000.00)));
        Account a6100 = accountRepository.save(new Account("6100", "Facility Rent & Utilities", "EXPENSE", "OPERATING", BigDecimal.valueOf(35000.00)));

        // 7. Warehouses
        Warehouse wh1 = new Warehouse();
        wh1.setCode("WH-CENTRAL");
        wh1.setName("Austin Main Central Logistics Hub");
        wh1.setAddress("7401 Metropolis Dr");
        wh1.setCity("Austin");
        wh1.setState("TX");
        wh1.setCountry("USA");
        wh1.setManagerName("Marcus Vance");
        wh1.setContactPhone("+1-512-555-0199");
        wh1.setCapacity(50000);
        wh1.setActive(true);
        warehouseRepository.save(wh1);

        Warehouse wh2 = new Warehouse();
        wh2.setCode("WH-WEST");
        wh2.setName("Reno West Coast Fulfillment Center");
        wh2.setAddress("1200 USA Pkwy");
        wh2.setCity("Reno");
        wh2.setState("NV");
        wh2.setCountry("USA");
        wh2.setManagerName("David Kim");
        wh2.setContactPhone("+1-775-555-0188");
        wh2.setCapacity(35000);
        wh2.setActive(true);
        warehouseRepository.save(wh2);

        // 8. Categories, Brands & Units
        Category cSoftware = categoryRepository.save(new Category("Enterprise Software", "CAT-SW", "Software licenses and subscriptions"));
        Category cServers = categoryRepository.save(new Category("Server Hardware", "CAT-SRV", "Rackmount servers, switches and arrays"));
        Category cComponents = categoryRepository.save(new Category("Raw Materials & Parts", "CAT-RAW", "Electronic boards, cases and chips"));

        Brand bProCorp = brandRepository.save(new Brand("EnterprisePro", "PRO", "Our flagship branded product lines"));
        Brand bCisco = brandRepository.save(new Brand("Cisco Systems", "CSCO", "Enterprise networking gear"));
        Brand bDell = brandRepository.save(new Brand("Dell EMC", "DELL", "PowerEdge and rack server hardware"));

        Unit uPcs = unitRepository.save(new Unit("Pieces", "PCS", false));
        Unit uLic = unitRepository.save(new Unit("Licenses", "LIC", false));
        Unit uSet = unitRepository.save(new Unit("Sets", "SET", false));

        // 9. Products
        Product p1 = new Product("PRD-1001", "Enterprise Cloud Server Pro X1", "SRV-X1-48C",
                BigDecimal.valueOf(3200.00), BigDecimal.valueOf(5400.00), 45, 10, cServers);
        p1.setBrand(bDell);
        p1.setUnit(uPcs);
        p1.setBarcode("8901234567890");
        productRepository.save(p1);

        Product p2 = new Product("PRD-1002", "Ultra Gigabit Managed Switch 48P", "NET-48P-SW",
                BigDecimal.valueOf(850.00), BigDecimal.valueOf(1499.00), 80, 15, cServers);
        p2.setBrand(bCisco);
        p2.setUnit(uPcs);
        p2.setBarcode("8901234567891");
        productRepository.save(p2);

        Product p3 = new Product("PRD-1003", "Enterprise ERP 2026 Core Subscription", "LIC-ERP-2026",
                BigDecimal.valueOf(400.00), BigDecimal.valueOf(1200.00), 500, 20, cSoftware);
        p3.setBrand(bProCorp);
        p3.setUnit(uLic);
        p3.setBarcode("8901234567892");
        productRepository.save(p3);

        Product p4 = new Product("PRD-1004", "Heavy-Duty Aluminum 2U Chassis", "RAW-2U-CHAS",
                BigDecimal.valueOf(120.00), BigDecimal.valueOf(250.00), 150, 25, cComponents);
        p4.setBrand(bProCorp);
        p4.setUnit(uPcs);
        p4.setBarcode("8901234567893");
        productRepository.save(p4);

        // 10. Customers
        Customer cust1 = new Customer();
        cust1.setCustomerCode("CUST-1001");
        cust1.setName("Acme Global Technologies Inc");
        cust1.setCompany("Acme Corp");
        cust1.setEmail("procurement@acmeglobal.com");
        cust1.setPhone("+1-800-555-9001");
        cust1.setAddress("100 Innovation Blvd, Suite 400");
        cust1.setCity("San Jose");
        cust1.setCountry("USA");
        cust1.setTaxNumber("US-8941092");
        cust1.setCustomerType("ENTERPRISE");
        cust1.setCreditStatus("EXCELLENT");
        cust1.setTotalSpend(245000.00);
        customerRepository.save(cust1);

        Customer cust2 = new Customer();
        cust2.setCustomerCode("CUST-1002");
        cust2.setName("Apex Health Systems LLC");
        cust2.setCompany("Apex Health");
        cust2.setEmail("it-director@apexhealth.org");
        cust2.setPhone("+1-888-555-3344");
        cust2.setAddress("450 Medical Center Dr");
        cust2.setCity("Boston");
        cust2.setCountry("USA");
        cust2.setTaxNumber("US-4491023");
        cust2.setCustomerType("ENTERPRISE");
        cust2.setCreditStatus("GOOD");
        cust2.setTotalSpend(128000.00);
        customerRepository.save(cust2);

        // 11. Vendors
        Vendor ven1 = new Vendor();
        ven1.setVendorCode("VEN-1001");
        ven1.setCompanyName("Intel & Micron Semiconductor Distribution");
        ven1.setContactPerson("William Vance");
        ven1.setEmail("orders@intel-distrib.com");
        ven1.setPhone("+1-800-444-2200");
        ven1.setAddress("2200 Mission College Blvd");
        ven1.setCity("Santa Clara");
        ven1.setCountry("USA");
        ven1.setPaymentTerms("NET_30");
        ven1.setRating(4.9);
        vendorRepository.save(ven1);

        Vendor ven2 = new Vendor();
        ven2.setVendorCode("VEN-1002");
        ven2.setCompanyName("Precision Metal Fab & Enclosures");
        ven2.setContactPerson("Karen Miller");
        ven2.setEmail("sales@precisionfab.com");
        ven2.setPhone("+1-877-333-1199");
        ven2.setAddress("88 Industrial Way");
        ven2.setCity("Detroit");
        ven2.setCountry("USA");
        ven2.setPaymentTerms("NET_45");
        ven2.setRating(4.7);
        vendorRepository.save(ven2);

        // 12. CRM Leads
        Lead lead1 = new Lead();
        lead1.setName("Global Retailers Cloud Transformation");
        lead1.setCompany("OmniRetail Group");
        lead1.setEmail("cio@omniretail.com");
        lead1.setPhone("+1-212-555-8833");
        lead1.setSource("CONFERENCE");
        lead1.setStage("PROPOSAL");
        lead1.setEstimatedValue(BigDecimal.valueOf(185000.00));
        lead1.setProbability(75);
        lead1.setAssignedTo(salesManager);
        lead1.setNotes("Enterprise ERP rollout across 120 retail outlets.");
        leadRepository.save(lead1);

        Lead lead2 = new Lead();
        lead2.setName("Fintech Core Infrastructure Upgrade");
        lead2.setCompany("Alpha Pay Global");
        lead2.setEmail("tech@alphapay.io");
        lead2.setPhone("+1-415-555-0912");
        lead2.setSource("WEBSITE");
        lead2.setStage("QUALIFIED");
        lead2.setEstimatedValue(BigDecimal.valueOf(95000.00));
        lead2.setProbability(50);
        lead2.setAssignedTo(salesManager);
        leadRepository.save(lead2);

        // 13. Projects & Tasks
        Project proj1 = new Project("PRJ-1001", "Global ERP Architecture & Cloud Scaling",
                "Migration and multi-region deployment of core ERP microservices",
                cust1, emp1, LocalDate.now().minusMonths(2), LocalDate.now().plusMonths(4),
                BigDecimal.valueOf(250000.00), "IN_PROGRESS", "HIGH");
        proj1.setProgressPercentage(65);
        proj1.setActualCost(BigDecimal.valueOf(140000.00));
        Project savedProj = projectRepository.save(proj1);

        Task t1 = new Task();
        t1.setProject(savedProj);
        t1.setTitle("Deploy Stateless Microservices on Kubernetes Cluster");
        t1.setDescription("Set up auto-scaling pods and ingress controller with TLS certificates.");
        t1.setStatus("COMPLETED");
        t1.setPriority("HIGH");
        t1.setDueDate(LocalDate.now().minusDays(5));
        t1.setEstimatedHours(40);
        t1.setLoggedHours(38);
        t1.setAssignedTo(emp1);
        taskRepository.save(t1);

        Task t2 = new Task();
        t2.setProject(savedProj);
        t2.setTitle("Automated Database Replication & Disaster Recovery Test");
        t2.setDescription("Verify cross-region read-replicas failover latency.");
        t2.setStatus("IN_PROGRESS");
        t2.setPriority("HIGH");
        t2.setDueDate(LocalDate.now().plusDays(10));
        t2.setEstimatedHours(30);
        t2.setLoggedHours(15);
        t2.setAssignedTo(emp1);
        taskRepository.save(t2);

        // 14. Fixed Assets
        Asset asset1 = new Asset();
        asset1.setAssetCode("AST-1001");
        asset1.setName("Dell PowerEdge Enterprise Cluster Rack (8 Nodes)");
        asset1.setCategory("IT_EQUIPMENT");
        asset1.setSerialNumber("DELL-PE-8849102");
        asset1.setPurchaseDate(LocalDate.of(2023, 1, 15));
        asset1.setPurchaseCost(BigDecimal.valueOf(84000.00));
        asset1.setCurrentValuation(BigDecimal.valueOf(67200.00));
        asset1.setUsefulLifeYears(5);
        asset1.setSalvageValue(BigDecimal.valueOf(8000.00));
        asset1.setLocation("Austin Data Center Room 3B");
        asset1.setStatus("OPERATIONAL");
        asset1.setAssignedTo(emp1);
        assetRepository.save(asset1);

        // 15. Settings
        companySettingRepository.save(new CompanySetting("company_name", "ENTERPRISEPRO ERP Systems Inc", "GENERAL", "Legal registered enterprise name"));
        companySettingRepository.save(new CompanySetting("system_currency", "USD", "FINANCE", "Base operational currency ($)"));
        companySettingRepository.save(new CompanySetting("tax_default_rate", "8.25", "FINANCE", "Default sales and value-added tax percentage"));
        companySettingRepository.save(new CompanySetting("fiscal_year_start", "01-01", "FINANCE", "Financial fiscal calendar cycle start date"));
        companySettingRepository.save(new CompanySetting("security_mfa_required", "true", "SECURITY", "Enforce two-factor authentication"));

        // 16. System Notifications
        Notification notif1 = new Notification();
        notif1.setRecipient(admin);
        notif1.setTitle("Monthly Financial Closing Completed");
        notif1.setMessage("General ledger balanced. Financial reports and Trial Balance ready for review.");
        notif1.setType("SUCCESS");
        notif1.setCategory("FINANCE");
        notif1.setLink("/finance");
        notificationRepository.save(notif1);

        Notification notif2 = new Notification();
        notif2.setRecipient(admin);
        notif2.setTitle("Low Stock Alert: Server Hardware");
        notif2.setMessage("Dell PowerEdge units at Austin Hub below threshold (10 units). Auto PO prepared.");
        notif2.setType("WARNING");
        notif2.setCategory("INVENTORY");
        notif2.setLink("/inventory");
        notificationRepository.save(notif2);

        // 17. Initial Audit Log
        AuditLog audit1 = new AuditLog();
        audit1.setUsername("SYSTEM");
        audit1.setAction("DATABASE_SEED");
        audit1.setModule("SYSTEM_INIT");
        audit1.setDescription("EnterprisePro ERP initial seed data loaded successfully with 17 RBAC roles and master records.");
        audit1.setIpAddress("127.0.0.1");
        audit1.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(audit1);
    }
}
