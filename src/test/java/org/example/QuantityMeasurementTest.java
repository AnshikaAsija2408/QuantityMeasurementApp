//package org.example;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.example.QuantityMeasurement.Feet;
//import org.example.QuantityMeasurement.Inches;
//
//public class QuantityMeasurementTest {
//
//    @Test
//    public void testSameValue() {
//        Feet f1 = new Feet(1.0);    //f1 is not the object. it si the reference
//        Feet f2 = new Feet(1.0);
//
//        assertTrue(f1.equals(f2));
//    }
//
//    @Test
//    public void testDifferentValue() {
//        Feet f1 = new Feet(1.0);
//        Feet f2 = new Feet(2.0);
//
//        assertFalse(f1.equals(f2));
//    }
//
//    @Test
//    public void testNull() {
//        Feet f1 = new Feet(1.0);
//
//        assertFalse(f1.equals(null));
//    }
//
//    @Test
//    public void testSameReference() {
//        Feet f1 = new Feet(1.0);
//        assertTrue(f1.equals(f1));
//    }
//
//    //  DIFFERENT CLASS
//    @Test
//    public void testDifferentClass() {
//        Feet f1 = new Feet(1.0);
//        String str = "1.0";
//        assertFalse(f1.equals(str));
//    }
//
//    @Test
//    public void testInchesSameValue() {
//        Inches i1 = new Inches(1.0);
//        Inches i2 = new Inches(1.0);
//        assertTrue(i1.equals(i2));
//    }
//
//    @Test
//    public void testInchesDifferentValue() {
//        Inches i1 = new Inches(1.0);
//        Inches i2 = new Inches(2.0);
//        assertFalse(i1.equals(i2));
//    }
//
//    @Test
//    public void testInchesNull() {
//        Inches i1 = new Inches(1.0);
//        assertFalse(i1.equals(null));
//    }
//
//    @Test
//    public void testInchesDifferentClass() {
//        Inches i1 = new Inches(1.0);
//        Feet f1 = new Feet(1.0);
//        assertFalse(i1.equals(f1));
//    }
//
//    @Test
//    public void testInchesSameReference() {
//        Inches i1 = new Inches(1.0);
//        assertTrue(i1.equals(i1));
//    }
//}


package org.example;

import org.example.Length;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuantityMeasurementTest {

    //Feet equality (same value)
    @Test
    public void testFeetEquality() {
        Length f1 = new Length(1.0, LengthUnit.FEET);
        Length f2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(f1.equals(f2));
    }

    //Inches equality (same value)
    @Test
    public void testInchesEquality() {
        Length i1 = new Length(1.0, LengthUnit.INCHES);
        Length i2 = new Length(1.0, LengthUnit.INCHES);

        assertTrue(i1.equals(i2));
    }

    //Feet ↔ Inches comparison
    @Test
    public void testFeetInchesComparison() {
        Length f = new Length(1.0, LengthUnit.FEET);
        Length i = new Length(12.0, LengthUnit.INCHES);

        assertTrue(f.equals(i));
    }

    //Feet inequality
    @Test
    public void testFeetInequality() {
        Length f1 = new Length(1.0, LengthUnit.FEET);
        Length f2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(f1.equals(f2));
    }

    //Inches inequality
    @Test
    public void testInchesInequality() {
        Length i1 = new Length(1.0, LengthUnit.INCHES);
        Length i2 = new Length(2.0, LengthUnit.INCHES);

        assertFalse(i1.equals(i2));
    }

    //Cross unit inequality (not equal)1
    @Test
    public void testCrossUnitInequality() {
        Length f = new Length(1.0, LengthUnit.FEET);
        Length i = new Length(10.0, LengthUnit.INCHES);

        assertFalse(f.equals(i));
    }

    //Same reference test
    @Test
    public void testSameReference() {
        Length f = new Length(1.0, LengthUnit.FEET);

        assertTrue(f.equals(f));
    }

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(new Length(1.0, LengthUnit.YARDS),
                new Length(1.0, LengthUnit.YARDS));
    }

    // 2
    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(new Length(1.0, LengthUnit.YARDS),
                new Length(2.0, LengthUnit.YARDS));
    }

    // 3
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET));
    }

    // 4
    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(new Length(3.0, LengthUnit.FEET),
                new Length(1.0, LengthUnit.YARDS));
    }

    // 5
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(new Length(1.0, LengthUnit.YARDS),
                new Length(36.0, LengthUnit.INCHES));
    }

    // 6
    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertEquals(new Length(36.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS));
    }

    // 7
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertNotEquals(new Length(1.0, LengthUnit.YARDS),
                new Length(2.0, LengthUnit.FEET));
    }

    // 8
//    @Test
//    void testEquality_centimetersToInches_EquivalentValue() {
//        assertEquals(new Length(1.0, LengthUnit.CENTIMETERS),
//                new Length(0.393701, LengthUnit.INCHES));
//    }

    // 9
    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertNotEquals(new Length(1.0, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.FEET));
    }

    // 10 (transitive)
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    // 11 (null unit)
    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    // 12 (same reference)
    @Test
    void testEquality_YardSameReference() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    // 13 (compare with null)
    @Test
    void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertNotEquals(yard, null);
    }

    // 14 (cm null unit)
    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    // 15 (cm same reference)
    @Test
    void testEquality_CentimetersSameReference() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertEquals(cm, cm);
    }

    // 16 (cm null comparison)
    @Test
    void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertNotEquals(cm, null);
    }

    // 17 (complex scenario)
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        assertEquals(new Length(2.0, LengthUnit.YARDS),
                new Length(6.0, LengthUnit.FEET));

        assertEquals(new Length(6.0, LengthUnit.FEET),
                new Length(72.0, LengthUnit.INCHES));
    }

    // ========================= UC5 TEST CASES =========================

    @Test
    void testConversion_FeetToInches() {
        Length result = QuantityMeasurement.demonstrateLengthConversion(1.0,LengthUnit.FEET,LengthUnit.INCHES  );
        assertEquals(new Length(12.0, LengthUnit.INCHES), result
        );
    }

    // 2
    @Test
    void testConversion_InchesToFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        24.0,
                        LengthUnit.INCHES,
                        LengthUnit.FEET
                );

        assertEquals(
                new Length(2.0, LengthUnit.FEET),
                result
        );
    }

    // 3
    @Test
    void testConversion_YardsToInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        1.0,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES
                );

        assertEquals(
                new Length(36.0, LengthUnit.INCHES),
                result
        );
    }

    // 4
    @Test
    void testConversion_InchesToYards() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        72.0,
                        LengthUnit.INCHES,
                        LengthUnit.YARDS
                );

        assertEquals(
                new Length(2.0, LengthUnit.YARDS),
                result
        );
    }

    // 5
    @Test
    void testConversion_CentimetersToInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        2.54,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES
                );

        assertEquals(
                1.0,
                result.getValue(),
                0.0001
        );
    }

    // 6
    @Test
    void testConversion_FeetToYards() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        6.0,
                        LengthUnit.FEET,
                        LengthUnit.YARDS
                );

        assertEquals(
                new Length(2.0, LengthUnit.YARDS),
                result
        );
    }

    // 7
    @Test
    void testConversion_RoundTrip_PreservesValue() {

        Length firstConversion =
                QuantityMeasurement.demonstrateLengthConversion(
                        5.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        Length secondConversion =
                QuantityMeasurement.demonstrateLengthConversion(
                        firstConversion,
                        LengthUnit.FEET
                );

        assertEquals(
                5.0,
                secondConversion.getValue(),
                0.0001
        );
    }

    // 8
    @Test
    void testConversion_ZeroValue2() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        0.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        assertEquals(
                new Length(0.0, LengthUnit.INCHES),
                result
        );
    }

    // 9
    @Test
    void testConversion_NegativeValue2() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        -1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                );

        assertEquals(
                new Length(-12.0, LengthUnit.INCHES),
                result
        );
    }

    // 10
    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthConversion(
                        1.0,
                        null,
                        LengthUnit.INCHES
                )
        );
    }

    // 11
    @Test
    void testConversion_NaNOrInfinite_Throws() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthConversion(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthConversion(
                        Double.POSITIVE_INFINITY,
                        LengthUnit.FEET,
                        LengthUnit.INCHES
                )
        );
    }

    // 12
    @Test
    void testConversion_PrecisionTolerance() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        2.54,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES
                );

        assertEquals(
                1.0,
                result.getValue(),
                1e-6
        );
    }


    //UC6

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(2.0, LengthUnit.FEET)
                );

        assertEquals(new Length(3.0, LengthUnit.FEET),result);
    }

    // 2
    @Test
    public void testAddition_SameUnit_InchPlusInch() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(6.0, LengthUnit.INCHES),
                        new Length(6.0, LengthUnit.INCHES)
                );

        assertEquals(
                new Length(12.0, LengthUnit.INCHES),result);
    }

    // 3
    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES)
                );

        assertEquals(
                new Length(2.0, LengthUnit.FEET),result);
    }

    // 4
    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(12.0, LengthUnit.INCHES),
                        new Length(1.0, LengthUnit.FEET)
                );

        assertEquals(
                new Length(24.0, LengthUnit.INCHES),result);
    }

    // 5
    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.YARDS),
                        new Length(3.0, LengthUnit.FEET)
                );

        assertEquals(
                new Length(2.0, LengthUnit.YARDS),result);
    }

    // 6
    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(2.54, LengthUnit.CENTIMETERS),
                        new Length(1.0, LengthUnit.INCHES)
                );

        assertEquals(5.08,result.getValue(),0.01);
    }

    // 7
    @Test
    public void testAddition_Commutativity() {

        Length result1 =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES)
                );

        Length result2 =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(12.0, LengthUnit.INCHES),
                        new Length(1.0, LengthUnit.FEET)
                );

        assertEquals(result1.convertTo(LengthUnit.INCHES),result2);
    }

    // 8
    @Test
    public void testAddition_WithZero() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(5.0, LengthUnit.FEET),
                        new Length(0.0, LengthUnit.INCHES)
                );

        assertEquals(new Length(5.0, LengthUnit.FEET),result);
    }

    // 9
    @Test
    public void testAddition_NegativeValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(5.0, LengthUnit.FEET),
                        new Length(-2.0, LengthUnit.FEET)
                );

        assertEquals(new Length(3.0, LengthUnit.FEET),result);
    }

    // 10
    @Test
    public void testAddition_NullSecondOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, LengthUnit.FEET),
                        null
                )
        );
    }

    // 11
    @Test
    public void testAddition_LargeValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1e6, LengthUnit.FEET),
                        new Length(1e6, LengthUnit.FEET)
                );

        assertEquals(new Length(2e6, LengthUnit.FEET),result);
    }

    // 12
    @Test
    public void testAddition_SmallValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(0.001, LengthUnit.FEET),
                        new Length(0.002, LengthUnit.FEET)
                );

        assertEquals(0.003,result.getValue(),0.0001);
    }

    private static final double EPSILON = 0.001;

    // 1
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Length length1 =
                new Length(1.0, LengthUnit.FEET);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // 2
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Length length1 =
                new Length(1.0, LengthUnit.FEET);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // 3
    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        Length length1 =
                new Length(1.0, LengthUnit.FEET);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 4
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        Length length1 =
                new Length(1.0, LengthUnit.INCHES);

        Length length2 =
                new Length(1.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    // 5
    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        Length length1 =
                new Length(2.0, LengthUnit.YARDS);

        Length length2 =
                new Length(3.0, LengthUnit.FEET);

        Length result =
                length1.add(length2, LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 6
    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        Length length1 =
                new Length(2.0, LengthUnit.YARDS);

        Length length2 =
                new Length(3.0, LengthUnit.FEET);

        Length result =
                length1.add(length2, LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // 7
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Length length1 =
                new Length(1.0, LengthUnit.FEET);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result1 =
                length1.add(length2, LengthUnit.YARDS);

        Length result2 =
                length2.add(length1, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    // 8
    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        Length length1 =
                new Length(5.0, LengthUnit.FEET);

        Length length2 =
                new Length(0.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 9
    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        Length length1 =
                new Length(5.0, LengthUnit.FEET);

        Length length2 =
                new Length(-2.0, LengthUnit.FEET);

        Length result =
                length1.add(length2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // 10
    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        Length length1 =
                new Length(1.0, LengthUnit.FEET);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> length1.add(length2, null)
        );
    }

    // 11
    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length length1 =
                new Length(1000.0, LengthUnit.FEET);

        Length length2 =
                new Length(500.0, LengthUnit.FEET);

        Length result =
                length1.add(length2, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // 12
    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        Length length1 =
                new Length(12.0, LengthUnit.INCHES);

        Length length2 =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 13
    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length yards =
                new Length(1.0, LengthUnit.YARDS);

        Length centimeters =
                new Length(30.48, LengthUnit.CENTIMETERS);

        assertEquals(
                feet.add(inches, LengthUnit.FEET),
                new Length(2.0, LengthUnit.FEET)
        );

        assertEquals(
                yards.add(feet, LengthUnit.YARDS),
                new Length(1.3333333333333333,
                        LengthUnit.YARDS)
        );

        Length result =
                centimeters.add(
                        inches,
                        LengthUnit.CENTIMETERS
                );

        assertEquals(
                60.96,
                result.getValue(),
                0.01
        );

        assertEquals(
                LengthUnit.CENTIMETERS,
                result.getUnit()
        );
    }

    // 14
    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        Length length1 =
                new Length(2.54, LengthUnit.CENTIMETERS);

        Length length2 =
                new Length(1.0, LengthUnit.INCHES);

        Length result =
                length1.add(length2,
                        LengthUnit.CENTIMETERS);

        assertEquals(5.08,
                result.getValue(),
                EPSILON);
    }

    // UC8 -> LengthUnit enum constant tests

    @Test
    public void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {

        assertEquals(
                1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON
        );
    }

    // UC8 -> convertToBaseUnit tests

    @Test
    public void testConvertToBaseUnit_FeetToFeet() {

        assertEquals(
                5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {

        assertEquals(
                3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {

        assertEquals(
                1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON
        );
    }

    // UC8 -> convertFromBaseUnit tests

    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {

        assertEquals(
                2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {

        assertEquals(
                1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {

        assertEquals(
                30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    // UC8 -> Refactored Length equality

    @Test
    public void testQuantityLengthRefactored_Equality() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        assertEquals(
                feet,
                inches
        );
    }

    // UC8 -> Refactored convertTo()

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit()
        );
    }

    // UC8 -> Refactored add() with target unit FEET

    @Test
    public void testQuantityLengthRefactored_Add() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.FEET,
                result.getUnit()
        );
    }

    // UC8 -> Refactored add() with target unit YARDS

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.YARDS);

        assertEquals(
                0.667,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.YARDS,
                result.getUnit()
        );
    }

    // UC8 -> Null unit validation

    @Test
    public void testQuantityLengthRefactored_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(1.0, null)
        );
    }

    // UC8 -> Invalid value validation

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET)
        );
    }

    // Backward Compatibility -> UC1 Equality Tests

    @Test
    public void testBackwardCompatibility_UC1EqualityTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length yards =
                new Length(1.0, LengthUnit.YARDS);

        Length thirtySixInches =
                new Length(36.0, LengthUnit.INCHES);

        assertEquals(
                feet,
                inches
        );

        assertEquals(
                yards,
                thirtySixInches
        );
    }

    // Backward Compatibility -> UC5 Conversion Tests

    @Test
    public void testBackwardCompatibility_UC5ConversionTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit()
        );
    }

    // Backward Compatibility -> UC6 Addition Tests

    @Test
    public void testBackwardCompatibility_UC6AdditionTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches);

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.FEET,
                result.getUnit()
        );
    }

    // Backward Compatibility -> UC7 Addition With Target Unit Tests

    @Test
    public void testBackwardCompatibility_UC7AdditionWithTargetUnitTests() {

        Length feet =
                new Length(1.0, LengthUnit.FEET);

        Length inches =
                new Length(12.0, LengthUnit.INCHES);

        Length result =
                feet.add(inches, LengthUnit.INCHES);

        assertEquals(
                24.0,
                result.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit()
        );
    }

    // Architectural Scalability Test

    @Test
    public void testArchitecturalScalability_MultipleCategories() {

        assertNotNull(
                LengthUnit.FEET
        );

        assertNotNull(
                LengthUnit.INCHES
        );

        assertNotNull(
                LengthUnit.YARDS
        );

        assertNotNull(
                LengthUnit.CENTIMETERS
        );
    }

    // Round Trip Conversion Test

    @Test
    public void testRoundTripConversion_RefactoredDesign() {

        Length original =
                new Length(5.0, LengthUnit.FEET);

        Length convertedToInches =
                original.convertTo(LengthUnit.INCHES);

        Length convertedBackToFeet =
                convertedToInches.convertTo(LengthUnit.FEET);

        assertEquals(
                original.getValue(),
                convertedBackToFeet.getValue(),
                EPSILON
        );

        assertEquals(
                LengthUnit.FEET,
                convertedBackToFeet.getUnit()
        );
    }

    // Unit Immutability Test

    @Test
    public void testUnitImmutability() {

        LengthUnit feetUnit =
                LengthUnit.FEET;

        LengthUnit anotherFeetUnit =
                LengthUnit.FEET;

        assertSame(
                feetUnit,
                anotherFeetUnit
        );
    }

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertEquals(new Weight(1000.0, WeightUnit.GRAM),
                new Weight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM), null);
    }

    @Test
    void testEquality_SameReference() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(weight, weight);
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    void testEquality_ZeroValue() {
        assertEquals(new Weight(0.0, WeightUnit.KILOGRAM),
                new Weight(0.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_NegativeWeight() {
        assertEquals(new Weight(-1.0, WeightUnit.KILOGRAM),
                new Weight(-1000.0, WeightUnit.GRAM));
    }

    @Test
    void testConversion_PoundToKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testConversion_KilogramToPound() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    void testConversion_SameUnit() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_ZeroValue() {
        Weight result = new Weight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(0.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_NegativeValue() {
        Weight result = new Weight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(new Weight(-1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_RoundTrip() {
        Weight result = new Weight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), 0.001);
    }

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(4.40924, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testWeightAddition_WithZero() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM));

        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testWeightAddition_NegativeValues() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM));

        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testWeightAddition_LargeValues() {
        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM));

        assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testEquality_LargeWeightValue() {
        assertEquals(new Weight(1000000.0, WeightUnit.GRAM),
                new Weight(1000.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_SmallWeightValue() {
        assertEquals(new Weight(0.001, WeightUnit.KILOGRAM),
                new Weight(1.0, WeightUnit.GRAM));
    }

    @Test
    void testWeightAddition_Commutativity() {
        Weight result1 = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        Weight result2 = new Weight(1000.0, WeightUnit.GRAM)
                .add(new Weight(1.0, WeightUnit.KILOGRAM));

        assertEquals(result1, result2);
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        assertNotEquals(new Weight(1.0, WeightUnit.KILOGRAM),
                new Length(1.0, LengthUnit.FEET));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight gram = new Weight(1000.0, WeightUnit.GRAM);
        Weight pound = new Weight(2.20462, WeightUnit.POUND);

        assertEquals(kg, gram);
        assertEquals(gram, pound);
        assertEquals(kg, pound);
    }

    @Test
    void testWeightUnitEnumMethods() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor());
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor());
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor());

        assertEquals(1.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0));
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0));
        assertEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0));

        assertEquals(1.0, WeightUnit.KILOGRAM.convertFromBaseUnit(1.0));
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0));
        assertEquals(2.20462, WeightUnit.POUND.convertFromBaseUnit(1.0), 0.001);
    }

    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor());
    }

    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(oneFeet, twelveInches);
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGram = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(oneKg, thousandGram);
    }

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = oneFeet.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = oneKg.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = oneFeet.add(twelveInches, LengthUnit.FEET);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGram = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = oneKg.add(thousandGram, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<LengthUnit>(1.0, null);
        });
    }

    @Test
    void testGenericQuantity_ConstructorValidation_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InfiniteValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET);
        });
    }

    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(oneFeet.hashCode(), twelveInches.hashCode());
    }

    @Test
    void testEquals_GenericQuantity_Reflexive() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(length, length);
    }

    @Test
    void testEquals_GenericQuantity_Symmetric() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(oneFeet, twelveInches);
        assertEquals(twelveInches, oneFeet);
    }

    @Test
    void testEquals_GenericQuantity_NullObject() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertNotEquals(null, length);
    }

    @Test
    void testEquals_GenericQuantity_DifferentObjectType() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);

        assertNotEquals("1 feet", length);
    }

    @Test
    void testAddition_DefaultTargetUnit_FirstOperandUnit() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = oneFeet.add(twelveInches);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testConversion_YardToFeet() {
        Quantity<LengthUnit> oneYard = new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> result = oneYard.convertTo(LengthUnit.FEET);

        assertEquals(new Quantity<>(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testConversion_PoundToKg() {
        Quantity<WeightUnit> pound = new Quantity<>(2.20462, WeightUnit.POUND);

        Quantity<WeightUnit> result = pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(1.0, WeightUnit.KILOGRAM), result);
    }

//    @Test
//    void testEquality_LitreToLitre_SameValue() {
//        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(1.0, VolumeUnit.LITRE));
//    }

//    @Test
//    void testEquality_LitreToLitre_DifferentValue() {
//        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(2.0, VolumeUnit.LITRE));
//    }
//
//    @Test
//    void testEquality_LitreToMillilitre_EquivalentValue() {
//        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
//    }
//
//    @Test
//    void testEquality_MillilitreToLitre_EquivalentValue() {
//        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
//                new Quantity<>(1.0, VolumeUnit.LITRE));
//    }
//
//    @Test
//    void testEquality_LitreToGallon_EquivalentValue() {
//        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(0.264172, VolumeUnit.GALLON));
//    }
//
//    @Test
//    void testEquality_GallonToLitre_EquivalentValue() {
//        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON),
//                new Quantity<>(3.78541, VolumeUnit.LITRE));
//    }
//
//    @Test
//    void testEquality_VolumeVsLength_Incompatible() {
//        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(1.0, LengthUnit.FEET));
//    }
//
//    @Test
//    void testEquality_VolumeVsWeight_Incompatible() {
//        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
//                new Quantity<>(1.0, WeightUnit.KILOGRAM));
//    }
//
//    @Test
//    void testEquality_NullComparison() {
//        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), null);
//    }
//
//    @Test
//    void testEquality_SameReference() {
//        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
//        assertEquals(volume, volume);
//    }
//
//    @Test
//    void testEquality_NullUnit() {
//        assertThrows(IllegalArgumentException.class, () ->
//                new Quantity<>(1.0, null));
//    }
//
//    @Test
//    void testEquality_TransitiveProperty() {
//        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
//        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
//        Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
//
//        assertEquals(litre, ml);
//        assertEquals(ml, gallon);
//        assertEquals(litre, gallon);
//    }
//
//    @Test
//    void testEquality_ZeroValue() {
//        assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE),
//                new Quantity<>(0.0, VolumeUnit.MILLILITRE));
//    }
//
//    @Test
//    void testEquality_NegativeVolume() {
//        assertEquals(new Quantity<>(-1.0, VolumeUnit.LITRE),
//                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE));
//    }
//
//    @Test
//    void testEquality_LargeVolumeValue() {
//        assertEquals(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE),
//                new Quantity<>(1000.0, VolumeUnit.LITRE));
//    }

//    @Test
//    void testEquality_SmallVolumeValue() {
//        assertEquals(new Quantity<>(0.001, VolumeUnit.LITRE),
//                new Quantity<>(1.0, VolumeUnit.MILLILITRE));
//    }

    // ---------------- Conversion Tests ----------------

    // ========================= UC11 VOLUME TEST CASES =========================

    @Test
    void testUC11Equality_LitreToLitre_SameValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    void testUC11Equality_LitreToLitre_DifferentValue() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE));
    }

    @Test
    void testUC11Equality_LitreToMillilitre_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testUC11Equality_MillilitreToLitre_EquivalentValue() {
        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE));
    }

    @Test
    void testUC11Equality_LitreToGallon_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(0.264172, VolumeUnit.GALLON));
    }

    @Test
    void testUC11Equality_GallonToLitre_EquivalentValue() {
        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON),
                new Quantity<>(3.78541, VolumeUnit.LITRE));
    }

    @Test
    void testUC11Equality_VolumeVsLength_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, LengthUnit.FEET));
    }

    @Test
    void testUC11Equality_VolumeVsWeight_Incompatible() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testUC11Equality_NullComparison() {
        assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), null);
    }

    @Test
    void testUC11Equality_SameReference() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(volume, volume);
    }

    @Test
    void testUC11Equality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, null));
    }

    @Test
    void testUC11Equality_ZeroValue() {
        assertEquals(new Quantity<>(0.0, VolumeUnit.LITRE),
                new Quantity<>(0.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testUC11Equality_NegativeVolume() {
        assertEquals(new Quantity<>(-1.0, VolumeUnit.LITRE),
                new Quantity<>(-1000.0, VolumeUnit.MILLILITRE));
    }

    @Test
    void testUC11Equality_LargeVolumeValue() {
        assertEquals(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1000.0, VolumeUnit.LITRE));
    }

    @Test
    void testUC11Equality_SmallVolumeValue() {
        assertEquals(new Quantity<>(0.001, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.MILLILITRE));
    }

// ---------------- UC11 Conversion Tests ----------------

    @Test
    void testUC11Conversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testUC11Conversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Conversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(3.78541, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Conversion_LitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE).convertTo(VolumeUnit.GALLON);

        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), result);
    }

    @Test
    void testUC11Conversion_SameUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Conversion_ZeroValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(0.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testUC11Conversion_NegativeValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(-1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE), result);
    }

// ---------------- UC11 Addition Tests ----------------

    @Test
    void testUC11Addition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Addition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Addition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testUC11Addition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);

        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Addition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testUC11Addition_WithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testUC11Addition_NegativeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), result);
    }

// ---------------- UC11 VolumeUnit Enum Tests ----------------

    @Test
    void testUC11VolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testUC11VolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    void testUC11VolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    void testUC11ConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    void testUC11ConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testUC11IMeasurableInterface_VolumeUnitImplementation() {
        assertTrue(VolumeUnit.LITRE instanceof IMeasurable);
    }

    //UC12
    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(120.0, LengthUnit.INCHES)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.FEET);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES),
                                LengthUnit.INCHES);

        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(3000.0, VolumeUnit.MILLILITRE),
                result
        );
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(-2.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> result1 =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        Quantity<LengthUnit> result2 =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertNotEquals(result1, result2);
    }

    @Test
    void testSubtraction_WithLargeValues() {
        Quantity<WeightUnit> result =
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));

        assertEquals(
                new Quantity<>(5e5, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testSubtraction_WithSmallValues() {
        Quantity<LengthUnit> result =
                new Quantity<>(0.001, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0005, LengthUnit.FEET));

        assertEquals(0.0005, result.getValue(), 0.001);
    }

    @Test
    void testSubtraction_NullOperand() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(null)
        );
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(
                                new Quantity<>(5.0, LengthUnit.FEET),
                                null
                        )
        );
    }

    @Test
    void testSubtraction_CrossCategory() {

        Quantity length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract(weight)
        );
    }

    @Test
    void testSubtraction_AllMeasurementCategories() {

        assertEquals(
                new Quantity<>(5.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET))
        );

        assertEquals(
                new Quantity<>(5.0, WeightUnit.KILOGRAM),
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM))
        );

        assertEquals(
                new Quantity<>(3.0, VolumeUnit.LITRE),
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE))
        );
    }

    @Test
    void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        assertEquals(
                5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        assertEquals(
                2.0,
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(5.0, VolumeUnit.LITRE)),
                0.01
        );
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        assertEquals(
                1.0,
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {
        assertEquals(
                1.0,
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(2000.0, WeightUnit.GRAM)),
                0.01
        );
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        assertEquals(
                5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testDivision_RatioLessThanOne() {
        assertEquals(
                0.5,
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testDivision_RatioEqualToOne() {
        assertEquals(
                1.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testDivision_NonCommutative() {

        double result1 =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET));

        double result2 =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertNotEquals(result1, result2);
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(
                ArithmeticException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET))
        );
    }

    @Test
    void testDivision_WithLargeRatio() {
        assertEquals(
                1e6,
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM)),
                0.01
        );
    }

    @Test
    void testDivision_WithSmallRatio() {
        assertEquals(
                1e-6,
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM)),
                0.000001
        );
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(null)
        );
    }

    @Test
    void testDivision_CrossCategory() {

        Quantity length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide(weight)
        );
    }

    @Test
    void testDivision_AllMeasurementCategories() {

        assertEquals(
                2.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET)),
                0.01
        );

        assertEquals(
                2.0,
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)),
                0.01
        );

        assertEquals(
                2.5,
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(2.0, VolumeUnit.LITRE)),
                0.01
        );
    }

    @Test
    void testSubtractionAndDivision_Integration() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(
                4.0,
                result.divide(new Quantity<>(2.0, LengthUnit.FEET)),
                0.01
        );
    }

    @Test
    void testSubtractionAddition_Inverse() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                original.add(new Quantity<>(5.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(original, result);
    }

    @Test
    void testSubtraction_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.subtract(
                new Quantity<>(5.0, LengthUnit.FEET)
        );

        assertEquals(
                new Quantity<>(10.0, LengthUnit.FEET),
                original
        );
    }

    @Test
    void testDivision_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.divide(
                new Quantity<>(2.0, LengthUnit.FEET)
        );

        assertEquals(
                new Quantity<>(10.0, LengthUnit.FEET),
                original
        );
    }

    @Test
    void testSubtraction_PrecisionAndRounding() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.55, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.33, LengthUnit.FEET));

        assertEquals(
                new Quantity<>(10.22, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testDivision_PrecisionHandling() {

        double result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(3.0, WeightUnit.KILOGRAM));

        assertEquals(0.3333, result, 0.01);
    }

    //========UC13=========
    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {

        Quantity<LengthUnit> q =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q.add(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> q.subtract(null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> q.divide(null)
        );
    }

    @Test
    void testValidation_CrossCategory_ConsistentAcrossOperations() {

        Quantity length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> length.add(weight)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> length.subtract(weight)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> length.divide(weight)
        );
    }

    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.add(q2, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.subtract(q2, null)
        );
    }

    @Test
    void testAdd_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(12.0, LengthUnit.INCHES)
                        );

        assertEquals(
                new Quantity<>(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(
                                new Quantity<>(6.0, LengthUnit.INCHES)
                        );

        assertEquals(
                new Quantity<>(9.5, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {

        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(
                                new Quantity<>(2.0, LengthUnit.FEET)
                        );

        assertEquals(
                1.0,
                result
        );
    }

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.11, LengthUnit.FEET)
                        .add(
                                new Quantity<>(2.22, LengthUnit.FEET)
                        );

        assertEquals(
                3.33,
                result.getValue()
        );
    }

    @Test
    void testRounding_Divide_NoRounding() {

        double result =
                new Quantity<>(7.0, LengthUnit.FEET)
                        .divide(
                                new Quantity<>(2.0, LengthUnit.FEET)
                        );

        assertEquals(
                3.5,
                result
        );
    }

    @Test
    void testImplicitTargetUnit_AddSubtract() {

        Quantity<LengthUnit> result =
                new Quantity<>(12.0, LengthUnit.INCHES)
                        .add(
                                new Quantity<>(1.0, LengthUnit.FEET)
                        );

        assertEquals(
                LengthUnit.INCHES,
                result.getUnit()
        );
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {

        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(12.0, LengthUnit.INCHES),
                                LengthUnit.YARDS
                        );

        assertEquals(
                LengthUnit.YARDS,
                result.getUnit()
        );
    }

    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {

        Quantity<LengthUnit> original =
                new Quantity<>(1.0, LengthUnit.FEET);

        original.add(
                new Quantity<>(12.0, LengthUnit.INCHES)
        );

        assertEquals(
                1.0,
                original.getValue()
        );
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.subtract(
                new Quantity<>(5.0, LengthUnit.FEET)
        );

        assertEquals(
                10.0,
                original.getValue()
        );
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {

        Quantity<LengthUnit> original =
                new Quantity<>(10.0, LengthUnit.FEET);

        original.divide(
                new Quantity<>(2.0, LengthUnit.FEET)
        );

        assertEquals(
                10.0,
                original.getValue()
        );
    }

    @Test
    void testAllOperations_AcrossAllCategories() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> volume =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        assertNotNull(
                length.add(
                        new Quantity<>(2.0, LengthUnit.FEET)
                )
        );

        assertNotNull(
                weight.subtract(
                        new Quantity<>(2.0, WeightUnit.KILOGRAM)
                )
        );

        assertEquals(
                2.0,
                volume.divide(
                        new Quantity<>(5.0, VolumeUnit.LITRE)
                )
        );
    }

    @Test
    void testArithmetic_Chain_Operations() {

        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .add(
                                new Quantity<>(2.0, LengthUnit.FEET)
                        )
                        .subtract(
                                new Quantity<>(4.0, LengthUnit.FEET)
                        )
                        .divide(
                                new Quantity<>(2.0, LengthUnit.FEET)
                        );

        assertEquals(
                4.0,
                result
        );
    }

    @Test
    void testErrorMessage_Consistency_Across_Operations() {

        Quantity<LengthUnit> q =
                new Quantity<>(10.0, LengthUnit.FEET);

        Exception addException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> q.add(null)
                );

        Exception subtractException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> q.subtract(null)
                );

        Exception divideException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> q.divide(null)
                );

        assertEquals(
                addException.getMessage(),
                subtractException.getMessage()
        );

        assertEquals(
                addException.getMessage(),
                divideException.getMessage()
        );
    }

    @Test
    void testRefactoring_NoBehaviorChange_LargeDataset() {

        for (int i = 1; i <= 1000; i++) {

            Quantity<LengthUnit> q1 =
                    new Quantity<>(i, LengthUnit.FEET);

            Quantity<LengthUnit> q2 =
                    new Quantity<>(i, LengthUnit.FEET);

            assertEquals(
                    i * 2,
                    q1.add(q2).getValue()
            );
        }
    }

}