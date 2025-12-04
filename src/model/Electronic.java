package model;

import java.math.BigDecimal;

public class Electronic extends Product {
    public Electronic(String id, String name, BigDecimal price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public String toString() {
        return "Electronic: " +
                "name = " + name +
                ", id = " + id +
                ", price = " + price +
                ", stock = " + stock;
    }
}