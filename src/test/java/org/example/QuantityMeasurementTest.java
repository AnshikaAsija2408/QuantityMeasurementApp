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
        Length f1 = new Length(1.0, Length.LengthUnit.FEET);
        Length f2 = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(f1.equals(f2));
    }

    //Inches equality (same value)
    @Test
    public void testInchesEquality() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertTrue(i1.equals(i2));
    }

    //Feet ↔ Inches comparison
    @Test
    public void testFeetInchesComparison() {
        Length f = new Length(1.0, Length.LengthUnit.FEET);
        Length i = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(f.equals(i));
    }

    //Feet inequality
    @Test
    public void testFeetInequality() {
        Length f1 = new Length(1.0, Length.LengthUnit.FEET);
        Length f2 = new Length(2.0, Length.LengthUnit.FEET);

        assertFalse(f1.equals(f2));
    }

    //Inches inequality
    @Test
    public void testInchesInequality() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(2.0, Length.LengthUnit.INCHES);

        assertFalse(i1.equals(i2));
    }

    //Cross unit inequality (not equal)1
    @Test
    public void testCrossUnitInequality() {
        Length f = new Length(1.0, Length.LengthUnit.FEET);
        Length i = new Length(10.0, Length.LengthUnit.INCHES);

        assertFalse(f.equals(i));
    }

    //Same reference test
    @Test
    public void testSameReference() {
        Length f = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(f.equals(f));
    }

    @Test
    void testEquality_YardToYard_SameValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.YARDS),
                new Length(1.0, Length.LengthUnit.YARDS));
    }

    // 2
    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertNotEquals(new Length(1.0, Length.LengthUnit.YARDS),
                new Length(2.0, Length.LengthUnit.YARDS));
    }

    // 3
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET));
    }

    // 4
    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertEquals(new Length(3.0, Length.LengthUnit.FEET),
                new Length(1.0, Length.LengthUnit.YARDS));
    }

    // 5
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.YARDS),
                new Length(36.0, Length.LengthUnit.INCHES));
    }

    // 6
    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertEquals(new Length(36.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.YARDS));
    }

    // 7
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertNotEquals(new Length(1.0, Length.LengthUnit.YARDS),
                new Length(2.0, Length.LengthUnit.FEET));
    }

    // 8
    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.CENTIMETERS),
                new Length(0.393701, Length.LengthUnit.INCHES));
    }

    // 9
    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertNotEquals(new Length(1.0, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.FEET));
    }

    // 10 (transitive)
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

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
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertEquals(yard, yard);
    }

    // 13 (compare with null)
    @Test
    void testEquality_YardNullComparison() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
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
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertEquals(cm, cm);
    }

    // 16 (cm null comparison)
    @Test
    void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        assertNotEquals(cm, null);
    }

    // 17 (complex scenario)
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        assertEquals(new Length(2.0, Length.LengthUnit.YARDS),
                new Length(6.0, Length.LengthUnit.FEET));

        assertEquals(new Length(6.0, Length.LengthUnit.FEET),
                new Length(72.0, Length.LengthUnit.INCHES));
    }

    // ========================= UC5 TEST CASES =========================

    @Test
    void testConversion_FeetToInches() {
        Length result = QuantityMeasurement.demonstrateLengthConversion(1.0,Length.LengthUnit.FEET,Length.LengthUnit.INCHES  );
        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result
        );
    }

    // 2
    @Test
    void testConversion_InchesToFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET
                );

        assertEquals(
                new Length(2.0, Length.LengthUnit.FEET),
                result
        );
    }

    // 3
    @Test
    void testConversion_YardsToInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        1.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.INCHES
                );

        assertEquals(
                new Length(36.0, Length.LengthUnit.INCHES),
                result
        );
    }

    // 4
    @Test
    void testConversion_InchesToYards() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        72.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.YARDS
                );

        assertEquals(
                new Length(2.0, Length.LengthUnit.YARDS),
                result
        );
    }

    // 5
    @Test
    void testConversion_CentimetersToInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES
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
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.YARDS
                );

        assertEquals(
                new Length(2.0, Length.LengthUnit.YARDS),
                result
        );
    }

    // 7
    @Test
    void testConversion_RoundTrip_PreservesValue() {

        Length firstConversion =
                QuantityMeasurement.demonstrateLengthConversion(
                        5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                );

        Length secondConversion =
                QuantityMeasurement.demonstrateLengthConversion(
                        firstConversion,
                        Length.LengthUnit.FEET
                );

        assertEquals(
                5.0,
                secondConversion.getValue(),
                0.0001
        );
    }

    // 8
    @Test
    void testConversion_ZeroValue() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        0.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                );

        assertEquals(
                new Length(0.0, Length.LengthUnit.INCHES),
                result
        );
    }

    // 9
    @Test
    void testConversion_NegativeValue() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        -1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                );

        assertEquals(
                new Length(-12.0, Length.LengthUnit.INCHES),
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
                        Length.LengthUnit.INCHES
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
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthConversion(
                        Double.POSITIVE_INFINITY,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES
                )
        );
    }

    // 12
    @Test
    void testConversion_PrecisionTolerance() {

        Length result =
                QuantityMeasurement.demonstrateLengthConversion(
                        2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES
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
                        new Length(1.0, Length.LengthUnit.FEET),
                        new Length(2.0, Length.LengthUnit.FEET)
                );

        assertEquals(new Length(3.0, Length.LengthUnit.FEET),result);
    }

    // 2
    @Test
    public void testAddition_SameUnit_InchPlusInch() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(6.0, Length.LengthUnit.INCHES),
                        new Length(6.0, Length.LengthUnit.INCHES)
                );

        assertEquals(
                new Length(12.0, Length.LengthUnit.INCHES),result);
    }

    // 3
    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.FEET),
                        new Length(12.0, Length.LengthUnit.INCHES)
                );

        assertEquals(
                new Length(2.0, Length.LengthUnit.FEET),result);
    }

    // 4
    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(12.0, Length.LengthUnit.INCHES),
                        new Length(1.0, Length.LengthUnit.FEET)
                );

        assertEquals(
                new Length(24.0, Length.LengthUnit.INCHES),result);
    }

    // 5
    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.YARDS),
                        new Length(3.0, Length.LengthUnit.FEET)
                );

        assertEquals(
                new Length(2.0, Length.LengthUnit.YARDS),result);
    }

    // 6
    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(2.54, Length.LengthUnit.CENTIMETERS),
                        new Length(1.0, Length.LengthUnit.INCHES)
                );

        assertEquals(5.08,result.getValue(),0.01);
    }

    // 7
    @Test
    public void testAddition_Commutativity() {

        Length result1 =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.FEET),
                        new Length(12.0, Length.LengthUnit.INCHES)
                );

        Length result2 =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(12.0, Length.LengthUnit.INCHES),
                        new Length(1.0, Length.LengthUnit.FEET)
                );

        assertEquals(result1.convertTo(Length.LengthUnit.INCHES),result2);
    }

    // 8
    @Test
    public void testAddition_WithZero() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(5.0, Length.LengthUnit.FEET),
                        new Length(0.0, Length.LengthUnit.INCHES)
                );

        assertEquals(new Length(5.0, Length.LengthUnit.FEET),result);
    }

    // 9
    @Test
    public void testAddition_NegativeValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(5.0, Length.LengthUnit.FEET),
                        new Length(-2.0, Length.LengthUnit.FEET)
                );

        assertEquals(new Length(3.0, Length.LengthUnit.FEET),result);
    }

    // 10
    @Test
    public void testAddition_NullSecondOperand() {

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.FEET),
                        null
                )
        );
    }

    // 11
    @Test
    public void testAddition_LargeValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(1e6, Length.LengthUnit.FEET),
                        new Length(1e6, Length.LengthUnit.FEET)
                );

        assertEquals(new Length(2e6, Length.LengthUnit.FEET),result);
    }

    // 12
    @Test
    public void testAddition_SmallValues() {

        Length result =
                QuantityMeasurement.demonstrateLengthAddition(
                        new Length(0.001, Length.LengthUnit.FEET),
                        new Length(0.002, Length.LengthUnit.FEET)
                );

        assertEquals(0.003,result.getValue(),0.0001);
    }
}