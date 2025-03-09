package org.example;

import org.example.Model.User;
import org.example.Service.AdminService;
import org.example.Service.TransactionService;
import org.example.Service.UserService;

import java.util.Scanner;
import java.util.UUID;

public class FinanceTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminService adminService = new AdminService(new TransactionService());
    private static final UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Добро пожаловать в систему управления финансами!");
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Выход из приложения.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private static void login() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = userService.login(email, password);
        if (user != null) {
            if (user.isAdmin()) {
                AdminPanel(user);
            } else {
                System.out.println("Добро пожаловать, " + user.getName() + "!");
                userMenu(user);
            }
        } else {
            System.out.println("Ошибка входа. Проверьте email и пароль.");
        }
    }

    private static void register() {
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите ваш email: ");
        String email = scanner.nextLine();
        System.out.print("Введите ваш пароль: ");
        String password = scanner.nextLine();

        userService.registered(name, email, password);
        System.out.println("Регистрация успешна! Теперь вы можете войти.");
    }

    private static void AdminPanel(User user) {
        if (!user.isAdmin()) {
            System.out.println("Доступ запрещён. Требуются права администратора.");
            return;
        }

        while (true) {
            System.out.println("\nАдминистративная панель:");
            System.out.println("1. Просмотр всех пользователей");
            System.out.println("2. Удаление пользователя");
            System.out.println("3. Блокировка пользователя");
            System.out.println("4. Просмотр транзакций пользователя");
            System.out.println("5. Просмотр всех транзакций");
            System.out.println("6. Разблокировка пользователя");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    adminService.viewAllUsers();
                    break;
                case 2:
                    System.out.print("Введите email пользователя для удаления: ");
                    String deleteEmail = scanner.nextLine();
                    adminService.deleteUser(deleteEmail);
                    break;
                case 3:
                    System.out.print("Введите email пользователя для блокировки: ");
                    String blockEmail = scanner.nextLine();
                    adminService.blockUser(blockEmail);
                    break;
                case 4:
                    System.out.print("Введите email пользователя для просмотра транзакций: ");
                    String transactionsEmail = scanner.nextLine();
                    adminService.viewUserTransactions(transactionsEmail);
                    break;
                case 5:
                    adminService.viewUserTransactions(user.getEmail());
                    break;
                case 6:
                    System.out.print("Введите email пользователя для разблокировки: ");
                    String unblockEmail = scanner.nextLine();
                    adminService.unblockUser(unblockEmail);
                    break;
                case 7:
                    System.out.println("Выход из административной панели.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nМеню пользователя:");
            System.out.println("1. Управление транзакциями");
            System.out.println("2. Управление целями");
            System.out.println("3. Управление бюджетом");
            System.out.println("4. Сформировать отчёт");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    System.out.println("Открыто меню управления транзакциями.");
                    break;
                case 2:
                    System.out.println("Открыто меню управления целями.");
                    break;
                case 3:
                    System.out.println("Открыто меню управления бюджетом.");
                    break;
                case 4:
                    System.out.println("Формирование отчёта.");
                    break;
                case 5:
                    System.out.println("Выход в главное меню.");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}
