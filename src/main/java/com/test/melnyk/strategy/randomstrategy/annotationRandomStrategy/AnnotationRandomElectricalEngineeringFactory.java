package com.test.melnyk.strategy.randomstrategy.annotationRandomStrategy;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;
import com.test.melnyk.randominputhelper.RandomInputHelper;
import com.test.melnyk.strategy.randomstrategy.AnnotationRandomFieldsFill;

public class AnnotationRandomElectricalEngineeringFactory extends AnnotationRandomProductFactory {
    private RandomInputHelper randomInputHelper;

    public AnnotationRandomElectricalEngineeringFactory(RandomInputHelper randomInputHelper) {
        super(randomInputHelper);
        this.randomInputHelper = randomInputHelper;
    }

    @Override
    public Product createProduct() {
        ElectricalEngineering electricalEngineering = new ElectricalEngineering();
        return new AnnotationRandomFieldsFill().fullFill(electricalEngineering, randomInputHelper);
    }
}
