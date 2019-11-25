package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;

import java.util.Map;

public class GetLastFiveItemInCartCommand implements Command {

    private ItemService itemService;

    public GetLastFiveItemInCartCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public String execute() {
        Map<Product,Integer> lastFiveProducts = itemService.showLastFiveProductsInCart();
        return String.valueOf(lastFiveProducts);
    }
}
