package com.test.melnyk.model;

import com.test.melnyk.annotation.ReflectionProduct;

import java.util.Objects;

public class Teapot extends ElectricalEngineering {

    private static final long serialVersionUID = 4975167177985255205L;
    @ReflectionProduct(name = "heatingElement")
    private String heatingElement;

    public Teapot(){}

    public Teapot(int id, String name, double price, String integratedCircuitChip, boolean hasPowerButton, String heatingElement) {
        super(id, name, price, integratedCircuitChip, hasPowerButton);
        this.heatingElement = heatingElement;
    }

    public String getHeatingElement() {
        return heatingElement;
    }

    public void setHeatingElement(String heatingElement) {
        this.heatingElement = heatingElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teapot teapot = (Teapot) o;
        return heatingElement.equals(teapot.heatingElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), heatingElement);
    }

    @Override
    public String toString() {
        return "Teapot{" +
                "heatingElement='" + heatingElement + '\'' +
                '}'+super.toString();
    }
}
