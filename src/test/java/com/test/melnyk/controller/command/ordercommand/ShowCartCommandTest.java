package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowCartCommandTest {

    private ShowCartCommand showCartCommand;

    @Mock
    ItemService itemService;

    @Before
    public void setUp(){
        showCartCommand = new ShowCartCommand(itemService);
    }

    @Test
    public void setShowCartCommandTest(){
        Map<Product,Integer> testMap = new LinkedHashMap<>();
        when(itemService.showCart()).thenReturn(testMap);
        showCartCommand.execute();
        verify(itemService).showCart();
    }

}