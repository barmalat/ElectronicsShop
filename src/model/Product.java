package model;

import java.math.BigDecimal;

public abstract class Product {
    protected String id;
    protected String name;
    protected BigDecimal price;
    protected int stock;

    public Product(String id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = quantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract void config();

    @Override
    public String toString() {
        return "Product: " +
                "name = " + name +
                ", id = " + id +
                ", price = " + price +
                ", quantity = " + stock;
    }
}