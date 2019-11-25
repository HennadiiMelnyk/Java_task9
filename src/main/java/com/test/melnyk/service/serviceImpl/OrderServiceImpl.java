package com.test.melnyk.service.serviceImpl;

import com.test.melnyk.dao.entitydao.OrderDao;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;
import com.test.melnyk.service.OrderService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.TreeMap;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private Cart cart;

    public OrderServiceImpl(OrderDao orderDao, Cart cart) {
        this.orderDao = orderDao;
        this.cart = cart;
    }

    @Override
    public boolean createOrder() {
        orderDao.create(cart.getCart());
        cart.clearCart();
        return true;
    }

    @Override
    public TreeMap<LocalDateTime, Map<Product, Integer>> showAllOrders() {
        TreeMap<LocalDateTime, Map<Product, Integer>> orderMap = orderDao.getOrders();
        return orderMap;
    }

    @Override
    public String buyAllProductsFromCart() {
        return cart.getTotalPrice();
    }

    @Override
    public String showAllOrdersInSpecifiedPeriodOfTime(LocalDateTime from, LocalDateTime to) {
        return orderDao.getOrdersByPeriodOfTime(from, to);
    }

    @Override
    public Map.Entry<LocalDateTime, Map<Product, Integer>> getOrdersByNearestDate(LocalDateTime dateTime) {
        if (orderDao.getOrders() == null) {
            return null;
        }
        Map.Entry<LocalDateTime, Map<Product, Integer>> left = orderDao.getOrders().lowerEntry(dateTime);
        Map.Entry<LocalDateTime, Map<Product, Integer>> right = orderDao.getOrders().ceilingEntry(dateTime);
        if (left == null) {
            return right;
        }
        if (right == null || ((right.getKey().toEpochSecond(ZoneOffset.UTC) - dateTime.toEpochSecond(ZoneOffset.UTC)) > (dateTime.toEpochSecond(ZoneOffset.UTC) - left.getKey().toEpochSecond(ZoneOffset.UTC)))) {
            return left;
        }
        return right;
    }
}
