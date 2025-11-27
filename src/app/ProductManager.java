package app;

import model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManager {
    private static Map<String, Product> productsMap = new HashMap<>();

    public static Map<String, Product> getProductsMap() {
        return productsMap;
    }

    public void addProduct(Product product) {
        if (!productsMap.containsKey(product.getId())) {
            productsMap.put(product.getId(), product);
            System.out.println("Dodano produkt: " + product.getName());
        } else {
            System.out.println("W naszym magazynie znajduje się już produkt o takim ID. Zwiększam stan magazynowy o podaną ilość.");
            int oldQuantity = productsMap.get(product.getId()).getQuantity();
            productsMap.get(product.getId()).setQuantity(oldQuantity + product.getQuantity());
        }
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

    private void updateProductsName(Product product) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nową nazwę:");
        String newName = scanner.nextLine();
        product.setName(newName);
    }

    private void updateProductsPrice(Product product){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nową cenę:");
        double newPrice = scanner.nextDouble();
        product.setPrice(new BigDecimal(newPrice));
    }

    private void updateProductsQuantity(Product product){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nową ilość:");
        int newQuantity = scanner.nextInt();
        product.setQuantity(newQuantity);
    }

    public void updateProduct(String id) {
        if (productsMap.containsKey(id)) {
            Product p = productsMap.get(id);
            System.out.println("Aktualizujesz dane o produkcie: " + p);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Co chcesz zaktualizować? \n1 - nazwę, \n2 - cenę, \n3 - ilość na stanie");
            String opt = scanner.nextLine();
            switch (opt) {
                case "1" -> updateProductsName(p);
                case "2" -> updateProductsPrice(p);
                case "3" -> updateProductsQuantity(p);
                default -> System.out.println("Wybrano złą opcję.");
            }
        } else System.out.println("Nie znaleziono produktu o podanym ID");
    }
}