package com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class ElectricalFactoryForConsoleStrategy extends ProductFactoryForConsoleStrategy {
    private PrinterWrapper printerWrapper;
    private Scanner scanner;

    public ElectricalFactoryForConsoleStrategy(Scanner scanner, PrinterWrapper printerWrapper) {
        super(scanner, printerWrapper);
        this.printerWrapper = printerWrapper;
        this.scanner = scanner;
    }

    public Product createProduct() {
        Product product = super.createProduct();
        printerWrapper.printOut("insert integrateCircuitChip");
        String integratedCircuitChip = scanner.next();
        printerWrapper.printOut("insert true/false your device a Power Button");
        Boolean hasPowerButton = scanner.nextBoolean();
        return new ElectricalEngineering(product.getId(), product.getName(), product.getPrice(),integratedCircuitChip, hasPowerButton);
    }
}
