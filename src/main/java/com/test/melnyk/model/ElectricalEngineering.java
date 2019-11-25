package com.test.melnyk.model;

import com.test.melnyk.annotation.ReflectionProduct;

import java.util.Objects;

public class ElectricalEngineering extends Product {

    private static final long serialVersionUID = 7239830204054929818L;
    @ReflectionProduct(name = "integratedCircuitChip")
    private String integratedCircuitChip;
    @ReflectionProduct(name = "hasPowerButton")
    private boolean hasPowerButton;

    public ElectricalEngineering() {
    }

    public ElectricalEngineering(int id, String name, double price, String integratedCircuitChip, boolean hasPowerButton) {
        super(id, name, price);
        this.integratedCircuitChip = integratedCircuitChip;
        this.hasPowerButton = hasPowerButton;
    }

    public String getIntegratedCircuitChip() {
        return integratedCircuitChip;
    }

    public void setIntegratedCircuitChip(String integratedCircuitChip) {
        this.integratedCircuitChip = integratedCircuitChip;
    }

    public boolean isHasPowerButton() {
        return hasPowerButton;
    }

    public void setHasPowerButton(boolean hasPowerButton) {
        this.hasPowerButton = hasPowerButton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectricalEngineering that = (ElectricalEngineering) o;
        return hasPowerButton == that.hasPowerButton &&
                integratedCircuitChip.equals(that.integratedCircuitChip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), integratedCircuitChip, hasPowerButton);
    }

    @Override
    public String toString() {
        return "ElectricalEngineering{" +
                "integratedCircuitChip='" + integratedCircuitChip + '\'' +
                ", hasPowerButton=" + hasPowerButton +
                '}'+super.toString();
    }
}
