package model;

import java.math.BigDecimal;
import java.util.Map;

public class Order {
    private Person customer;
    private final Map<Product, Integer> finalCart;
    private final BigDecimal totalPrice;
    private final int orderId;

    private static int staticOrderId = 0;

    public Order(Person customer, Map<Product, Integer> finalCart) {
        this.customer = customer;
        this.finalCart = finalCart;
        this.totalPrice = calculateTotalPrice(finalCart);
        this.orderId = staticOrderId;
        staticOrderId++;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<Product, Integer> getFinalCart() {
        return finalCart;
    }

    private BigDecimal calculateTotalPrice(Map<Product, Integer> finalCart){
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Map.Entry<Product, Integer> entry : finalCart.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            totalPrice = totalPrice.add(product.price.multiply(BigDecimal.valueOf(quantity)));
        }
        return totalPrice;
    }
}