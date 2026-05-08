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
}