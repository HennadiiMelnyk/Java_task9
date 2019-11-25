package com.test.melnyk.repository;

import com.test.melnyk.model.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class LastFiveOrderInCart {

    Map<Product, Integer> lastFiveOrderInCart;


    public LastFiveOrderInCart() {
        lastFiveOrderInCart = new LinkedHashMap<Product, Integer>(5, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > 5;
            }
        };
    }

    /**
     * add product to cart
     *
     * @param product
     */
    public void addToLimitCart(Product product) {
        if (lastFiveOrderInCart.keySet().contains(product)) {
            int count = lastFiveOrderInCart.get(product);
            lastFiveOrderInCart.put(product, ++count);
        } else {
            lastFiveOrderInCart.put(product, 1);
        }

    }

    /**
     * Show Limit Cart
     *
     * @return Order Map
     */
    public Map<Product, Integer> showLimitCart() {
        return lastFiveOrderInCart;
    }
}
