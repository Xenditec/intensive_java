package org.example.Service;

import org.example.Model.Budget;


import java.util.HashMap;
import java.util.Map;

public class BudgetService {
    private static Map<String, Budget> budgets = new HashMap();

    public static void addBudgetMonth(String userId, double monthlyLimit){
        budgets.put(userId, new Budget(userId, monthlyLimit));
    }

    public static void addBudgetExpense(String userId, double amount){
        Budget budget = budgets.get(userId);
        if (budget != null) {
            budget.addExpense(amount);
            System.out.println("Добавлен расход: " + amount);


            if (budget.isOverBudget()) {
                System.out.println("Внимание! Вы превысили бюджет");
            }

        }else{
            System.out.println("Бюджет не установлен");
        }
    }
    public static double getRemainingBudget(String userId){
        Budget budget = budgets.get(userId);
        return (budget != null) ? budget.getRemainingBudget(): -1;
    }

    public static void editBudget(String userId, double newLimit) {
        Budget budget = budgets.get(userId);
        if (budget != null) {
            budget.setMonthlyLimit(newLimit);
            System.out.println("Новый бюджетный лимит установлен: " + newLimit);
        } else {
            System.out.println("Бюджет не найден.");
        }
    }

    public static void removeBudget(String userId) {
        if (budgets.remove(userId) != null) {
            System.out.println("Бюджет удалён.");
        } else {
            System.out.println("Бюджет не найден.");
        }
    }
}
