package org.example;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(convertedValue), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        double sumBase = thisBase + otherBase;
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(round(result), targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(round(thisBase), round(otherBase)) == 0;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(round(baseValue), unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}
