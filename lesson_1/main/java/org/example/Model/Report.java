package org.example.Model;

import java.time.LocalDate;
import java.util.Map;

public class Report {
    private String userId; // Идентификатор пользователя
    private double currentBalance; // Текущий баланс
    private double totalIncome; // Общий доход за период
    private double totalExpense; // Общий расход за период
    private Map<String, Double> categoryExpenses; // Расходы по категориям
    private LocalDate reportPeriodStart; // Начало периода отчёта
    private LocalDate reportPeriodEnd; // Конец периода отчёта
    private LocalDate dateGenerated; // Дата генерации отчёта


    public Report(String userId, double currentBalance, double totalIncome, double totalExpense,
                  Map<String, Double> categoryExpenses, LocalDate reportPeriodStart, LocalDate reportPeriodEnd) {
        this.userId = userId;
        this.currentBalance = currentBalance;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.categoryExpenses = categoryExpenses;
        this.reportPeriodStart = reportPeriodStart;
        this.reportPeriodEnd = reportPeriodEnd;
        this.dateGenerated = LocalDate.now(); // Дата генерации отчёта
    }


    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public double getCurrentBalance() {return currentBalance;}
    public void setCurrentBalance(double currentBalance) {this.currentBalance = currentBalance;}
    public double getTotalIncome() {return totalIncome;}
    public void setTotalIncome(double totalIncome) {this.totalIncome = totalIncome;}
    public double getTotalExpense() {return totalExpense;}
    public void setTotalExpense(double totalExpense) {this.totalExpense = totalExpense;}
    public Map<String, Double> getCategoryExpenses() {return categoryExpenses;}
    public void setCategoryExpenses(Map<String, Double> categoryExpenses) {this.categoryExpenses = categoryExpenses;}
    public LocalDate getReportPeriodStart() {return reportPeriodStart;}
    public void setReportPeriodStart(LocalDate reportPeriodStart) {this.reportPeriodStart = reportPeriodStart;}
    public LocalDate getReportPeriodEnd() {return reportPeriodEnd;}
    public void setReportPeriodEnd(LocalDate reportPeriodEnd) {this.reportPeriodEnd = reportPeriodEnd;}
    public LocalDate getDateGenerated() {return dateGenerated;}
    public void setDateGenerated(LocalDate dateGenerated) {this.dateGenerated = dateGenerated;}
}
