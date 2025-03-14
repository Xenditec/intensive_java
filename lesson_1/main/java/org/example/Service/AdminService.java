package org.example.Service;

import org.example.Model.User;
import org.example.Model.Transaction;
import org.example.Service.TransactionService;

import java.util.List;
import java.util.Map;

public class AdminService extends UserService {
    /**
     * @param userId Пользователь должен иметь возможность себя удалить тоже (Перенести метод deleteUser -> В UserService)
     */
    public static void deleteUser(String userId) {
        User user = users.get(userId);
        users.remove(userId, user);
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

    public static void unblockUser(String email) {
        User user = users.get(email);
        if (user != null) {
            user.setBlocked(false); // разблокируем пользователя
            System.out.println("Пользователь с email " + email + " разблокирован.");
        } else {
            System.out.println("Пользователь не найден.");
        }
    }


    public static void viewAllTransactions(Map<String, User> users) {

        System.out.println("Вывод транзакций всех пользователей:");
        for (User user : users.values()) {
            System.out.println("Пользователь с email: " + user.getEmail());
            TransactionService.listOfAllTransactions(user.getId());
        }
    }
}
