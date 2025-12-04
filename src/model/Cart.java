package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Product, Integer> items = new HashMap<>();

    public Map<Product, Integer> getItems() {
        return items;
    }
}