package app;

import model.Computer;
import model.Electronic;
import model.Product;
import model.Smartphone;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        productManager.addProduct(new Electronic("ASD4325", "Suszarka Dyson", BigDecimal.valueOf(349.99), 2));
        productManager.addProduct(new Computer("001", "Lenovo thinkPad", new BigDecimal(2200), 8));
        productManager.addProduct(new Computer("001", "Lenovo thinkPad", new BigDecimal(2200), 2));
        productManager.addProduct(new Computer("002", "ASUS ROG", new BigDecimal(4200), 4));
        productManager.addProduct(new Smartphone("S0001", "Samsung", BigDecimal.valueOf(1249.99), 9));

        productManager.showAll();

        productManager.deleteProduct("001");
        productManager.updateProduct("002");

        productManager.showAll();
    }
}