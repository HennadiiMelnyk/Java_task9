package com.test.melnyk.strategy.consolestrategy;

import com.test.melnyk.annotation.ReflectionProduct;
import com.test.melnyk.model.Product;
import com.test.melnyk.util.PrinterWrapper;
import com.test.melnyk.consts.Constants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AnnotationConsoleFieldsFill {


    /**
     * getAll declaredFields of input product and added declared fields from parent class
     *
     * @param product -> get any product
     * @return
     */
    public Product fullFill(Product product, Scanner scanner, PrinterWrapper printerWrapper) {
        List<Field> fieldList = new ArrayList<>();
        Class productClass = product.getClass();
        while (Objects.nonNull(productClass)) {
            fieldList.addAll(Arrays.asList(productClass.getDeclaredFields()));
            productClass = productClass.getSuperclass();
        }
        setFields(product, fieldList, scanner, printerWrapper);
        return product;
    }

    private void setFields(Product product, List<Field> fields, Scanner scanner, PrinterWrapper printerWrapper) {
        printerWrapper.printOut(Constants.CHOOSE_LANGUAGE);
        String chooseLocale = scanner.next();
        Locale ru = new Locale(Constants.RUSSIAN_LANGUAGE, Constants.COUNTRY_CODE_RU);
        Locale en = new Locale(Constants.ENGLISH_LANGUAGE, Constants.COUNTRY_CODE_US);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.LOCALE, en);
        if (chooseLocale.equals(Constants.RUSSIAN_LANGUAGE)) {
            resourceBundle = ResourceBundle.getBundle(Constants.LOCALE, ru);
        }
        for (Field field : fields) {
            field.setAccessible(true);
            ReflectionProduct reflectionProducts = field.getAnnotation(ReflectionProduct.class);
            if (Objects.nonNull(reflectionProducts)) {
                Class<?> fieldsType = field.getType();
                printerWrapper.printOut(resourceBundle.getString(reflectionProducts.name()));
                Object parsed = fillTheFields(fieldsType, scanner);
                try {
                    field.set(product, parsed);
                } catch (IllegalAccessException e) {
                    System.out.println(Constants.TYPE_MISMATCH_ERROR);
                }
            }
            field.setAccessible(false);
        }
    }

    private Object fillTheFields(Class<?> fieldsType, Scanner scanner) {
        Object result = null;
        if (fieldsType.equals(int.class)) {
            result = scanner.nextInt();
        } else if (fieldsType.equals(String.class)) {
            result = scanner.next();
        } else if (fieldsType.equals(double.class)) {
            result = scanner.nextDouble();
        } else if (fieldsType.equals(boolean.class)) {
            result = scanner.nextBoolean();
        }
        return result;
    }

}
