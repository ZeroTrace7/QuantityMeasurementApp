package com.zerotrace7.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityMeasurementAppTest {

    @Test
    void givenSameFeetValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areFeetEqual(1.0, 1.0),
                "Expected equal feet values to be equal");
    }

    @Test
    void givenDifferentFeetValueWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areFeetEqual(1.0, 2.0),
                "Expected different feet values to not be equal");
    }

    @Test
    void givenSameFeetReferenceWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet), "Expected feet value to be equal to itself");
    }

    @Test
    void givenNullFeetWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null), "Expected feet value to not be equal to null");
    }

    @Test
    void givenDifferentFeetTypeWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals("1.0"), "Expected feet value to not match a different object type");
    }

    @Test
    void givenSameInchesValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areInchesEqual(1.0, 1.0),
                "Expected equal inches values to be equal");
    }

    @Test
    void givenDifferentInchesValueWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areInchesEqual(1.0, 2.0),
                "Expected different inches values to not be equal");
    }

    @Test
    void givenSameInchesReferenceWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(inches.equals(inches), "Expected inches value to be equal to itself");
    }

    @Test
    void givenNullInchesWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(inches.equals(null), "Expected inches value to not be equal to null");
    }

    @Test
    void givenDifferentInchesTypeWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(inches.equals("1.0"), "Expected inches value to not match a different object type");
    }
}
