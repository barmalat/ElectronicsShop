package app;

import exceptions.NoSuchOptionException;
import managers.CartManager;
import managers.OrderProcessor;
import managers.ProductManager;
import model.Cart;
import model.Order;
import threads.OrderProcessingThread;

import java.util.InputMismatchException;
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
            System.out.println();
            printOptions();
            option = getOption();

            switch (option) {
                case EXIT -> System.out.println("Do zobaczenia!");
                case SHOW_ALL_PRODUCTS -> productManager.showAll();
                case ADD_TO_CART -> {
                    System.out.println("Podaj ID produktu który chcesz dodać do koszyka:");
                    String idToCart = scanner.nextLine();
                    System.out.println("Podaj ilość jaką chcesz dodać do koszyka");
                    int quantityToCart = scanner.nextInt();
                    try {
                        cartManager.addToCart(temp, idToCart, quantityToCart);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
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
                    orderProcessor.invoiceProcess(Order.getStaticOrderId() - 1);
                }
            }
        }
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(scanner.nextInt());
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

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }
    }
}