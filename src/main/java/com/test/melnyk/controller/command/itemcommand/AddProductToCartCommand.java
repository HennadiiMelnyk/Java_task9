package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.service.ItemService;
import com.test.melnyk.util.PrinterWrapper;
import com.test.melnyk.consts.Constants;

import java.util.Scanner;

public class AddProductToCartCommand implements Command {

    private ItemService itemService;
    private PrinterWrapper printerWrapper;
    private Scanner scanner;

    public AddProductToCartCommand(ItemService itemService, PrinterWrapper printerWrapper, Scanner scanner) {
        this.itemService = itemService;
        this.printerWrapper = printerWrapper;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        printerWrapper.printOut(Constants.INSERT_ITEM_ID);
        int id = scanner.nextInt();
        if (!itemService.addProductToCart(id)) {
            return "error";
        }
        return "product was added";

    }
}
