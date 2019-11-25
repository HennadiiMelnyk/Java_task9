package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.model.Product;
import com.test.melnyk.service.OrderService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class ShowAllOrdersCommand implements Command {

    private OrderService orderService;

    public ShowAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute() {
        TreeMap<LocalDateTime, Map<Product, Integer>> orderMap = orderService.showAllOrders();
        return String.valueOf(orderMap);
    }
}
