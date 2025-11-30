package model;

import java.math.BigDecimal;
import java.util.Map;

public class Order {
    private Person customer;
    private Map<Product, Integer> finalCart;
    private BigDecimal totalPrice;

    public Order(Person customer, Map<Product, Integer> finalCart) {
        this.customer = customer;
        this.finalCart = finalCart;
        this.totalPrice = calculateTotalPrice(finalCart);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
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