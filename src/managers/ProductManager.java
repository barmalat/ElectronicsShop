package managers;

import Exceptions.NoSuchProductException;
import model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductManager {
    private static final Map<String, Product> productsMap = new HashMap<>();

    public void addProduct(Product product) {
        if (!productsMap.containsKey(product.getId())) {
            productsMap.put(product.getId(), product);
            System.out.println("Dodano produkt: " + product.getName());
        } else {
            System.out.println("W naszym magazynie znajduje się już produkt o takim ID. Zwiększam stan magazynowy o podaną ilość.");
            int oldStock = productsMap.get(product.getId()).getStock();
            productsMap.get(product.getId()).setStock(oldStock + product.getStock());
        }
    }

    public Product getProductById(String id) {
        if (!productsMap.containsKey(id)) {
            throw new NoSuchProductException("Nie znaleziono produktu o takim ID");
        }
        return productsMap.get(id);
    }

    public void deleteProduct(String id) {
        if (productsMap.containsKey(id)) {
            System.out.println("Usunięto produkt: " + productsMap.get(id).getName());
            productsMap.remove(id);
        } else System.out.println("Nie znaleziono produktu o podanym ID");
    }

    public void showAll() {
        System.out.println("Wszystkie produkty:");
        productsMap.values()
                .forEach(System.out::println);
    }

    public void updateProductName(String productId, String newName) {
        Product product = productsMap.get(productId);
        product.setName(newName);
        System.out.println("Zaktualizowane nazwę produktu o ID: " + product.getId());
    }

    public void updateProductPrice(String productId, double newPrice) {
        Product product = productsMap.get(productId);
        product.setPrice(BigDecimal.valueOf(newPrice));
        System.out.println("Zaktualizowano cenę produktu o ID: " + product.getId());
    }

    public void updateProductStock(String productId, int newStock) {
        Product product = productsMap.get(productId);
        product.setStock(newStock);
        System.out.println("Zaktualizowano stan magazynowy produktu o ID: " + product.getId());
    }
}