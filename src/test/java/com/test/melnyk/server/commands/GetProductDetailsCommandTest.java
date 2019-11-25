package com.test.melnyk.server.commands;

import com.test.melnyk.service.ItemService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class GetProductDetailsCommandTest {

    private GetProductDetailsCommand getProductDetailsCommand;

    @Mock
    ItemService itemService;
    @Before
    public void setUp(){
        getProductDetailsCommand = new GetProductDetailsCommand(itemService);
    }
}