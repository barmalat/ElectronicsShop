package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cart = new HashMap<>();

    public Map<Product, Integer> getCart() {
        return cart;
    }
}