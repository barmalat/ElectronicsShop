package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Smartphone extends Product {
    private String color;
    private int batteryCapacity;
    private String accessories;

    public Smartphone(String id, String name, BigDecimal price, int quantity, String color, int batteryCapacity, String accessories) {
        super(id, name, price, quantity);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.accessories = accessories;
    }

    public Smartphone(String id, String name, BigDecimal price, int quantity) {
        super(id, name, price, quantity);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public void config() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Konfiguracja smartfona: " + name);
        System.out.println("Podaj kolor:");
        color = scanner.nextLine();
        System.out.println("Podaj pojemność baterii:");
        batteryCapacity = scanner.nextInt();
        System.out.println("Podaj dodatkowe akcesoria do smartfona:");
        accessories = scanner.nextLine();
    }
}