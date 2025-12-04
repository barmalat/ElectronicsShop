package managers;

import model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementacja klasy dającej dostęp do listy przetworzonych zamówień
 */

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    public static List<Order> getOrders() {
        return orders;
    }
}