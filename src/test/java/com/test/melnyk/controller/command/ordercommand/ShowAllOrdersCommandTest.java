package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.model.Product;
import com.test.melnyk.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllOrdersCommandTest {

    private ShowAllOrdersCommand showAllOrdersCommand;

    @Mock
    OrderService orderService;

    @Before
    public void setUp(){
        showAllOrdersCommand = new ShowAllOrdersCommand(orderService);
    }

    @Test
    public void setShowAllOrdersCommandTest(){
        TreeMap<LocalDateTime, Map<Product,Integer>> testMap = new TreeMap<>();
        when(orderService.showAllOrders()).thenReturn(testMap);
        showAllOrdersCommand.execute();
        verify(orderService).showAllOrders();
    }

}