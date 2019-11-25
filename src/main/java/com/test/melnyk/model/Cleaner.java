package com.test.melnyk.model;

import java.util.Objects;

public class Cleaner extends HouseHoldChemical {
    private static final long serialVersionUID = 3931446177926314638L;
    private int percentageOfNonIonicSurfactants;


    public Cleaner(int id, String name, double price, int capacity, String chemicalSubstance, int percentageOfNonIonicSurfactants) {
        super(id, name, price, capacity, chemicalSubstance);
        this.percentageOfNonIonicSurfactants = percentageOfNonIonicSurfactants;
    }

    public int getPercentageOfNonIonicSurfactants() {
        return percentageOfNonIonicSurfactants;
    }

    public void setPercentageOfNonIonicSurfactants(int percentageOfNonIonicSurfactants) {
        this.percentageOfNonIonicSurfactants = percentageOfNonIonicSurfactants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cleaner cleaner = (Cleaner) o;
        return percentageOfNonIonicSurfactants == cleaner.percentageOfNonIonicSurfactants;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), percentageOfNonIonicSurfactants);
    }

    @Override
    public String toString() {
        return "Cleaner{" +
                "percentageOfNonIonicSurfactants=" + percentageOfNonIonicSurfactants +
                "} " + super.toString();
    }
}
