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
public class CreateOrderCommandTest {
    private CreateOrderCommand createOrderCommand;

    @Mock
    OrderService orderService;

    @Before
    public void setUp() {
        createOrderCommand = new CreateOrderCommand(orderService);
    }

    @Test
    public void setCreateOrderCommandTest() {
        when(orderService.createOrder()).thenReturn(true);
        createOrderCommand.execute();
        verify(orderService).createOrder();
    }

}