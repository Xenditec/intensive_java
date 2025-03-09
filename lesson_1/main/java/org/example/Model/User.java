package org.example.Model;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean isBlocked;
    private boolean isAdmin;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isBlocked = false;// считаем иззначально что пользователь не заблокирован.
        this.isAdmin = false;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public boolean getBlocked() {return isBlocked;}
    public boolean isAdmin() { return isAdmin; } // проверка на администратора

    public void setBlocked(boolean blocked) {this.isBlocked = blocked;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}



}
