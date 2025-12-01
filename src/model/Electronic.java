package model;

import java.math.BigDecimal;

public class Electronic extends Product {
    public Electronic(String id, String name, BigDecimal price, int quantity) {
        super(id, name, price, quantity);
    }

    @Override
    public void config() {
        System.out.println("Brak możliwości konfiguracji.");
    }

    @Override
    public String toString() {
        return "Electronic: " +
                "name = " + name +
                ", id = " + id +
                ", price = " + price +
                ", quantity = " + stock;
    }
}