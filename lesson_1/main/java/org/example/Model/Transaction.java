package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private String id;
    private String userId;
    private double amount;
    private String category;
    private String type;
    private LocalDate date;
    private String description;

    public Transaction(String userId, double amount, String category, String type, LocalDate date, String description) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    public String getId() {return id;}
    public String getUserId() {return userId;}
    public double getAmount() {return amount;}
    public String getCategory() {return category;}
    public String getType() {return type;}
    public LocalDate getDate() {return date;}
    public String getDescription() {return description;}

    public void setAmount(double amount) {this.amount = amount;}
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setType(String type) {this.type = type;}
}
