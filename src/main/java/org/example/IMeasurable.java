//package org.example;

//public interface IMeasurable {
//    double getConversionFactor();
//
//    double convertToBaseUnit(double value);
//
//    double convertFromBaseUnit(double baseValue);
//
//    String getUnitName();
//}

//uc14
//@FunctionalInterface
//interface SupportsArithmetic {
//    boolean isSupported();
//}
//
//public interface IMeasurable {
//
//    String getUnitName();
//
//    double getConversionFactor();
//
//    double convertToBaseUnit(double value);
//
//    double convertFromBaseUnit(double baseValue);
//
//    SupportsArithmetic supportsArithmetic = () -> true;
//
//    default boolean supportsArithmetic() {
//        return supportsArithmetic.isSupported();
//    }
//
//    default void validateOperationSupport(String operation) {
//        // default: all operations allowed
//    }
//}

package org.example;

public interface IMeasurable {

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    SupportArithmetic ARITHMETIC_SUPPORT =
            () -> true;

    default boolean supportArithmetic() {
        return ARITHMETIC_SUPPORT.isSupported();
    }

    default void validateOperationSupport(
            String operation
    ) {
        // default: all operations supported
    }
}
