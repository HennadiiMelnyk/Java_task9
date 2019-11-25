package com.test.melnyk.server;

import com.test.melnyk.model.Product;
import com.test.melnyk.service.ItemService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPServerExecutor implements Runnable {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private Socket socket;
    private ItemService itemService;


    public TCPServerExecutor(Socket socket, ItemService itemService) {
        this.socket = socket;
        this.itemService = itemService;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            printWriter = new PrintWriter(socket.getOutputStream());
            String command = bufferedReader.readLine();
            if (command == null) {
                System.out.println("commands not found");
            }
            printWriter.println(parsingCommand(command));
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String parsingCommand(String command) {
        if ("count".equals(command)) {
            String response = "Quantity of products: " + itemService.getSize();
            return response;
        } else if (command.startsWith("get item=")) {
            String number = command.replaceAll("get item=", "");
            int id = Integer.parseInt(number);
            Product product = itemService.getAllProducts().get(id);
            return product.getName() + "->" + product.getPrice();
        } else {
            return "wrong commands key";
        }
    }
}