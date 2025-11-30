package app;

import managers.CartManager;
import managers.ProductManager;
import model.Cart;
import model.Computer;
import model.Electronic;
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

        //productManager.showAll();

//        ** Testy funkcjonalności productManagera **
//        productManager.deleteProduct("001");
//        productManager.updateProductName("ASD4325", "Turbo szuszarka Dyson");
//        productManager.updateProductStock("S0001", 5);
//        productManager.updateProductPrice("002", 4199.99);
//        productManager.showAll();

        CartManager cartManager = new CartManager();
        Cart testCart = new Cart();

        cartManager.addToCart(testCart, "001", 2);
        cartManager.addToCart(testCart, "002", 3);
        cartManager.showCart(testCart);
        cartManager.makeOrder(testCart);
        cartManager.showCart(testCart);
    }
}