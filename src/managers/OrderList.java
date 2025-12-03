package managers;

import model.Order;

import java.util.List;

public class OrderList {
    public static List<Order> getAllOrders = OrderProcessor.getOrders();
}