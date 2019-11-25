package com.test.melnyk.server;

import com.test.melnyk.ApplicationContext;
import com.test.melnyk.server.commands.CommandContainer;
import com.test.melnyk.service.ItemService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    int port;
    ItemService itemService;
    private ServerSocket serverSocket;
    private ApplicationContext applicationContext;
    private CommandContainer commandContainer;

    public HTTPServer(int port, ItemService itemService) {
        this.port = port;
        this.itemService = itemService;
        commandContainer = new CommandContainer(itemService);
    }

    public void startHTTPServer() {
        try {
            serverSocket = new ServerSocket(port);
            runHTTPServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runHTTPServer() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new HTTPServerExecutor(socket, commandContainer)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopHTTPServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
