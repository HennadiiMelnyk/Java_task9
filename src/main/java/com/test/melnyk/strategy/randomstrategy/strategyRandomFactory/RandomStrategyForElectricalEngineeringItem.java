package com.test.melnyk.strategy.randomstrategy.strategyRandomFactory;

import com.test.melnyk.model.ElectricalEngineering;
import com.test.melnyk.model.Product;

import java.util.Random;

public class RandomStrategyForElectricalEngineeringItem extends ProductFactoryForRandomStrategy {



    public Product createProduct() {
        Product product = super.createProduct();
        Random random = new Random();
        product.setPrice(random.nextInt(999) + 1);
        String intergratedCircuitChip = "dd " + random.nextInt(100);
        boolean hasPowerButton = random.nextBoolean();

        return new ElectricalEngineering(product.getId(), product.getName(), product.getPrice(), intergratedCircuitChip, hasPowerButton);
    }
}
