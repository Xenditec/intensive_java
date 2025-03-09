package org.example.Service;

import org.example.Model.User;
import org.example.Model.Transaction;
import org.example.Service.TransactionService;

import java.util.List;

public class AdminService extends UserService {
    private final TransactionService transactionService;

    public AdminService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void deleteUser(String email) {
        User user = users.get(email);
        users.remove(email, user);
        System.out.println("Пользователь успешно удалён");

    }

    // Просмотр всех пользователей
    public static void viewAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Нет зарегистрированных пользователей.");
        } else {
            users.forEach((email, user) -> System.out.println(user.getName() + " | " + email + "|" + user.getId() + "|" + user.getBlocked()));
        }
    }

    // Блокировка пользователя
    public static void blockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setBlocked(true); // блокируем пользователя
            System.out.println("Пользователь с email " + email + " заблокирован.");
        } else {
            System.out.println("Пользователь не найден.");
        }
    }

    // Просмотр транзакций пользователя
    public static void viewUserTransactions(String email) {
        User user = users.get(email);
        if (user != null) {
            System.out.println("Транзакции пользователя с email " + email + ":");
            TransactionService.listTransactions(user.getId()); // выводим транзакции
        } else {
            System.out.println("Пользователь не найден.");
        }
    }

    public void unblockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setBlocked(false); // разблокируем пользователя
            System.out.println("Пользователь с email " + email + " разблокирован.");
        } else {
            System.out.println("Пользователь не найден.");
        }
    }

}
