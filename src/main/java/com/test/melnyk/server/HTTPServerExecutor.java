package com.test.melnyk.server;


import com.test.melnyk.server.commands.Command;
import com.test.melnyk.server.commands.CommandContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPServerExecutor implements Runnable {

    private Socket socket;
    private CommandContainer commandContainer;

    public HTTPServerExecutor(Socket socket, CommandContainer commandContainer) {
        this.socket = socket;
        this.commandContainer = commandContainer;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = bufferedReader.readLine();
            Command command = commandContainer.getCommandContainer(parseRequest(request));
            command.execute(request, new PrintWriter(socket.getOutputStream(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String parseRequest(String request) {
        Pattern p = Pattern.compile(".*?(?:[a-z][a-z]+).*?((?:[a-z][a-z]+))");
        Matcher matcher = p.matcher(request);
        String response = "";
        if (matcher.find()) {
            response = matcher.group(1);
        }
        return response;
    }
}