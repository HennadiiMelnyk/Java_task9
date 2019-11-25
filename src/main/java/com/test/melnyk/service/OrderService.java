package com.test.melnyk.service;

import com.test.melnyk.model.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public interface OrderService {

    boolean createOrder();

    TreeMap<LocalDateTime, Map<Product, Integer>> showAllOrders();

    String buyAllProductsFromCart();

   String showAllOrdersInSpecifiedPeriodOfTime(LocalDateTime from, LocalDateTime to);

    Map.Entry<LocalDateTime, Map<Product, Integer>> getOrdersByNearestDate(LocalDateTime dateTime);


}
