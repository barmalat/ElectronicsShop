package managers;

import model.Address;
import model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderProcessor {
    private static List<Order> orders = new ArrayList<>();

    public static List<Order> getOrders() {
        return orders;
    }

    public void registerCustomer(int orderId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię:");
        orders.get(orderId).getCustomer().setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko:");
        orders.get(orderId).getCustomer().setLastName(scanner.nextLine());
        System.out.println("Teraz podaj adres wysyłki w formacie: kraj, miasto, ulica, numer domu, numer mieszkania:");
        String[] addressParts = scanner.nextLine().split("\\s*,\\s*");
        orders.get(orderId).getCustomer().setAddress(new Address(addressParts[0], addressParts[1], addressParts[2],
                addressParts[3], addressParts[4]));
    }

    private void showFinalCart(int orderId) {
        orders.get(orderId).getFinalCart().forEach((product, quantity) ->
                System.out.println(product.getName() + ", ilość: " +
                        quantity + ", łączna wartość: " +
                        product.getPrice().multiply(BigDecimal.valueOf(quantity))));
        System.out.println("Łączna wartość zamówienia: " + orders.get(orderId).getTotalPrice());
    }

    public void invoiceGenerate(int orderId) {
        Order order = orders.get(orderId);
        System.out.println("FAKTURA:");
        System.out.println("Zamówienie nr: " + order.getOrderId());
        System.out.println("Data: " + order.getOrderTime());
        System.out.println("Dane do wysyłki: " + order.getCustomer());
        showFinalCart(orderId);
    }
}