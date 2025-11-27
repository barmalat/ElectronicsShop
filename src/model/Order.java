package model;

import java.math.BigDecimal;
import java.util.Map;

public class Order {
    private Person customer;
    private Cart cart = new Cart();
    private BigDecimal totalPrice = calculateTotalPrice(cart);

    private BigDecimal calculateTotalPrice(Cart cart){
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Map.Entry<Product, Integer> entry : cart.getShoppingCart().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            totalPrice = totalPrice.add(product.price.multiply(BigDecimal.valueOf(quantity)));
        }
        return totalPrice;
    }
}