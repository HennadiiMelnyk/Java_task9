package com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;
import com.test.melnyk.model.Teapot;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class TeapotFactoryForConsoleStrategy extends ElectricalFactoryForConsoleStrategy {
    private PrinterWrapper printerWrapper;
    private Scanner scanner;

    public TeapotFactoryForConsoleStrategy(Scanner scanner, PrinterWrapper printerWrapper) {
        super(scanner, printerWrapper);
        this.printerWrapper = printerWrapper;
        this.scanner = scanner;
    }

    public Product createProduct() {
        ElectricalEngineering electricalEngineering = (ElectricalEngineering) super.createProduct();
        printerWrapper.printOut("insert heatingElement");
        String heatingElement = scanner.next();
        return new Teapot(electricalEngineering.getId(), electricalEngineering.getName(), electricalEngineering.getPrice(), electricalEngineering.getIntegratedCircuitChip(),
                electricalEngineering.isHasPowerButton(), heatingElement);
    }
}
