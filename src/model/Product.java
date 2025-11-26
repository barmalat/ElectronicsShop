package model;

import java.math.BigDecimal;

public class Product {
    protected String id;
    protected String name;
    protected BigDecimal price;
    protected int quantity;

    public Product(String id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}