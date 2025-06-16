package org.example;

public class User {
    private String id;
    private String name;
    private double rate; // Реализация user story про рейтинг пользователя
    private String driverLicense;// Реализация user story про водительское удостоверение

    public User(String id, String name, double rate, String driverLicense) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.driverLicense = driverLicense;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
    public String getDriverLicense() { return driverLicense; }
}

