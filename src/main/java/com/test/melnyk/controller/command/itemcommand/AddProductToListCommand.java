package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;
import com.test.melnyk.strategy.Strategy;
import com.test.melnyk.util.PrinterWrapper;


public class AddProductToListCommand implements Command {

    private ItemService itemService;
    private PrinterWrapper printerWrapper;
    private Strategy strategy;


    public AddProductToListCommand(ItemService itemService, PrinterWrapper printerWrapper, Strategy strategy) {
        this.itemService = itemService;
        this.printerWrapper = printerWrapper;
        this.strategy = strategy;
    }

    @Override
    public String execute() {
      Product product = strategy.doOperations();
        if (!itemService.addProductToList(product)) {
            return "error";
        }
        return "product was added";
    }
}
