package managers;

import exceptions.NoSuchOptionOfConfigException;
import exceptions.NoSuchQuantityOfProductException;
import model.Cart;
import model.Computer;
import model.Order;
import model.Person;
import model.Product;
import model.Smartphone;
import model.common.ConfigKey;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Klasa do zarządzania obiektami klasy Cart
 */

public class CartManager {
    ProductManager productManager = new ProductManager();

    public void addToCart(Cart cart, String productId, int quantity, String configuration) {
        Product productInStock = productManager.getProductById(productId);
        Product productToBuy = copyProductToConfig(productInStock);
        if (quantity > productInStock.getStock()) {
            throw new NoSuchQuantityOfProductException("Podana wartość przekracza stany magazynowe");
        } else {
            configureProduct(productToBuy, configuration);

            cart.getItems().merge(productToBuy, quantity, Integer::sum);
            System.out.println("Dodano produkt do koszyka.");
            productInStock.setStock(productInStock.getStock() - quantity);
        }
    }

    public void showCart(Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Koszyk jest pusty.");
        } else {
            System.out.println("Zawartość koszyka:");
            cart.getItems().forEach((product, quantity) -> {
                System.out.print(product.getName());
                if (product instanceof Computer computer) {
                    computer.getConfigInfo();
                } else if (product instanceof Smartphone smartphone) {
                    smartphone.getConfigInfo();
                }
                System.out.println(", ilość: " + quantity +
                        ", łączna wartość: " + product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            });
        }
    }

    public void makeOrder(Cart cart) {
        Order order = new Order(new Person(), new HashMap<>(cart.getItems()));
        OrderRepository.addToOrders(order);
        System.out.println("Złożono zamówienie. Twój numer zamówienia to: " + order.getOrderId());
        cart.getItems().clear();
    }

    private Product copyProductToConfig(Product orginalProduct) {
        if (orginalProduct instanceof Computer) {
            return new Computer(orginalProduct.getId(), orginalProduct.getName(), orginalProduct.getPrice(), orginalProduct.getStock());
        } else if (orginalProduct instanceof Smartphone) {
            return new Smartphone(orginalProduct.getId(), orginalProduct.getName(), orginalProduct.getPrice(), orginalProduct.getStock());
        }
        return orginalProduct;
    }

    private void configureProduct(Product product, String configuration) {
        List<String> configurationParts = List.of(configuration.split("\\s*,\\s*", 3));
        switch (product) {
            case Computer computer -> {
                var productConfig = computer.getConfig();
                if (configurationParts.size() == productConfig.size()) {
                    productConfig.replace(ConfigKey.CPU, configurationParts.getFirst());
                    productConfig.replace(ConfigKey.RAM, configurationParts.get(1));
                } else throw new NoSuchOptionOfConfigException("Podano błędny format konfiguracji");
            }
            case Smartphone smartphone -> {
                var productConfig = smartphone.getConfig();
                if (configurationParts.size() == productConfig.size()) {
                    productConfig.replace(ConfigKey.COLOR, configurationParts.getFirst());
                    productConfig.replace(ConfigKey.BATTERY, configurationParts.get(1));
                    productConfig.replace(ConfigKey.ACCESSORIES, configurationParts.get(2));
                } else throw new NoSuchOptionOfConfigException("Podano błędny format konfiguracji");
            }
            default -> {
            }
        }
    }
}