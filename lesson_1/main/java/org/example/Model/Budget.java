package org.example.Model;

public class Budget {
    private String userId;
    private double monthlyLimit;
    private double currentExpenses;
    private double balance;

    public Budget(String userId, double monthlyLimit) {
        this.userId = userId;
        this.monthlyLimit = monthlyLimit;
    }


    // Сеттеры
    public void setUserId(String userId) {this.userId = userId;}
    public void setMonthlyLimit(double monthlyLimit) {this.monthlyLimit = monthlyLimit;}
    public void setCurrentExpenses(double currentExpenses) {this.currentExpenses = currentExpenses;}
    public void setBalance(double balance){this.balance = balance;}

    // Гетттеры
    public String getUserId() {return userId;}
    public double getMonthlyLimit() {return monthlyLimit;}
    public double getCurrentExpenses() {return currentExpenses;}
    public double getRemainingBudget() {
        return monthlyLimit - currentExpenses;
    }
    public double getBalance() {return balance;}

    public void addExpense(double amount) {
        this.currentExpenses += amount;
    }
    public boolean isOverBudget() {
        return currentExpenses > monthlyLimit;
    }
}
