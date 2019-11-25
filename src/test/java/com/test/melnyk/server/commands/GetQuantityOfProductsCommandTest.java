package com.test.melnyk.server.commands;

import com.test.melnyk.model.Product;
import com.test.melnyk.server.HTTPServer;
import com.test.melnyk.server.TCPServer;
import com.test.melnyk.server.serversfactory.ThreadFactoryImpl;
import com.test.melnyk.service.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetQuantityOfProductsCommandTest {
    @Mock
    ItemService itemService;
    private GetQuantityOfProductsCommand getQuantityOfProductsCommand;

    @Before
    public void setUp() {
        getQuantityOfProductsCommand = new GetQuantityOfProductsCommand(itemService);
    }

    @Test
    public void getQuantityOfProductsCommandTest() throws IOException {
        TCPServer tcpServer = new TCPServer(3000, itemService);
        ThreadFactoryImpl threadFactory = new ThreadFactoryImpl();
        HTTPServer httpServer = new HTTPServer(8888, itemService);
        threadFactory.runTCPServer(tcpServer);
        threadFactory.runHTTPServer(httpServer);
        List<Product> testList = new ArrayList<>();
        when(itemService.getAllProducts()).thenReturn(testList);
        String testRequest = "GET /shop/count HTTP/1.0";
        Socket socket = new Socket("127.0.0.1", 3000);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        getQuantityOfProductsCommand.execute(testRequest, printWriter);
        verify(itemService).getAllProducts();
    }
}