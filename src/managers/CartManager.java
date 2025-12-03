package managers;

import exceptions.NoSuchQuantityOfProductException;
import model.Cart;
import model.Computer;
import model.Electronic;
import model.Order;
import model.Person;
import model.Product;
import model.Smartphone;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartManager {
    ProductManager productManager = new ProductManager();

    public void addToCart(Cart cart, String productId, int quantity) {
        Product productInStock = productManager.getProductById(productId);
        Product productInCart = copyProductToConfig(productInStock);
        if (quantity > productInStock.getStock()) {
            throw new NoSuchQuantityOfProductException("Podana wartość przekracza stany magazynowe");
        } else {
            productInCart.config();
            if (cart.getShoppingCart().containsKey(productInCart)) {
                int oldQuantity = cart.getShoppingCart().get(productInCart);
                cart.getShoppingCart().replace(productInCart, oldQuantity + quantity);
            } else {
                cart.getShoppingCart().put(productInCart, quantity);
            }
            System.out.println("Dodano produkt do koszyka.");
            productInStock.setStock(productInStock.getStock() - quantity);
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

    private Product copyProductToConfig(Product orginalProduct) {
        if (orginalProduct instanceof Computer) {
            return new Computer(orginalProduct.getId(), orginalProduct.getName(), orginalProduct.getPrice(), orginalProduct.getStock());
        } else if (orginalProduct instanceof Smartphone) {
            return new Smartphone(orginalProduct.getId(), orginalProduct.getName(), orginalProduct.getPrice(), orginalProduct.getStock());
        }
        return new Electronic(orginalProduct.getId(), orginalProduct.getName(), orginalProduct.getPrice(), orginalProduct.getStock());
    }
}