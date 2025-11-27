package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> shoppingCart = new HashMap<>();

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }
}