package managers;

import model.Address;
import model.Computer;
import model.Order;
import model.Smartphone;
import threads.OrderProcessingThread;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

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
        System.out.printf("FAKTURA:%nZamówienie nr: %s%nData: %s%nDane do wysyłki:%s%n",
                order.getOrderId(), order.getOrderTime(), order.getCustomer());
        showOrder(order);
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

    public CompletableFuture<Void> invoiceProcessAsync(int orderId) {
        System.out.println("Generuje fakturę... (asynchronicznie)");

        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                invoiceGenerate(orderId);
                System.out.println("Zamówienie zostało przetworzone (asynchronicznie)");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
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

    private void showOrder(Order order) {
        order.getFinalCart().forEach((product, quantity) -> {
            System.out.print(product.getName());
            if (product instanceof Computer computer) {
                computer.getConfigInfo();
            } else if (product instanceof Smartphone smartphone) {
                smartphone.getConfigInfo();
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