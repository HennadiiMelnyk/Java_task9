package com.test.melnyk.server;

import com.test.melnyk.service.ItemService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private ItemService itemService;
    private ServerSocket serverSocket;
    private int port;

    public TCPServer( int port, ItemService itemService) {
        this.port = port;
        this.itemService = itemService;
    }

    public void startTCPServer() {
        try {
            serverSocket = new ServerSocket(port);
            runTCPServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runTCPServer() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new TCPServerExecutor( socket, itemService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopTCPServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
