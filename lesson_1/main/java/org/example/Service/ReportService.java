package org.example.Service;

import org.example.Model.Report;
import org.example.Model.Transaction;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {



    // Метод для подсчёта текущего баланса
    public static double calculateCurrentBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("income")) {
                balance += transaction.getAmount();
            } else if (transaction.getType().equalsIgnoreCase("expense")) {
                balance -= transaction.getAmount();
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
    public static Map<String, Double> analyzeExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> categoryExpenses = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equalsIgnoreCase("expense")) {
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
        double currentBalance = calculateCurrentBalance(userTransactions);

        // Рассчитываем суммарный доход
        double totalIncome = calculateTotalIncome(userTransactions, startDate, endDate);

        // Рассчитываем суммарный расход
        double totalExpense = calculateTotalExpense(userTransactions, startDate, endDate);

        // Анализируем расходы по категориям
        Map<String, Double> categoryExpenses = analyzeExpensesByCategory(userTransactions);

        // Генерируем отчёт
        return new Report(userId, currentBalance, totalIncome, totalExpense, categoryExpenses, startDate, endDate);
    }
}
