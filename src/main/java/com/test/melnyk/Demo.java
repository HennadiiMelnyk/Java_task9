package com.test.melnyk;

import com.test.melnyk.controller.Command;
import com.test.melnyk.server.HTTPServer;
import com.test.melnyk.server.TCPServer;
import com.test.melnyk.server.serversfactory.ThreadFactoryImpl;
import com.test.melnyk.service.ItemService;
import com.test.melnyk.strategy.Strategy;
import com.test.melnyk.consts.Constants;

import java.util.Map;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ApplicationContext applicationContext = new ApplicationContext();
        ItemService itemService = applicationContext.getItemService();
        TCPServer tcpServer = new TCPServer(3000, itemService);
        ThreadFactoryImpl threadFactory = new ThreadFactoryImpl();
        HTTPServer httpServer = new HTTPServer(8888, itemService);
        threadFactory.runTCPServer(tcpServer);
        threadFactory.runHTTPServer(httpServer);

        System.out.println("Choose strategy");
        Map<String, Strategy> strategyMap = applicationContext.getStrategyMap();

        strategyMap.forEach((key, value) -> {
            System.out.println(key + "--->" + value.getClass().getSimpleName());
        });
        String chooseStrategy = scanner.nextLine();
        applicationContext.setStrategy(strategyMap.get(chooseStrategy));
        Map<String, Command> commands = applicationContext.getCommandContainer();
        while (true) {
            System.out.println(Constants.SHOW_MENU);
            commands.forEach((k, v) -> {
                System.out.println(k + " " + v.getClass().getSimpleName());
            });
            String chooseCommand = scanner.nextLine();
            if (chooseCommand.equals("stop")) {
                commands.get(chooseCommand).execute();
                break;
            }
            if (chooseCommand.matches("([1-9]|([1][0-3]))")) {
                String out = commands.get(chooseCommand).execute();
                System.out.println(out);
            }else{
                System.out.println("invalid command key");
            }
        }
    }
}











