package org.example.Model;

public class Target {
    private final String id;
    private String userId;
    private double targetAmount;
    private double savedAmount;
    private String description;

    public Target (String id, String userId, double targetAmount, double savedAmount, String description){
        this.id = id;
        this.userId = userId;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.description = description;
    }

    public String getId(){return id;};
    public double getTargetAmount() {return targetAmount;}
    public double getSavedAmount() {return savedAmount;}
    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
    public void setTargetAmount(double targetAmount) {this.targetAmount = targetAmount;}
    public void setSavedAmount(double savedAmount) {this.savedAmount = savedAmount;}
}
