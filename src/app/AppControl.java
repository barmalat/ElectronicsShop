package app;

import exceptions.NoSuchOptionException;
import managers.CartManager;
import managers.OrderProcessor;
import managers.ProductManager;
import model.Cart;
import model.Computer;
import model.Order;
import model.Product;
import model.Smartphone;
import model.common.AppOption;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppControl {
    Scanner scanner = new Scanner(System.in);
    ProductManager productManager = new ProductManager();
    CartManager cartManager = new CartManager();
    OrderProcessor orderProcessor = new OrderProcessor();

    void controlLoop() {
        AppOption option = null;
        Cart temp = new Cart();
        while (option != AppOption.EXIT) {
            System.out.println();
            printOptions();
            option = getOption();

            switch (option) {
                case EXIT -> System.out.println("Do zobaczenia!");
                case SHOW_ALL_PRODUCTS -> productManager.showAll();
                case ADD_TO_CART -> addToCartByCustomer(temp);
                case SHOW_CART -> cartManager.showCart(temp);
                case MAKE_ORDER -> finalizeOrder(temp);
            }
        }
    }

    private void addToCartByCustomer(Cart cart) {
        System.out.println("Podaj ID produktu który chcesz dodać do koszyka:");
        String idToCart = scanner.nextLine();
        System.out.println("Podaj ilość jaką chcesz dodać do koszyka");
        int quantityToCart = scanner.nextInt();
        try {
            String configuration = configIfNecessary(productManager, scanner, idToCart);
            cartManager.addToCart(cart, idToCart, quantityToCart, configuration);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String configIfNecessary(ProductManager productManager, Scanner scanner, String id) {
        Product product = productManager.getProductById(id);
        if (product instanceof Computer) {
            scanner.nextLine();
            System.out.println("Podaj konfiguracje komputera w formacie: CPU, RAM");
            return scanner.nextLine();
        } else if (product instanceof Smartphone) {
            scanner.nextLine();
            System.out.println("Podaj konfiguracje smartfona w formacie: kolor, bateria, akcesoria");
            return scanner.nextLine();
        } else
            return "niedotyczy";
    }


    private void finalizeOrder(Cart temp) {
        if (temp.getItems().isEmpty()) {
            System.out.println("Koszyk jest pusty, nie można złożyć zamówienia.");
        } else {
            cartManager.makeOrder(temp);
            System.out.println("Celem sfinalizowania zamówienia, musisz podać swoje dane.");
            orderProcessor.registerCustomer(Order.getStaticOrderId() - 1);
            orderProcessor.invoiceProcess(Order.getStaticOrderId() - 1);
//            orderProcessor.invoiceProcessAsync(Order.getStaticOrderId() - 1);
//            var test = orderProcessor.invoiceProcessAsync(Order.getStaticOrderId() - 1);
//            test.join();
        }
    }

    private AppOption getOption() {
        boolean optionOk = false;
        AppOption option = null;
        while (!optionOk) {
            try {
                option = AppOption.createFromInt(scanner.nextInt());
                scanner.nextLine();
                optionOk = true;
            } catch (NoSuchOptionException e) {
                System.err.println(e.getMessage() + ", podaj ponownie:");
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
                scanner.nextLine();
            }
        }
        return option;
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        for (AppOption option : AppOption.values()) {
            System.out.println(option.toString());
        }
    }
}