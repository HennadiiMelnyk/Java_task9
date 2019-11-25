package com.test.melnyk.server.commands;

import com.test.melnyk.service.ItemService;

import java.io.PrintWriter;

public class GetQuantityOfProductsCommand implements Command {
    private ItemService itemService;

    public GetQuantityOfProductsCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(String request, PrintWriter printWriter) {
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println(System.lineSeparator());
        printWriter.println("count: " + itemService.getAllProducts().size());
        printWriter.flush();

    }
}
