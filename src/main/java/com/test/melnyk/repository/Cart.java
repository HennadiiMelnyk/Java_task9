package com.test.melnyk.repository;

import com.test.melnyk.model.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart;

    public Cart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public Cart() {
        cart = new LinkedHashMap();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    /**
     * Adding product to storage
     *
     * @param product
     */
    public void addToCart(Product product) {
        if (cart.keySet().contains(product)) {
            int count = cart.get(product);
            cart.put(product, ++count);
        } else {
            cart.put(product, 1);
        }
    }

    /**
     * multiply price of all product in cart
     *
     * @return
     */
    public String getTotalPrice() {
        final BigDecimal[] result = {BigDecimal.valueOf(0)};
        cart.forEach((key, value) -> {
            BigDecimal currentTotalPrice = BigDecimal.valueOf(Double.parseDouble(String.valueOf(key.getPrice())))
                    .multiply(BigDecimal.valueOf(value));
            result[0] = result[0].add(currentTotalPrice);
        });
        return result[0].toString();
    }

    /**
     * Show Cart
     *
     * @return Map
     */
    public Map<Product, Integer> showCart() {
        return cart;
    }

    /**
     * clear Cart
     */
    public boolean clearCart() {
        if (!cart.isEmpty()) {
            cart = new LinkedHashMap<>();
            return true;
        }
        return false;
    }

}
