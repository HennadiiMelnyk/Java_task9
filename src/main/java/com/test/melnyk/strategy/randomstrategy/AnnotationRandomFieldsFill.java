package com.test.melnyk.strategy.randomstrategy;

import com.test.melnyk.annotation.ReflectionProduct;
import com.test.melnyk.model.Product;
import com.test.melnyk.randominputhelper.RandomInputHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnnotationRandomFieldsFill {
    private RandomInputHelper randomInputHelper;

    /**
     * getAll declaredFields of input product and added declared fields from parent class
     *
     * @param product           -> get any product
     * @param randomInputHelper
     * @return
     */
    public Product fullFill(Product product, RandomInputHelper randomInputHelper) {
        this.randomInputHelper = randomInputHelper;
        List<Field> fieldList = new ArrayList<>();
        Class productClass = product.getClass();
        while (productClass != null) {
            fieldList.addAll(Arrays.asList(productClass.getDeclaredFields()));
            productClass = productClass.getSuperclass();
        }
        setFields(product, fieldList);
        return product;
    }

    /**
     * matching fields with data types
     *
     * @param fieldsType
     * @return random for specific date type
     */
    private Object fillTheFields(Class<?> fieldsType) {
        Object result = null;
        if (fieldsType.equals(int.class)) {
            result = randomInputHelper.getRandomInt();
        } else if (fieldsType.equals(String.class)) {
            result = randomInputHelper.getRandomString();
        } else if (fieldsType.equals(double.class)) {
            result = randomInputHelper.getRandomDouble();
        } else if (fieldsType.equals(boolean.class)) {
            result = randomInputHelper.getRandomBoolean();
        }
        return result;
    }

    /**
     * change access level to fields and filter only annotated fields
     *
     * @param product
     * @param fields
     */
    private void setFields(Product product, List<Field> fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            ReflectionProduct reflectionProducts = field.getAnnotation(ReflectionProduct.class);
            if (Objects.nonNull(reflectionProducts)) {
                Class<?> fieldsType = field.getType();
                Object parsed = fillTheFields(fieldsType);
                try {
                    field.set(product, parsed);
                } catch (IllegalAccessException e) {
                    System.out.println("type were not match");
                }
            }
            field.setAccessible(false);
        }
    }
}
