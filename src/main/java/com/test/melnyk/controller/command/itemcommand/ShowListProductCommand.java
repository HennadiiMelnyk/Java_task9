package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;

import java.util.List;

public class ShowListProductCommand implements Command {
    private ItemService itemService;

    public ShowListProductCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public String execute() {
        List<Product> productList = itemService.getAllProducts();
        return productList.toString();
    }
}
