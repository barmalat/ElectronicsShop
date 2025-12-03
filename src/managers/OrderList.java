package managers;

import model.Order;

import java.util.List;

/**
 * Implementacja klasy dającej dostęp do listy przetworzonych zamówień
 */

public class OrderList {
    public static List<Order> getAllOrders = OrderProcessor.getOrders();
}