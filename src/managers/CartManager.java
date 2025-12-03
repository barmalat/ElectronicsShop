package managers;

import model.Cart;
import model.Order;
import model.Person;
import model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartManager {

    public void addToCart(Cart cart, String productId, int quantity) {
        Product product = ProductManager.findById(productId);
        if (quantity > product.getStock()) {
            System.err.println("Niestety, wskazana ilość przekracza stany magazynowe!");
        } else {
            product.config();
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
            System.out.println("Koszyk jest pusty.");
        } else {
            System.out.println("Zawartość koszyka:");
            cart.getShoppingCart().forEach((product, quantity) ->
                    System.out.println(product.getName() + ", ilość: " +
                            quantity + ", łączna wartość: " +
                            product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        }
    }

    public void makeOrder(Cart cart) {
        Map<Product, Integer> cartToOrder = new HashMap<>(cart.getShoppingCart());
        Order order = new Order(new Person(), cartToOrder);
        OrderProcessor.getOrders().add(order);
        System.out.println("Złożono zamówienie. Twój numer zamówienia to: " + order.getOrderId());
        cart.getShoppingCart().clear();
    }
}