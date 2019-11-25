package com.test.melnyk.randominputhelper.impl;

import com.test.melnyk.randominputhelper.RandomInputHelper;

import java.util.Random;

public class RandomInputHelperImpl implements RandomInputHelper {

    private static final String TEMPLATE = "Name:";
    private Random rand = new Random();

    @Override
    public int getRandomInt() {
        return (rand.nextInt(999) + 1);
    }

    @Override
    public double getRandomDouble() {
        return rand.nextDouble();
    }

    @Override
    public String getRandomString() {
        return TEMPLATE + (rand.nextInt(999) + 1);
    }

    @Override
    public boolean getRandomBoolean() {
        return rand.nextBoolean();
    }
}
