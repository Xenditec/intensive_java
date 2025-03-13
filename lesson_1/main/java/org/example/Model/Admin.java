package org.example.Model;

import org.example.Service.TransactionService;
import org.example.Service.UserService;

public class Admin extends User {
    private static final String EMAIL = "admin@example.com";
    private static final String PASSWORD = "admin123";


    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password);
    }


    public static String getAdminEmail() {return EMAIL;}


    public static String getAdminPassword() {return PASSWORD;}
}