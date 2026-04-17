package com.zerotrace7.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityMeasurementAppTest {

    @Test
    void givenSameFeetValueWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(first.equals(second), "Expected equal feet values to be equal");
    }

    @Test
    void givenDifferentFeetValueWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Feet first = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet second = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(first.equals(second), "Expected different feet values to not be equal");
    }

    @Test
    void givenNullWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null), "Expected feet value to not be equal to null");
    }

    @Test
    void givenDifferentObjectTypeWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals("1.0"), "Expected feet value to not be equal to a non-feet object");
    }

    @Test
    void givenSameReferenceWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet), "Expected feet value to be equal to itself");
    }
}
