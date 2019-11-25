package com.test.melnyk.dao.impl;

import com.test.melnyk.dao.entitydao.OrderDao;
import com.test.melnyk.dao.entitydao.impl.OrderDaoImpl;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;


public class OrderDaoImplTest {
    private OrderDao orderDao;
    private Cart cart;
    Product product;
    private TreeMap<LocalDateTime, Map<Product, Integer>> orderMap;

    @Before
    public void setUp() {
        cart = new Cart();
        orderMap = new TreeMap<>();
        orderDao = new OrderDaoImpl(orderMap, cart);
        product = new Product(1, "b", 3);
        cart.addToCart(product);
    }

    @Test
    public void shouldCreateOrder() {
        orderDao.create(cart.getCart());
        int expectedSize=1;
        Assert.assertEquals(expectedSize,orderMap.size());
    }


}