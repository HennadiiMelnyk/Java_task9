package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.service.OrderService;

public class BuyAllProductsFromCartCommand implements Command {
    private OrderService orderService;

    public BuyAllProductsFromCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute() {
        return orderService.buyAllProductsFromCart();
    }
}
