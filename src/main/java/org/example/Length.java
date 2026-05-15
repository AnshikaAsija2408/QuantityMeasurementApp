package org.example;

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

    // Convert to base unit (inches)
    private double toInches() {
        return this.value * unit.getConversionFactor();
    }

    // Convert current object into target unit
    public Length convertTo(LengthUnit targetUnit) {

        // Validation
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1 -> convert current value to inches
        double inchesValue = this.toInches();

        // Step 2 -> convert inches to target unit
        double convertedValue =
                inchesValue / targetUnit.getConversionFactor();

        // Step 3 -> return NEW Length object
        return new Length(convertedValue, targetUnit);
    }

    //equals() method
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Double.compare(this.toInches(), other.toInches()) == 0;
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

         //validation
//        if (otherLength == null) {
//            throw new IllegalArgumentException("Length cannot be null");
//        }
//
//         //convert both lengths to inches
//        double firstLengthInInches = this.toInches();
//        double secondLengthInInches = otherLength.toInches();
//
//         //add both
//        double totalInInches =
//                firstLengthInInches + secondLengthInInches;
//
//        // convert result back to FIRST operand unit
//        double finalValue =
//                totalInInches / this.unit.getConversionFactor();
//
//        // return NEW object
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

            // convert both into inches
            double thisInches =
                    this.value * this.unit.getConversionFactor();

            double otherInches =
                    otherLength.value *
                            otherLength.unit.getConversionFactor();

            // add
            double totalInches = thisInches + otherInches;

            // convert into target unit
            double resultValue =
                    totalInches / targetUnit.getConversionFactor();

            return new Length(resultValue, targetUnit);
    }

    // UC7 -> ADDITION WITH TARGET UNIT
//    public Length add(
//            Length otherLength,
//            LengthUnit targetUnit
//    ) {
//
//        // validation
//        if (otherLength == null) {
//            throw new IllegalArgumentException("Length cannot be null");
//        }
//
//        if (targetUnit == null) {
//            throw new IllegalArgumentException("Target unit cannot be null");
//        }
//
//        // convert both lengths to inches
//        double firstLengthInInches =
//                this.toInches();
//
//        double secondLengthInInches =
//                otherLength.toInches();
//
//        // add both
//        double totalInInches =
//                firstLengthInInches + secondLengthInInches;
//
//        // convert result to TARGET UNIT
//        double finalValue =
//                totalInInches / targetUnit.getConversionFactor();
//
//        // return NEW object
//        return new Length(finalValue, targetUnit);
//    }

    //ENUM INSIDE SAME CLASS
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {

            this.conversionFactor = conversionFactor;
        }


        public double getConversionFactor() {

            return conversionFactor;
        }
    }
}