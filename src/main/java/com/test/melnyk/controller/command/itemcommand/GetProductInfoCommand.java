package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.service.ItemService;

public class GetProductInfoCommand  {

private ItemService itemService;

    public GetProductInfoCommand(ItemService itemService) {
        this.itemService = itemService;
    }


  /*  public String execute() {
        return itemService.getProductDetails();
    }*/
}
