package org.example.Service;

import org.example.Model.Report;
import org.example.Model.Transaction;

import java.time.LocalDate;
import java.util.*;

public class ReportService {
    private static Map<String, List<Transaction>> report = new HashMap<>();



    // Метод для подсчёта текущего баланса
    public static double calculateCurrentBalance(String userId) {
        double balance = 0;
        List<Transaction> userTransactions = report.getOrDefault(userId, Collections.emptyList());
        for (Transaction t : userTransactions){
            if (t.getType().trim().equalsIgnoreCase("income")){
                balance += t.getAmount();
            }
            else if(t.getType().trim().equalsIgnoreCase("expense")){
                balance -= t.getAmount();
            }
        }
        return balance;
    }

    // Метод для расчёта суммарного дохода за период
    public static double calculateTotalIncome(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("income"))
                .filter(t -> (startDate == null || !t.getDate().isBefore(startDate)))
                .filter(t -> (endDate == null || !t.getDate().isAfter(endDate)))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Метод для расчёта суммарных расходов за период
    public static double calculateTotalExpense(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("expense"))
                .filter(t -> (startDate == null || !t.getDate().isBefore(startDate)))
                .filter(t -> (endDate == null || !t.getDate().isAfter(endDate)))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Метод для анализа расходов по категориям
    public static Map<String, Double> analyzeExpensesByCategory(String userId) {
       List<Transaction> transactions = TransactionService.getUserTransaction(userId);
        Map<String, Double> categoryExpenses = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().trim().equalsIgnoreCase("expense")) {
                System.out.println("Добавляем в категорию " + transaction.getCategory() + ": " + transaction.getAmount());
                categoryExpenses.put(transaction.getCategory(),
                        categoryExpenses.getOrDefault(transaction.getCategory(), 0.0) + transaction.getAmount());
            }
        }
        return categoryExpenses;
    }

    // Метод для формирования отчёта
    public static Report generateReport(String userId, LocalDate startDate, LocalDate endDate) {
        // Получаем все транзакции пользователя
        List<Transaction> userTransactions = TransactionService.filteredTransactions(userId, null, null, startDate, endDate);
        // Рассчитываем текущий баланс
        double currentBalance = calculateCurrentBalance(userId);
        // Рассчитываем суммарный доход
        double totalIncome = calculateTotalIncome(userTransactions, startDate, endDate);
        // Рассчитываем суммарный расход
        double totalExpense = calculateTotalExpense(userTransactions, startDate, endDate);
        // Анализируем расходы по категориям
        Map<String, Double> categoryExpenses = analyzeExpensesByCategory(userId);
        // Генерируем отчёт
                return new Report(userId, currentBalance, totalIncome, totalExpense, categoryExpenses, startDate, endDate);
    }
}
