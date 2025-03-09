package org.example.Service;

import org.example.Model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
   protected static Map<String, User> users = new HashMap<>();

   public static User getUser(String email){
       User user = users.get(email);
       return user;
   }
    public boolean registered(String name, String email, String password){
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

    public User login(String email, String password){
        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)){
            System.out.println("Вход выполнен. Добро пожаловать, " + user.getName());
            return user;
        }
        System.out.println("Неверный логин или пароль.");
        return null;
    }

    public void editUser(String email, String newName, String newEmail, String newPassword){
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

    public static boolean isAdmin(String email) {
        User user = users.get(email);
        return user != null && user.isAdmin(); // если пользователь существует и является администратором
    }

}
