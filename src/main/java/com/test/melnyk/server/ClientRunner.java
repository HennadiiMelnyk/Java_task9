package com.test.melnyk.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                Socket clientSocket = new Socket("127.0.0.1", 3000);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("insert command");
                String request = scanner.nextLine();
                writer.write(request);
                writer.newLine();
                writer.flush();
                System.out.println(clientReader.readLine());
                clientReader.close();
                writer.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
