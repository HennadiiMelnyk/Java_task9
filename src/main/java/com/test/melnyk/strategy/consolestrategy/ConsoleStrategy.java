package com.test.melnyk.strategy.consolestrategy;

import com.test.melnyk.model.Product;
import com.test.melnyk.strategy.ProductFactory;
import com.test.melnyk.strategy.Strategy;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Map;
import java.util.Scanner;

public class ConsoleStrategy implements Strategy {
    private PrinterWrapper printerWrapper;
    private Scanner scanner;
    Map<String, ProductFactory> factoryMap;

    public ConsoleStrategy(PrinterWrapper printerWrapper, Scanner scanner, Map<String, ProductFactory> factoryMap) {
        this.printerWrapper = printerWrapper;
        this.scanner = scanner;
        this.factoryMap = factoryMap;
    }

    @Override
    public Product doOperations() {
        printerWrapper.printOut("which type of product you want to create");
        factoryMap.forEach((key, value) -> {
            System.out.println(key + "-->" + value.getClass().getSimpleName());
        });
        String choice = scanner.next();
        return factoryMap.get(choice).createProduct();
    }
}
