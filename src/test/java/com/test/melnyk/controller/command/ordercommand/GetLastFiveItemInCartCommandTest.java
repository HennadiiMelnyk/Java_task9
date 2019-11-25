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
public class GetLastFiveItemInCartCommandTest {

    private GetLastFiveItemInCartCommand getLastFiveItemInCartCommand;

    @Mock
    ItemService itemService;
    @Before
    public void setUp(){
        getLastFiveItemInCartCommand = new GetLastFiveItemInCartCommand(itemService);
    }

    @Test
    public void setGetLastFiveItemInCartCommandTest(){
        Map<Product,Integer> testMap = new LinkedHashMap<>();
        when(itemService.showLastFiveProductsInCart()).thenReturn(testMap);
        getLastFiveItemInCartCommand.execute();
        verify(itemService).showLastFiveProductsInCart();
    }

}