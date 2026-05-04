package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.QuantityMeasurement.Feet;

public class QuantityMeasurementTest {

    @Test
    public void testSameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void testDifferentValue() {
        QuantityMeasurement.Feet f1 = new Feet(1.0);
        QuantityMeasurement.Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testNull() {
        Feet f1 = new QuantityMeasurement.Feet(1.0);

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
}