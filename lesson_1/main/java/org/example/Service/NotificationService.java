package org.example.Service;

import org.example.Model.Budget;
import org.example.Model.User;
import org.example.Model.Transaction;
import org.example.Model.Admin;
import org.example.Model.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService {
    private final BudgetService budgetService;
    private final UserService userService;

    public NotificationService(BudgetService budgetService, UserService userService) {
        this.budgetService = budgetService;
        this.userService = userService;
    }

    public void sendBudgetExceedNotification(String userId) {
        double remainingBudget = BudgetService.getRemainingBudget(userId);
        if (remainingBudget < 0) {
            System.out.println("Бюджет не установлен для пользователя с ID: " + userId);
            return;
        }

        if (remainingBudget < 0) {
            System.out.println("Внимание! Вы превысили бюджет для пользователя с ID: " + userId);
            User user = userService.login(userId, ""); // Пример использования логина, можно передать пустой пароль
            if (user != null) {
                System.out.println("Отправка уведомления пользователю: " + user.getName());
                // Здесь может быть отправка уведомления через email
            }
        }
    }
}
