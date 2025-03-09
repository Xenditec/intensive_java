package org.example.Service;

import org.example.Model.Budget;


import java.util.HashMap;
import java.util.Map;

public class BudgetService {
    private static Map<String, Budget> budgets = new HashMap();

    public static void addBudgetMonth(String userId, double monthlyLimit){
        budgets.put(userId, new Budget(userId, monthlyLimit));
    }

    public static void addBudgetExpensive(String userId, double amount){
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
}
