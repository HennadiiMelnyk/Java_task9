package com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy;

import com.test.melnyk.model.Product;
import com.test.melnyk.randominputhelper.RandomInputHelper;
import com.test.melnyk.strategy.randomstrategy.AnnotationRandomFieldsFill;
import com.test.melnyk.strategy.ProductFactory;

public class AnnotationRandomProductFactory extends ProductFactory {


    private RandomInputHelper randomInputHelper;

    public AnnotationRandomProductFactory(RandomInputHelper randomInputHelper) {
        this.randomInputHelper = randomInputHelper;
    }

    public Product createProduct() {
        Product product = new Product();
        return new AnnotationRandomFieldsFill().fullFill(product, randomInputHelper);
    }
}
