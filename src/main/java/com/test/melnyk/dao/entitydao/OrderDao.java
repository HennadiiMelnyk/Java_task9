package com.test.melnyk.dao.entitydao;

import com.test.melnyk.dao.CrudDao;
import com.test.melnyk.model.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public interface OrderDao extends CrudDao<Map> {

    TreeMap<LocalDateTime, Map<Product, Integer>> getOrders();

    String getOrdersByPeriodOfTime(LocalDateTime from, LocalDateTime to);




}
