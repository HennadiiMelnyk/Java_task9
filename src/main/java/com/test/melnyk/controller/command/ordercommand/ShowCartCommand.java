package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;

import java.util.Map;

public class ShowCartCommand implements Command {

    private ItemService itemService;

    public ShowCartCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public String execute() {
        Map<Product, Integer> cartList = itemService.showCart();
        return String.valueOf(cartList);
    }
}
