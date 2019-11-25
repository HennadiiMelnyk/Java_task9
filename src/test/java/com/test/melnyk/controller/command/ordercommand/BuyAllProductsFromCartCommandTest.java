package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class BuyAllProductsFromCartCommandTest {

    private BuyAllProductsFromCartCommand buyAllProductsFromCartCommand;

    @Mock
    OrderService orderService;

    @Before
    public void setUp(){
        buyAllProductsFromCartCommand = new BuyAllProductsFromCartCommand(orderService);
    }

    @Test
    public void setBuyAllProductsFromCartCommandTest(){
        when(orderService.buyAllProductsFromCart()).thenReturn("success");
        buyAllProductsFromCartCommand.execute();
        verify(orderService).buyAllProductsFromCart();
    }

}