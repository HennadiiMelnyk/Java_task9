package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.service.OrderService;

public class CreateOrderCommand implements Command {
    private OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute() {
        return String.valueOf(orderService.createOrder());
    }
}
