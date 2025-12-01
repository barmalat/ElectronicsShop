package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Smartphone extends Product {
    private String color = "Kolor do konfiguracji własnej";
    private String batteryCapacity = "Bateria do konfiguracji własnej";
    private String accessories = "Akcesoria do konfiguracji własnej";

    public Smartphone(String id, String name, BigDecimal price, int quantity) {
        super(id, name, price, quantity);
    }

    @Override
    public String toString() {
        return "Smartphone: " +
                "name = " + name +
                ", id = " + id +
                ", color = " + color +
                ", batteryCapacity = " + batteryCapacity +
                ", accessories = " + accessories +
                ", price = " + price +
                ", stock = " + stock;
    }

    public void config() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Konfiguracja smartfona: " + name);
        System.out.println("Podaj kolor:");
        color = scanner.nextLine();
        System.out.println("Podaj pojemność baterii:");
        batteryCapacity = scanner.nextLine();
        System.out.println("Podaj dodatkowe akcesoria do smartfona:");
        accessories = scanner.nextLine();
    }
}