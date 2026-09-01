package com.enterprisepro.erp.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DashboardSummaryDto {

    private long totalEmployees;
    private long activeEmployees;
    private BigDecimal monthlyRevenue;
    private BigDecimal monthlyExpenses;
    private BigDecimal netProfit;
    private BigDecimal totalSales;
    private long pendingOrders;
    private Double inventoryValuation;
    private long lowStockCount;
    private long totalCustomers;
    private long totalVendors;
    private BigDecimal pendingPayables;
    private BigDecimal pendingReceivables;
    private long activeProjects;
    private long completedProjects;
    private long pendingLeaves;
    private long unreadNotifications;

    private List<Map<String, Object>> monthlyRevenueChart;
    private List<Map<String, Object>> departmentEmployeeDistribution;
    private List<Map<String, Object>> salesByProductCategory;
    private List<Map<String, Object>> recentActivities;

    public DashboardSummaryDto() {}

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(long activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public BigDecimal getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(BigDecimal monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public BigDecimal getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(BigDecimal monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public Double getInventoryValuation() {
        return inventoryValuation;
    }

    public void setInventoryValuation(Double inventoryValuation) {
        this.inventoryValuation = inventoryValuation;
    }

    public long getLowStockCount() {
        return lowStockCount;
    }

    public void setLowStockCount(long lowStockCount) {
        this.lowStockCount = lowStockCount;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(long totalVendors) {
        this.totalVendors = totalVendors;
    }

    public BigDecimal getPendingPayables() {
        return pendingPayables;
    }

    public void setPendingPayables(BigDecimal pendingPayables) {
        this.pendingPayables = pendingPayables;
    }

    public BigDecimal getPendingReceivables() {
        return pendingReceivables;
    }

    public void setPendingReceivables(BigDecimal pendingReceivables) {
        this.pendingReceivables = pendingReceivables;
    }

    public long getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(long activeProjects) {
        this.activeProjects = activeProjects;
    }

    public long getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(long completedProjects) {
        this.completedProjects = completedProjects;
    }

    public long getPendingLeaves() {
        return pendingLeaves;
    }

    public void setPendingLeaves(long pendingLeaves) {
        this.pendingLeaves = pendingLeaves;
    }

    public long getUnreadNotifications() {
        return unreadNotifications;
    }

    public void setUnreadNotifications(long unreadNotifications) {
        this.unreadNotifications = unreadNotifications;
    }

    public List<Map<String, Object>> getMonthlyRevenueChart() {
        return monthlyRevenueChart;
    }

    public void setMonthlyRevenueChart(List<Map<String, Object>> monthlyRevenueChart) {
        this.monthlyRevenueChart = monthlyRevenueChart;
    }

    public List<Map<String, Object>> getDepartmentEmployeeDistribution() {
        return departmentEmployeeDistribution;
    }

    public void setDepartmentEmployeeDistribution(List<Map<String, Object>> departmentEmployeeDistribution) {
        this.departmentEmployeeDistribution = departmentEmployeeDistribution;
    }

    public List<Map<String, Object>> getSalesByProductCategory() {
        return salesByProductCategory;
    }

    public void setSalesByProductCategory(List<Map<String, Object>> salesByProductCategory) {
        this.salesByProductCategory = salesByProductCategory;
    }

    public List<Map<String, Object>> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<Map<String, Object>> recentActivities) {
        this.recentActivities = recentActivities;
    }
}
