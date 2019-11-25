package com.test.melnyk.strategy.consolestrategy.annotationConsoleStrategy;

import com.test.melnyk.model.Product;
import com.test.melnyk.strategy.ProductFactory;
import com.test.melnyk.strategy.consolestrategy.AnnotationConsoleFieldsFill;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class AnnotationConsoleProductFactory extends ProductFactory {

    private Scanner scanner;
    private PrinterWrapper printerWrapper;

    public AnnotationConsoleProductFactory(Scanner scanner, PrinterWrapper printerWrapper) {
        this.scanner = scanner;
        this.printerWrapper = printerWrapper;
    }

    @Override
    public Product createProduct() {
        Product product = new Product();
        return new AnnotationConsoleFieldsFill().fullFill(product, scanner, printerWrapper);
    }
}
