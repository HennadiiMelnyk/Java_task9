package com.test.melnyk.strategy.randomstrategy.strategyRandomFactory;

import com.test.melnyk.model.Product;
import com.test.melnyk.strategy.ProductFactory;

import java.util.Random;

public class ProductFactoryForRandomStrategy extends ProductFactory {


    public ProductFactoryForRandomStrategy() {
    }

    public Product createProduct(){
        Product product = new Product();
        Random random = new Random();
        product.setPrice(random.nextInt(999) + 1);
        product.setId(random.nextInt(199) + 1);
        product.setName("Product" + random.nextInt(99999) + 1);
        return product;
    }
}
