package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Computer extends Product {
    private String processor = "CPU do konfiguracji własnej";
    private String ramMemory = "RAM do konfiguracji własnej";

    public Computer(String id, String name, BigDecimal price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public String toString() {
        return "Computer: " +
                "name = " + name +
                ", id = " + id +
                ", processor = " + processor +
                ", ramMemory = " + ramMemory +
                ", price = " + price +
                ", stock = " + stock;
    }

    public void config() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Konfiguracja komputera: " + name);
        System.out.println("Podaj procesor:");
        processor = scanner.nextLine();
        System.out.println("Podaj ilość pamięci RAM:");
        ramMemory = scanner.nextLine();
    }
}