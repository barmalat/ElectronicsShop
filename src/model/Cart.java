package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cart = new HashMap<>();

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void addToCart(Product product, int quantity) {
        if (quantity > product.quantity) {
            System.err.println("Niestety, wskazana ilość przekracza stany magazynowe!");
        } else {
            if (cart.containsKey(product)) {
                int oldQuantity = cart.get(product);
                cart.replace(product, oldQuantity + quantity);
            } else {
                cart.put(product, quantity);
            }
            System.out.println("Dodano produkt do koszyka.");
            product.quantity -= quantity;
        }
    }

    public void showCart() {
        if (cart.isEmpty()) {
            System.err.println("Koszyk jest pusty.");
        } else {
            System.out.println("Zawartość koszyka:");
            cart.forEach((product, quantity) -> {
                System.out.println(product.name + ", ilość: " +
                        quantity + "łączna wartość: " +
                        product.price.multiply(BigDecimal.valueOf(quantity)));
            });
        }
    }

    public void makeOrder() {
        if (cart.isEmpty()) {
            System.err.println("Koszyk jest pusty, nie można złożyć zamówienia.");
        } else {
            System.out.println("Złożono zamówienie.");
            cart.clear();
        }
    }
}