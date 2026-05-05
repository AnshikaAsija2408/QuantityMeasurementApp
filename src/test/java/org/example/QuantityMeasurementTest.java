package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.QuantityMeasurement.Feet;
import org.example.QuantityMeasurement.Inches;

public class QuantityMeasurementTest {

    @Test
    public void testSameValue() {
        Feet f1 = new Feet(1.0);    //f1 is not the object. it si the reference
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void testDifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testNull() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    public void testSameReference() {
        Feet f1 = new Feet(1.0);
        assertTrue(f1.equals(f1));
    }

    //  DIFFERENT CLASS
    @Test
    public void testDifferentClass() {
        Feet f1 = new Feet(1.0);
        String str = "1.0";
        assertFalse(f1.equals(str));
    }

    @Test
    public void testInchesSameValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(1.0);
        assertTrue(i1.equals(i2));
    }

    @Test
    public void testInchesDifferentValue() {
        Inches i1 = new Inches(1.0);
        Inches i2 = new Inches(2.0);
        assertFalse(i1.equals(i2));
    }

    @Test
    public void testInchesNull() {
        Inches i1 = new Inches(1.0);
        assertFalse(i1.equals(null));
    }

    @Test
    public void testInchesDifferentClass() {
        Inches i1 = new Inches(1.0);
        Feet f1 = new Feet(1.0);
        assertFalse(i1.equals(f1));
    }

    @Test
    public void testInchesSameReference() {
        Inches i1 = new Inches(1.0);
        assertTrue(i1.equals(i1));
    }
}