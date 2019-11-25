package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.service.ItemService;

public class ExitCommand implements Command {

    private ItemService itemService;

    public ExitCommand(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public String execute() {
        String path = "container.ser";
        return String.valueOf(itemService.saveContainer(path));
    }
}
