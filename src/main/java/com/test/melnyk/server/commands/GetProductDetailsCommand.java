package com.test.melnyk.server.commands;

import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetProductDetailsCommand implements Command {
    private ItemService itemService;

    public GetProductDetailsCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void execute(String request, PrintWriter printWriter) {
        String [] arr = request.split("/");
        String res = arr[2];
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(res);
        String number = "";
        while (matcher.find()) {
            number = matcher.group();
        }
        Product product = itemService.getAllProducts().get(Integer.parseInt(number));
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html");
        printWriter.println(System.lineSeparator());
        printWriter.println("name: " + product.getName() + " price: " + product.getPrice());
        printWriter.flush();
    }
}
