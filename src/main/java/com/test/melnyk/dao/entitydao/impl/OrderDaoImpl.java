package com.test.melnyk.dao.entitydao.impl;

import com.test.melnyk.dao.entitydao.OrderDao;
import com.test.melnyk.model.Product;
import com.test.melnyk.repository.Cart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrderDaoImpl implements OrderDao {

    private TreeMap<LocalDateTime, Map<Product, Integer>> orderMap;

    public TreeMap<LocalDateTime, Map<Product, Integer>> getOrderMap() {
        return orderMap;
    }

    public OrderDaoImpl(TreeMap<LocalDateTime, Map<Product, Integer>> orderMap, Cart cart) {
        this.orderMap = orderMap;
    }

    @Override
    public Map select(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Map map) {
        orderMap.put(LocalDateTime.now(), map);
        System.out.println(orderMap.toString());
        return true;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map update(Map map, int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TreeMap<LocalDateTime, Map<Product, Integer>> getOrders() {
        return orderMap;
    }

    @Override
    public String getOrdersByPeriodOfTime(LocalDateTime from, LocalDateTime to) {
        if(orderMap.isEmpty()){
            return "no orders yet";
        }
        return orderMap.subMap(from, to).toString();
    }


}
