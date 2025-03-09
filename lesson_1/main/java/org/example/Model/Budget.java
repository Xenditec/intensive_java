package org.example.Model;

public class Budget {
    private String userId;
    private double monthlyLimit;
    private double currentExpenses;

    public Budget(String userId, double monthlyLimit) {
        this.userId = userId;
        this.monthlyLimit = monthlyLimit;
    }

    public String getUserId() {return userId;}
    public void setUserId(String userId) {this.userId = userId;}
    public double getMonthlyLimit() {return monthlyLimit;}

    public void setMonthlyLimit(double monthlyLimit) {this.monthlyLimit = monthlyLimit;}
    public double getCurrentExpenses() {return currentExpenses;}
    public void setCurrentExpenses(double currentExpenses) {this.currentExpenses = currentExpenses;}

    public void addExpense(double amount) {
        this.currentExpenses += amount;
    }

    public boolean isOverBudget() {
        return currentExpenses > monthlyLimit;
    }

    public double getRemainingBudget() {
        return monthlyLimit - currentExpenses;
    }
}
