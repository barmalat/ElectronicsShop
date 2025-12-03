package app;

import managers.ProductManager;
import model.Computer;
import model.Electronic;
import model.Smartphone;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        productManager.addProduct(new Electronic("1", "Suszarka Dyson", BigDecimal.valueOf(349.99), 2));
        productManager.addProduct(new Computer("2", "Lenovo thinkPad", new BigDecimal(2200), 8));
        productManager.addProduct(new Computer("2", "Lenovo thinkPad", new BigDecimal(2200), 2));
        productManager.addProduct(new Computer("3", "ASUS ROG", new BigDecimal(4200), 4));
        productManager.addProduct(new Smartphone("4", "Samsung", BigDecimal.valueOf(1249.99), 9));

        AppControl appControl = new AppControl();

        appControl.controlLoop();
    }
}