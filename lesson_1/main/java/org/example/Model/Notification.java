package org.example.Model;

import org.example.Service.AdminService;
import org.example.Service.BudgetService;
import org.example.Service.TransactionService;
import org.example.Service.UserService;

import java.util.HashMap;
import java.util.Map;

public class Notification {
    private final Map<String, String> userEmails = new HashMap<>();
    private final BudgetService budgetService;
    private final UserService userService;
    private final TransactionService transactionService;
    private final AdminService adminService;

    public Notification(BudgetService budgetService, UserService userService, TransactionService transactionService, AdminService adminService) {
        this.budgetService = budgetService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.adminService = adminService;
    }
}
