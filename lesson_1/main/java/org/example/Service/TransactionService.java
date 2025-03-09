package org.example.Service;

import org.example.Model.Transaction;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {
    private static Map<String, List<Transaction>> transactions = new HashMap<>();

    public static void addTransactions(String userID, double amount, String category, String type, LocalDate date, String description){
        Transaction transaction = new Transaction(userID, amount, category, type, date, description);
        transactions.computeIfAbsent(userID, k -> new ArrayList<>()).add(transaction);
    }

    public static void listTransactions(String userId){
        List<Transaction> userTransactions = transactions.getOrDefault(userId, new ArrayList<>());
        if (userTransactions.isEmpty()){
            System.out.println("Транзакций нет.");
            return;
        }
        for (Transaction t : userTransactions){
            System.out.println(t.getDate()+ " | " + t.getType() + " | " + t.getCategory() + " | " + t.getAmount() + " | " + t.getDescription());
        }
    }
    public static void deleteTransactions(String userId, String transcationId){
        List<Transaction> userTransactions = transactions.get(userId);
        if (userTransactions != null){
            userTransactions.removeIf(t -> t.getId().equals(transcationId));
            System.out.println("Транзакция удалена.");
        }
    }

    public static void editTransactions(String userId, String transactionId, double newAmount, String newCategory, String newDescription){
        List<Transaction> userTransactions = transactions.get(userId);
        if (userTransactions!=null){
            for (Transaction t : userTransactions){
                if (t.getId().equals(transactionId)){
                    t.setAmount(newAmount);
                    t.setCategory(newCategory);
                    t.setDescription(newDescription);
                    System.out.println("Транзакция была обновлена.");
                    return;
                }
            }
        }
        System.out.println("Транзакции не существует/не была найдена.");
    }

    public static List<Transaction> filteredTransactions (String userId, String category, String type, LocalDate startDate, LocalDate endDate){
        return transactions.getOrDefault(userId, Collections.emptyList()).stream()
                .filter(t -> (category == null || t.getCategory().equalsIgnoreCase(category)))
                .filter(t -> (type == null || t.getType().equalsIgnoreCase(type)))
                .filter(t -> (startDate == null || !t.getDate().isBefore(startDate)))
                .filter(t -> (endDate == null || !t.getDate().isAfter(endDate)))
                .collect(Collectors.toList());
    }
}
