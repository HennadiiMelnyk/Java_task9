package com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;
import com.test.melnyk.strategy.consolestrategy.AnnotationConsoleFieldsFill;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class AnnotationConsoleElectricalEngineeringFactory extends AnnotationConsoleProductFactory {

    private Scanner scanner;
    private PrinterWrapper printerWrapper;

    public AnnotationConsoleElectricalEngineeringFactory(Scanner scanner, PrinterWrapper printerWrapper) {
        super(scanner, printerWrapper);
        this.scanner = scanner;
        this.printerWrapper = printerWrapper;
    }

    @Override
    public Product createProduct() {
        ElectricalEngineering electricalEngineering = new ElectricalEngineering();
        return new AnnotationConsoleFieldsFill().fullFill(electricalEngineering, scanner, printerWrapper);
    }
}
