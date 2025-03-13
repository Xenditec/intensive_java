package org.example.Service;

import org.example.Model.Admin;
import org.example.Model.User;
import org.example.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
   protected static Map<String, User> users = new HashMap<>();

    public static Map<String, User> getUsersMap() {
        return users;
    }

    public static boolean registered(String name, String email, String password){
        if (users.containsKey(email))
        {
            System.out.println("Пользователь с таким email уже зарегестрирован");
            return false;
        }
        User user = new User(UUID.randomUUID().toString(), name, email, password);
        users.put(email, user);
        System.out.println("Регистрация прошла успешно!");
        return true;
    }

    public static User login(String email, String password){
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)){
            System.out.println("Вход выполнен. Добро пожаловать, " + user.getName());
            if (isUserAdmin(email)){
                user.setAdmin(true);
                System.out.println("Вход выполнен в роли администратора. Добро пожаловать, " + user.getName());
            }
            return user;
        }
        System.out.println("Неверный логин или пароль.");
        return null;
    }

    public static void editUser(String email, String newName, String newEmail, String newPassword){
        User user = users.get(email);
        if (user != null){
            user.setName(newName);
            user.setPassword(newPassword);
            user.setEmail(newEmail);
            users.remove(email);
            users.put(newEmail, user);
            System.out.println("Профиль успешно обновлен.");
        }
        else {System.out.println("Пользователь не найден.");}
    }



    private static boolean isUserAdmin (String email){
        User user = users.get(email);

        if (user != null){
            if (user.getEmail().equals(Admin.getAdminEmail()) && user.getPassword().equals(Admin.getAdminPassword())){
                return true;
            }
            return false;
        }
        System.out.println("Пользователь не найден.");
        return false;
    }

}
