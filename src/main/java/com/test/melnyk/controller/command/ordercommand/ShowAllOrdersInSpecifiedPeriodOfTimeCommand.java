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

public class ShowAllOrdersInSpecifiedPeriodOfTimeCommand implements Command {

    private OrderService orderService;
    private PrinterWrapper printerWrapper;
    private Scanner scanner;

    public ShowAllOrdersInSpecifiedPeriodOfTimeCommand(OrderService orderService, PrinterWrapper printerWrapper, Scanner scanner) {
        this.orderService = orderService;
        this.printerWrapper = printerWrapper;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        printerWrapper.printOut(Constants.INSERT_LOCAL_DATE_TIME_PATTERN);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.LOCAL_DATE_TIME_PATTERN);
        LocalDateTime dateTimeFrom = null;
        LocalDateTime dateTimeTo = null;
        try {
            dateTimeFrom = formatter.parse(scanner.nextLine()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            dateTimeTo = formatter.parse(scanner.nextLine()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return orderService.showAllOrdersInSpecifiedPeriodOfTime(dateTimeFrom, dateTimeTo);
    }
}
