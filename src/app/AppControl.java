package app;

import managers.CartManager;
import managers.OrderProcessor;
import managers.ProductManager;
import model.Cart;
import model.Order;

import java.util.Scanner;

public class AppControl {
    Scanner scanner = new Scanner(System.in);
    ProductManager productManager = new ProductManager();
    CartManager cartManager = new CartManager();
    OrderProcessor orderProcessor = new OrderProcessor();

    void controlLoop() {
        Option option = null;
        Cart temp = new Cart();
        while (option != Option.EXIT) {
            printOptions();
            option = Option.createFromInt(scanner.nextInt());
            scanner.nextLine();

            switch (option) {
                case EXIT -> System.out.println("Do zobaczenia!");
                case SHOW_ALL_PRODUCTS -> productManager.showAll();
                case ADD_TO_CART -> {
                    System.out.println("Podaj ID produktu który chcesz dodać do koszyka:");
                    String idToCart = scanner.nextLine();
                    System.out.println("Podaj ilość jaką chcesz dodać do koszyka");
                    int quantityToCart = scanner.nextInt();
                    cartManager.addToCart(temp, idToCart, quantityToCart);
                    cartManager.showCart(temp);
                }
                case SHOW_CART -> cartManager.showCart(temp);
                case MAKE_ORDER -> {
                    if (temp.getShoppingCart().isEmpty()) {
                        System.out.println("Koszyk jest pusty, nie można złożyć zamówienia.");
                        continue;
                    }
                    cartManager.makeOrder(temp);
                    System.out.println("Celem złożenia zamówienia, musisz podać swoje dane.");
                    orderProcessor.registerCustomer(Order.getStaticOrderId() - 1);
                    System.out.println("Generuje fakturę...");
                    orderProcessor.invoiceGenerate(Order.getStaticOrderId() - 1);
                }
                default -> System.out.println("Błędna opcja, wprowadź ponownie.");
            }
        }
    }

    private void printOptions() {
        System.out.println("Wybierz opcję: ");
        for (Option option : Option.values()) {
            System.out.println(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        SHOW_ALL_PRODUCTS(1, "Wyświetl dostępne produkty"),
        ADD_TO_CART(2, "Dodaj do koszyka"),
        SHOW_CART(3, "Wyświetl koszyk"),
        MAKE_ORDER(4, "Złóż zamówienie");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) {
            return Option.values()[option];
        }
    }
}