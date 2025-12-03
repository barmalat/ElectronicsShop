package threads;

import managers.OrderProcessor;

public class OrderProcessingThread implements Runnable {
    private final int orderId;

    public OrderProcessingThread(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        System.out.println("Przetwarzanie zamówienia nr " + orderId);
        try {
            Thread.sleep(2000);
            OrderProcessor orderProcessor = new OrderProcessor();
            orderProcessor.invoiceGenerate(orderId);
            System.out.println("Zamówienie zostało przetworzone");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}