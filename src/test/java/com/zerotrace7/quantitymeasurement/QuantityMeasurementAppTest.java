package com.zerotrace7.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityMeasurementAppTest {

    @Test
    void givenSameFeetValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.FEET,
                        1.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected identical feet measurements to be equal");
    }

    @Test
    void givenSameInchValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.INCH,
                        1.0, QuantityMeasurementApp.LengthUnit.INCH),
                "Expected identical inch measurements to be equal");
    }

    @Test
    void givenFeetAndEquivalentInchesWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.FEET,
                        12.0, QuantityMeasurementApp.LengthUnit.INCH),
                "Expected 1 foot and 12 inches to be equal");
    }

    @Test
    void givenInchesAndEquivalentFeetWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(12.0, QuantityMeasurementApp.LengthUnit.INCH,
                        1.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected 12 inches and 1 foot to be equal");
    }

    @Test
    void givenDifferentFeetValuesWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.FEET,
                        2.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected different feet measurements to not be equal");
    }

    @Test
    void givenDifferentInchValuesWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.INCH,
                        2.0, QuantityMeasurementApp.LengthUnit.INCH),
                "Expected different inch measurements to not be equal");
    }

    @Test
    void givenNullUnitWhenConstructedThenThrowsException() {
        assertThrows(NullPointerException.class,
                () -> new QuantityMeasurementApp.Quantity(1.0, null),
                "Expected a null unit to be rejected");
    }

    @Test
    void givenSameReferenceWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(quantity.equals(quantity), "Expected quantity to be equal to itself");
    }

    @Test
    void givenNullWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(quantity.equals(null), "Expected quantity to not be equal to null");
    }

    @Test
    void givenDifferentTypeWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(quantity.equals("1.0 feet"), "Expected quantity to not match a different object type");
    }
}
