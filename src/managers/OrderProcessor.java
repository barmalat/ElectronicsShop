package managers;

import model.Address;
import model.Computer;
import model.Order;
import model.Smartphone;
import threads.OrderProcessingThread;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Klasa do zarządzania obiektami klasy Order
 */

public class OrderProcessor {

    public void registerCustomer(int orderId) {
        Order order = OrderRepository.getOrders().get(orderId);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię:");
        order.getCustomer().setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko:");
        order.getCustomer().setLastName(scanner.nextLine());
        setCustomerAddress(scanner, order);
    }

    public void invoiceGenerate(int orderId) {
        Order order = OrderRepository.getOrders().get(orderId);
        System.out.println("FAKTURA:");
        System.out.println("Zamówienie nr: " + order.getOrderId());
        System.out.println("Data: " + order.getOrderTime());
        System.out.println("Dane do wysyłki: " + order.getCustomer());
        showFinalCart(order);
    }

    public void invoiceProcess(int orderId) {
        System.out.println("Generuje fakturę...");
        Thread thread = new Thread(new OrderProcessingThread(orderId));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCustomerAddress(Scanner scanner, Order order) {
        boolean addressOk = false;
        while (!addressOk) {
            try {
                System.out.println("Teraz podaj adres wysyłki w formacie: kraj, miasto, ulica, numer domu, numer mieszkania:");
                String[] addressParts = scanner.nextLine().split("\\s*,\\s*");
                order.getCustomer().setAddress(new Address(addressParts[0], addressParts[1], addressParts[2],
                        addressParts[3], addressParts[4]));
                addressOk = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Podano błędny adres");
            }
        }
    }

    private void showFinalCart(Order order) {
        order.getFinalCart().forEach((product, quantity) -> {
            System.out.print(product.getName());
            if (product instanceof Computer computer) {
                System.out.print(" (konfiguracja: " + computer.getConfig().get(Computer.ConfigKey.CPU) +
                        ", " + computer.getConfig().get(Computer.ConfigKey.RAM) + ")");
            } else if (product instanceof Smartphone smartphone) {
                System.out.print(" (konfiguracja: " + smartphone.getConfig().get(Smartphone.ConfigKey.COLOR) +
                        ", " + smartphone.getConfig().get(Smartphone.ConfigKey.BATTERY) +
                        ", " + smartphone.getConfig().get(Smartphone.ConfigKey.ACCESSORIES) + ")");
            }
            System.out.println(", ilość: " + quantity +
                    ", łączna wartość: " + product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        });
        System.out.println("Łączna wartość zamówienia: " + order.getTotalPrice());
        if (!order.getTotalPrice().equals(order.getFinalTotalPrice())) {
            System.out.println("Do zapłaty po rabacie: " + order.getFinalTotalPrice());
        }
    }
}