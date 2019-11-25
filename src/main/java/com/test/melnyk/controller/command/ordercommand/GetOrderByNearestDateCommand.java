package com.test.melnyk.controller.command.ordercommand;

import com.test.melnyk.controller.Command;
import com.test.melnyk.service.OrderService;
import com.test.melnyk.util.PrinterWrapper;
import com.test.melnyk.consts.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class GetOrderByNearestDateCommand implements Command {

    private OrderService orderService;
    private PrinterWrapper printerWrapper;

    public GetOrderByNearestDateCommand(OrderService orderService, PrinterWrapper printerWrapper) {
        this.orderService = orderService;
        this.printerWrapper = printerWrapper;
    }

    @Override
    public String execute() {
        Scanner scanner = new Scanner(System.in);
        printerWrapper.printOut(Constants.INSERT_LOCAL_DATE_TIME_PATTERN);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.LOCAL_DATE_TIME_PATTERN);
        LocalDateTime dateTime = null;
        try {
            dateTime = formatter.parse(scanner.nextLine()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }

        return orderService.getOrdersByNearestDate(dateTime).toString();
    }
}
