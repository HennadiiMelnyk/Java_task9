package com.test.melnyk.model;

import java.util.Objects;

public class HouseHoldChemical extends Product {

    private static final long serialVersionUID = 3336924800344895296L;
    private int capacity;
    private String chemicalSubstance;

    public HouseHoldChemical(){}


    public HouseHoldChemical(int id, String name, double price, int capacity, String chemicalSubstance) {
        super(id, name, price);
        this.capacity = capacity;
        this.chemicalSubstance = chemicalSubstance;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getChemicalSubstance() {
        return chemicalSubstance;
    }

    public void setChemicalSubstance(String chemicalSubstance) {
        this.chemicalSubstance = chemicalSubstance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HouseHoldChemical that = (HouseHoldChemical) o;
        return capacity == that.capacity &&
                chemicalSubstance.equals(that.chemicalSubstance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity, chemicalSubstance);
    }

    @Override
    public String toString() {
        return "HouseHoldChemical{" +
                "capacity=" + capacity +
                ", chemicalSubstance='" + chemicalSubstance + '\'' +
                '}'+ super.toString();
    }
}
