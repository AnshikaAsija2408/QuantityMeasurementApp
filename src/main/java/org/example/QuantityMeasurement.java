//package org.example;
//
//public class QuantityMeasurement {
//    public static class Feet {
//        private final double value;
//
//        public Feet(double value) {
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//
//            if (this == obj)
//                return true;
//
//            if (obj == null || this.getClass() != obj.getClass())
//                return false;
//
//            Feet feet = (Feet)obj;
//
//            return Double.compare(this.value , feet.value)==0;
//        }
//    }
//
//    public static class Inches {
//        private final double value;
//
//        public Inches(double value) {
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//
//            if (this == obj) return true;
//
//            if (obj == null || getClass() != obj.getClass()) return false;
//
//            Inches inches = (Inches) obj;
//
//            return Double.compare(this.value, inches.value) == 0;
//        }
//    }
//    public static void demonstrateFeetEquality() {
//        Feet f1 = new Feet(1.0);
//        Feet f2 = new Feet(1.0);
//
//        System.out.println("Feet Equal: " + f1.equals(f2));
//    }
//
//    public static void demonstrateInchesEquality() {
//        Inches i1 = new Inches(1.0);
//        Inches i2 = new Inches(1.0);
//
//        System.out.println("Inches Equal: " + i1.equals(i2));
//    }
//
//    public static void main(String[] args) {
//        demonstrateFeetEquality();
//        demonstrateInchesEquality();
//    }
//}

package org.example;

public class QuantityMeasurement {

    // Generic method to demonstrate EQUALITY comparison
    public static void demonstrateLengthEquality(
            double value1,
            Length.LengthUnit unit1,
            double value2,
            Length.LengthUnit unit2
    ) {

        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);

        System.out.println(value1 + " " + unit1 + " and " +value2 + " " + unit2 +" are equal: " +length1.equals(length2));
    }

    // Method 2
    // Demonstrates comparison feature
    public static void demonstrateLengthComparison(
            double value1,
            Length.LengthUnit unit1,
            double value2,
            Length.LengthUnit unit2
    ) {

        demonstrateLengthEquality(
                value1,
                unit1,
                value2,
                unit2
        );
    }
    public static Length demonstrateLengthConversion(
            double value,
            Length.LengthUnit fromUnit,
            Length.LengthUnit toUnit
    ) {

        Length originalLength =
                new Length(value, fromUnit);
        System.out.println("Converted " + value + " " + fromUnit + " to " + originalLength.convertTo(toUnit).getValue() + " " +originalLength.convertTo(toUnit).getUnit()
        );

        return originalLength.convertTo(toUnit);
    }

    // Method Overloading
    // Conversion using existing Length object
    public static Length demonstrateLengthConversion(
            Length length,
            Length.LengthUnit toUnit
    ) {
        System.out.println("Converted " +length +" to " +toUnit
        );
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {

        // Feet and Inches comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.FEET,
                12.0,
                Length.LengthUnit.INCHES
        );

        // Yard and Feet comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.YARDS,
                3.0,
                Length.LengthUnit.FEET
        );

        // Yard and Inches comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.YARDS,
                36.0,
                Length.LengthUnit.INCHES
        );

        // Centimeter and Inches comparison
        demonstrateLengthEquality(
                1.0,
                Length.LengthUnit.CENTIMETERS,
                0.393701,
                Length.LengthUnit.INCHES
        );

        // Centimeter and Feet comparison
        demonstrateLengthEquality(
                30.48,
                Length.LengthUnit.CENTIMETERS,
                1.0,
                Length.LengthUnit.FEET
        );

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.FEET,
                12.0,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.YARDS,
                3.0,
                Length.LengthUnit.FEET
        );

        demonstrateLengthComparison(
                1.0,
                Length.LengthUnit.YARDS,
                36.0,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthComparison(
                30.48,
                Length.LengthUnit.CENTIMETERS,
                1.0,
                Length.LengthUnit.FEET
        );

        // Conversion demonstrations

        demonstrateLengthConversion(
                1.0,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                24.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET
        );

        demonstrateLengthConversion(
                1.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                72.0,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                2.54,
                Length.LengthUnit.CENTIMETERS,
                Length.LengthUnit.INCHES
        );
        Length converted =
                demonstrateLengthConversion(
                        1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                );

        System.out.println(converted);
    }
}