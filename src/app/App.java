package app;

import managers.CartManager;
import managers.OrderProcessor;
import managers.ProductManager;
import model.Cart;
import model.Computer;
import model.Electronic;
import model.Smartphone;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        productManager.addProduct(new Electronic("1", "Suszarka Dyson", BigDecimal.valueOf(349.99), 2));
        productManager.addProduct(new Computer("2", "Lenovo thinkPad", new BigDecimal(2200), 8));
        productManager.addProduct(new Computer("3", "Lenovo thinkPad", new BigDecimal(2200), 2));
        productManager.addProduct(new Computer("4", "ASUS ROG", new BigDecimal(4200), 4));
        productManager.addProduct(new Smartphone("5", "Samsung", BigDecimal.valueOf(1249.99), 9));

        //productManager.showAll();

//        ** Testy funkcjonalności productManagera **
//        productManager.deleteProduct("001");
//        productManager.updateProductName("ASD4325", "Turbo szuszarka Dyson");
//        productManager.updateProductStock("S0001", 5);
//        productManager.updateProductPrice("002", 4199.99);
//        productManager.showAll();

//        ** Testy koszyka **
//        CartManager cartManager = new CartManager();
//        Cart testCart = new Cart();
//
//        cartManager.addToCart(testCart, "001", 2);
//        cartManager.addToCart(testCart, "S0001", 3);
//        cartManager.showCart(testCart);
//        cartManager.makeOrder(testCart);
//        cartManager.showCart(testCart);
//
//        OrderProcessor orderProcessor = new OrderProcessor();
//
//        orderProcessor.invoiceGenerate(0);

        AppControl appControl = new AppControl();

        appControl.controlLoop();

    }
}