package org.example;

public class Car {
    private String id;// ойди
    private String model;// марка машины
    private String carClass;// класс машины (эконом, комфорт, премиум, грузовые)
    private double pricePerMinute;// цена в час
    private boolean isAvaible; // доступность

    public Car(String id, String model, String carClass, double pricePerMinute) {
        this.id = id;
        this.model = model;
        this.carClass = carClass;
        this.pricePerMinute = pricePerMinute;
        this.isAvaible = true;
    }

    public String getId() { return id; }
    public String getModel() { return model; }
    public String getCarClass() { return carClass; }
    public boolean isAvailable() { return isAvaible; }
    public void setAvailable(boolean available) { isAvaible = available; }
    public double getPricePerMinute() { return pricePerMinute; }
    public void setPricePerMinute(double price) { this.pricePerMinute = price; }

}

