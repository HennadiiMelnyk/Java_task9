package com.test.melnyk.strategy.consolestrategy.strategyConsoleFactory;

import com.test.melnyk.model.Product;
import com.test.melnyk.strategy.ProductFactory;
import com.test.melnyk.util.PrinterWrapper;

import java.util.Scanner;

public class ProductFactoryForConsoleStrategy extends ProductFactory {
    private Scanner scanner;
    private PrinterWrapper printerWrapper;

    public ProductFactoryForConsoleStrategy(Scanner scanner, PrinterWrapper printerWrapper) {
        this.scanner = scanner;
        this.printerWrapper = printerWrapper;
    }

    public Product createProduct() {
        Product product = new Product();
        printerWrapper.printOut("insert all product parameters");
        printerWrapper.printOut("insert product id");
        int productId = scanner.nextInt();
        product.setId(productId);
        printerWrapper.printOut("insert product name");
        String productName = scanner.next();
        product.setName(productName);
        printerWrapper.printOut("inset product price");
        int productPrice = scanner.nextInt();
        product.setPrice(productPrice);
        return product;
    }
}
