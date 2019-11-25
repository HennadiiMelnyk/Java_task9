package com.test.melnyk.server.commands;

import com.test.melnyk.service.ItemService;

import java.util.HashMap;
import java.util.Map;

/**
 * command for TCP and HTTP servers
 */
public class CommandContainer {

    private Map<String, Command> commandContainer = new HashMap<>();
    private ItemService itemService;

    public CommandContainer(ItemService itemService) {
        commandContainer.put("count", new GetQuantityOfProductsCommand(itemService));
        commandContainer.put("item", new GetProductDetailsCommand(itemService));

    }

    public Command getCommandContainer(String request) {
        return commandContainer.get(request);
    }
}
