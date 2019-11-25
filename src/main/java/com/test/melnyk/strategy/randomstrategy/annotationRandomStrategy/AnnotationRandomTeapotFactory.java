package com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy;

import com.test.melnyk.model.Product;
import com.test.melnyk.model.Teapot;
import com.test.melnyk.randominputhelper.RandomInputHelper;
import com.test.melnyk.strategy.randomstrategy.AnnotationRandomFieldsFill;

public class AnnotationRandomTeapotFactory extends AnnotationRandomElectricalEngineeringFactory {
    private RandomInputHelper randomInputHelper;

    public AnnotationRandomTeapotFactory(RandomInputHelper randomInputHelper) {
        super(randomInputHelper);
        this.randomInputHelper = randomInputHelper;
    }

    @Override
    public Product createProduct() {
        Teapot teapot = new Teapot();
        return new AnnotationRandomFieldsFill().fullFill(teapot, randomInputHelper);
    }
}
