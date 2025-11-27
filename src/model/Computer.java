package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Computer extends Product {
    private String processor;
    private int ramMemory;

    public Computer(String id, String name, BigDecimal price, int quantity, String processor, int ramMemory) {
        super(id, name, price, quantity);
        this.processor = processor;
        this.ramMemory = ramMemory;
    }

    public Computer(String id, String name, BigDecimal price, int quantity) {
        super(id, name, price, quantity);
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRamMemory(int ramMemory) {
        this.ramMemory = ramMemory;
    }

    @Override
    public String toString() {
        return "Computer: " +
                "name = " + name +
                ", id = " + id +
                ", price = " + price +
                ", quantity = " + stock;
    }

    public void config() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Konfiguracja komputera: " + name);
        System.out.println("Podaj procesor:");
        processor = scanner.nextLine();
        System.out.println("Podaj ilość pamięci RAM:");
        ramMemory = scanner.nextInt();
    }
}