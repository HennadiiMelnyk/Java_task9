package com.test.melnyk.controller.command.itemcommand;

import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ShowListProductCommandTest {

    private ShowListProductCommand showListProductCommand;

    @Mock
    ItemService itemService;

    @Before
    public void setUp(){
        showListProductCommand = new ShowListProductCommand(itemService);
    }


    @Test
    public void showListProductCommandTest(){
        List<Product> testList = new ArrayList<>();
        when(itemService.getAllProducts()).thenReturn(testList);
        showListProductCommand.execute();
        verify(itemService).getAllProducts();
    }
}