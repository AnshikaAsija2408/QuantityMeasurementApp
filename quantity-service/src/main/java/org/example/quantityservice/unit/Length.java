package org.example.quantityservice.unit;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // Constructor
    public Length(double value, LengthUnit unit) {

        //validation for null unit
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        // Validation for invalid numbers
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid length value");
        }

        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit
    private double toBaseUnit() {
        return this.unit.convertToBaseUnit(this.value);
    }
    // Convert current object into target unit
    public Length convertTo(LengthUnit targetUnit) {

        // Validation
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1 -> convert current value to inches
        double baseValue =
                this.unit.convertToBaseUnit(this.value);

        // Step 2 -> convert inches to target unit
        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        // Step 3 -> return NEW Length object
        return new Length(convertedValue, targetUnit);
    }

    //equals() method
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        double firstLengthInBaseUnit =
                this.unit.convertToBaseUnit(this.value);

        double secondLengthInBaseUnit =
                other.unit.convertToBaseUnit(other.value);

        return Double.compare(firstLengthInBaseUnit, secondLengthInBaseUnit) == 0;
    }

    // toString() method
    @Override
    public String toString() {

        return value + " " + unit;
    }

    // Getter methods
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

     //UC6 -> ADDITION METHOD
    public Length add(Length otherLength) {

        return add(otherLength, this.unit);
    }
    public Length add(
        Length otherLength,
        LengthUnit targetUnit
     ) {

            if (otherLength == null) {
                throw new IllegalArgumentException("Length cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }


        // convert first length to base unit FEET
        double firstLengthInBaseUnit =
                this.unit.convertToBaseUnit(this.value);

        // convert second length to base unit FEET
        double secondLengthInBaseUnit =
                otherLength.unit.convertToBaseUnit(otherLength.value);

        // add both
        double totalInBaseUnit =
                firstLengthInBaseUnit + secondLengthInBaseUnit;

        // convert result to target unit
        double finalValue =
                targetUnit.convertFromBaseUnit(totalInBaseUnit);

        // return NEW object
        return new Length(finalValue, targetUnit);
    }
}
