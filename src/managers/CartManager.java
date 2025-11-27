package managers;

import model.Cart;
import model.Product;

import java.math.BigDecimal;

public class CartManager {

    public void addToCart(Cart cart, Product product, int quantity) {
        if (quantity > product.getStock()) {
            System.err.println("Niestety, wskazana ilość przekracza stany magazynowe!");
        } else {
            if (cart.getShoppingCart().containsKey(product)) {
                int oldQuantity = cart.getShoppingCart().get(product);
                cart.getShoppingCart().replace(product, oldQuantity + quantity);
            } else {
                cart.getShoppingCart().put(product, quantity);
            }
            System.out.println("Dodano produkt do koszyka.");
            product.setStock(product.getStock() - quantity);
        }
    }

    public void showCart(Cart cart) {
        if (cart.getShoppingCart().isEmpty()) {
            System.err.println("Koszyk jest pusty.");
        } else {
            System.out.println("Zawartość koszyka:");
            cart.getShoppingCart().forEach((product, quantity) ->
                    System.out.println(product.getName() + ", ilość: " +
                    quantity + "łączna wartość: " +
                    product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        }
    }

    public void makeOrder(Cart cart) {
        if (cart.getShoppingCart().isEmpty()) {
            System.err.println("Koszyk jest pusty, nie można złożyć zamówienia.");
        } else {
            System.out.println("Złożono zamówienie.");
            cart.getShoppingCart().clear();
        }
    }
}