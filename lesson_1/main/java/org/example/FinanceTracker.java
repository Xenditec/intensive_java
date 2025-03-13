package org.example;

import org.example.Model.Report;
import org.example.Model.Target;
import org.example.Model.Transaction;
import org.example.Model.User;
import org.example.Service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static org.example.Service.BudgetService.removeBudget;

public class FinanceTracker {
    private static final Scanner scanner = new Scanner(System.in);


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

        User user = UserService.login(email, password);
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

        UserService.registered(name, email, password);
        System.out.println("Регистрация успешна! Теперь вы можете войти.");
    }

    private static void AdminPanel(User user) {
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
                    AdminService.viewAllUsers();
                    break;
                case 2:
                    System.out.print("Введите email пользователя для удаления: ");
                    String deleteEmail = scanner.nextLine();
                    AdminService.deleteUser(deleteEmail);
                    break;
                case 3:
                    System.out.print("Введите email пользователя для блокировки: ");
                    String blockEmail = scanner.nextLine();
                    AdminService.blockUser(blockEmail);
                    break;
                case 4:
                    System.out.print("Введите email пользователя для просмотра транзакций: ");
                    String transactionsEmail = scanner.nextLine();
                    AdminService.viewUserTransactions(transactionsEmail);
                    break;
                case 5:
                    AdminService.viewAllTransactions(UserService.getUsersMap());
                    break;
                case 6:
                    System.out.print("Введите email пользователя для разблокировки: ");
                    String unblockEmail = scanner.nextLine();
                    AdminService.unblockUser(unblockEmail);
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
                    panelTransactions(user);
                    break;
                case 2:
                    System.out.println("Открыто меню управления целями.");
                    panelTarget(user);
                    break;
                case 3:
                    System.out.println("Открыто меню управления бюджетом.");
                    panelBudget(user);
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

    private static void panelTransactions(User user) {
        System.out.println("1. Добавить транзакцию\n2. Посмотреть список транзакций\n3. Редактировать транзакцию\n4. Удалить транзакцию\n5. Выход");
        String choice = scanner.nextLine();
        switch(choice){
            case "1":{
                System.out.println("Введите сумму:");
                double ammount = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите категорию:");
                String category = scanner.nextLine();
                System.out.println("Введите тип: (income)/(expensive)");
                String type = scanner.nextLine();
                System.out.println("Введите описание:");
                String description = scanner.nextLine();
                LocalDate date = LocalDate.now();

                TransactionService.addTransactions(user.getId(), ammount, category, type, date, description);
                System.out.println("Транзакция успешно добавлена!");
                break;
            }
            case "2":{
                System.out.println("Ваши транзакции:");
                TransactionService.listTransactions(user.getId());
            }
            case "3":{
                System.out.println("Введите ID транзакции:");
                String transactionId = scanner.nextLine();
                System.out.println("Введите новую сумму:");
                double ammount = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите новую категорию:");
                String category = scanner.nextLine();
                System.out.println("Введите новый тип: (income)/(expensive)");
                String type = scanner.nextLine();
                System.out.println("Введите новое описание:");
                String description = scanner.nextLine();
                LocalDate date = LocalDate.now();

                TransactionService.editTransactions(user.getId(),transactionId,ammount,category,type,description);
                break;
            }
            case "4":{
                System.out.println("Введите ID транзакции которую хотите удалить:");
                String transactionId = scanner.nextLine();
                TransactionService.deleteTransactions(user.getId(),transactionId);
            }
            case "5":{
                System.out.println("Выход в главное меню...");
                return;
            }
            default:
                System.out.println("Некорректный ввод, попробуйте снова.");
        }
    }

    private static void panelTarget(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Добавить цель\n2. Посмотреть список целей\n3. Обновить накопленную сумму\n4. Удалить цель\n5. Выйти");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Введите сумму цели: ");
                    double targetAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Введите описание цели: ");
                    String description = scanner.nextLine();
                    TargetService.addTarget(user.getId(), targetAmount, description);
                    System.out.println("Цель успешно добавлена.");
                    break;
                case "2":
                    List<Target> targets = TargetService.getUserTargets(user.getId());
                    if (targets.isEmpty()) {
                        System.out.println("У вас нет сохраненных целей.");
                    } else {
                        for (Target target : targets) {
                            System.out.println(target.getId() + " | " + target.getDescription() + " | Цель: " + target.getTargetAmount() + " | Накоплено: " + target.getSavedAmount());
                        }
                    }
                    break;
                case "3":
                    System.out.print("Введите ID цели для обновления: ");
                    String targetId = scanner.nextLine();
                    System.out.print("Введите сумму пополнения: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    TargetService.updateSavedAmount(user.getId(), targetId, amount);
                    System.out.println("Сумма успешно обновлена.");
                    break;
                case "4":
                    System.out.print("Введите ID цели для удаления: ");
                    targetId = scanner.nextLine();
                    TargetService.removeTarget(user.getId(), targetId);
                    System.out.println("Цель удалена.");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static void panelBudget(User user) {
        while (true) {
            System.out.println("1. Установить бюджет\n2. Добавить расход\n3. Просмотреть оставшийся бюджет\n4. Изменить бюджетный лимит\n5. Удалить бюджет\n6. Выход");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Введите месячный лимит:");
                    double monthLimit = Double.parseDouble(scanner.nextLine());
                    BudgetService.addBudgetMonth(user.getId(), monthLimit);
                    break;
                case "2":
                    System.out.println("Введите сумму расхода:");
                    double expense = Double.parseDouble(scanner.nextLine());
                    BudgetService.addBudgetExpense(user.getId(), expense);
                    break;
                case "3":
                    double remaining = BudgetService.getRemainingBudget(user.getId());
                    if (remaining != -1) {
                        System.out.println("Оставшийся бюджет: " + remaining);
                    } else {
                        System.out.println("Бюджет не установлен.");
                    }
                    break;
                case "4":
                    System.out.println("Введите новый лимит:");
                    double newLimit = Double.parseDouble(scanner.nextLine());
                    BudgetService.editBudget(user.getId(), newLimit);
                    break;
                case "5":
                    removeBudget(user.getId());
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }


    private static void panelReport(User user) {
        System.out.println("1. Сгенерировать отчет за период");
        System.out.println("2. Посмотреть текущий баланс");
        System.out.println("3. Анализ расходов по категориям");
        System.out.println("4. Выйти в главное меню");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1": {
                System.out.println("Введите начальную дату (ГГГГ-ММ-ДД): ");
                LocalDate startDate = LocalDate.parse(scanner.nextLine());
                System.out.println("Введите конечную дату (ГГГГ-ММ-ДД): ");
                LocalDate endDate = LocalDate.parse(scanner.nextLine());
                Report report = ReportService.generateReport(user.getId(), startDate, endDate);
                System.out.println("Отчет сгенерирован:");
                System.out.println(report);
                break;
            }
            case "2": {
                double balance = ReportService.calculateCurrentBalance(user.getId());
                System.out.println("Ваш текущий баланс: " + balance);
                break;
            }
            case "3": {
                Map<String, Double> expenses = ReportService.analyzeExpensesByCategory(user.getId());
                System.out.println("Анализ расходов по категориям:");
                for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                break;
            }

            case "4":
                System.out.println("Возвращаемся в главное меню...");
                return;

            default:
                System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }
}
