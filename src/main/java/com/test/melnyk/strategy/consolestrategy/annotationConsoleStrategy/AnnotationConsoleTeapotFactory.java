package com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy;

import com.test.melnyk.model.Product;
import com.test.melnyk.model.Teapot;
import com.test.melnyk.strategy.consolestrategy.AnnotationConsoleFieldsFill;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class AnnotationConsoleTeapotFactory extends AnnotationConsoleElectricalEngineeringFactory {
    private Scanner scanner;
    private PrinterWrapper printerWrapper;

    public AnnotationConsoleTeapotFactory(Scanner scanner, PrinterWrapper printerWrapper) {
        super(scanner, printerWrapper);
        this.scanner = scanner;
        this.printerWrapper = printerWrapper;
    }

    @Override
    public Product createProduct() {
        Teapot teapot = new Teapot();
        return new AnnotationConsoleFieldsFill().fullFill(teapot, scanner, printerWrapper);
    }
}
