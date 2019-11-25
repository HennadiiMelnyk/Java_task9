package com.test.melnyk.strategy.randomstrategy.strategyRandomFactory;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;
import com.test.melnyk.model.Teapot;

import java.util.Random;

public class RandomStrategyForTeapotItem extends RandomStrategyForElectricalEngineeringItem {


    public Product createProduct() {
        ElectricalEngineering electricalEngineering = (ElectricalEngineering) super.createProduct();
        Random random = new Random();
        String heatingElement = "tt" + random.nextInt(100);
        return new Teapot(electricalEngineering.getId(), electricalEngineering.getName(), electricalEngineering.getPrice(), electricalEngineering.getIntegratedCircuitChip(),
                electricalEngineering.isHasPowerButton(), heatingElement);
    }
}
