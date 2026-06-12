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

//    public Quantity<U> add(Quantity<U> other, U targetUnit) {
//        if (other == null) {
//            throw new IllegalArgumentException("Quantity cannot be null");
//        }
//        if (targetUnit == null) {
//            throw new IllegalArgumentException("Target unit cannot be null");
//        }
//
//        double thisBase = this.unit.convertToBaseUnit(this.value);
//        double otherBase = other.unit.convertToBaseUnit(other.value);
//
//        double sumBase = thisBase + otherBase;
//        double result = targetUnit.convertFromBaseUnit(sumBase);
//
//        return new Quantity<>(round(result), targetUnit);
//    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double result =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        return new Quantity<>(
                round(result),
                targetUnit
        );
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

//    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
//
//        if (other == null) {
//            throw new IllegalArgumentException("Quantity cannot be null");
//        }
//
//        if (targetUnit == null) {
//            throw new IllegalArgumentException("Target unit cannot be null");
//        }
//
//        if (this.unit.getClass() != other.unit.getClass()) {
//            throw new IllegalArgumentException(
//                    "Cannot subtract different measurement categories"
//            );
//        }
//
//        double thisBase =
//                this.unit.convertToBaseUnit(this.value);
//
//        double otherBase =
//                other.unit.convertToBaseUnit(other.value);
//
//        double resultBase =
//                thisBase - otherBase;
//
//        double result =
//                targetUnit.convertFromBaseUnit(resultBase);
//
//        return new Quantity<>(round(result), targetUnit);
//    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double resultBase =
                performArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double result =
                targetUnit.convertFromBaseUnit(
                        resultBase
                );

        return new Quantity<>(
                round(result),
                targetUnit
        );
    }

//    public double divide(Quantity<U> other) {
//
//        if (other == null) {
//            throw new IllegalArgumentException(
//                    "Quantity cannot be null"
//            );
//        }
//
//        if (this.unit.getClass() != other.unit.getClass()) {
//            throw new IllegalArgumentException(
//                    "Cannot divide different measurement categories"
//            );
//        }
//
//        double thisBase =
//                this.unit.convertToBaseUnit(this.value);
//
//        double otherBase =
//                other.unit.convertToBaseUnit(other.value);
//
//        if (otherBase == 0) {
//            throw new ArithmeticException(
//                    "Division by zero"
//            );
//        }
//
//        return thisBase / otherBase;
//    }

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false
        );

        return performArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null"
            );
        }

        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException(
                    "Cannot operate on different measurement categories"
            );
        }

        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }
    }

    private double performArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double thisBase =
                this.unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return operation.compute(
                thisBase,
                otherBase
        );
    }

    private enum ArithmeticOperation {

        ADD {
            @Override
            public double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            @Override
            public double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            @Override
            public double compute(double a, double b) {

                if (b == 0.0) {
                    throw new ArithmeticException(
                            "Division by zero"
                    );
                }

                return a / b;
            }
        };

        public abstract double compute(
                double a,
                double b
        );
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
