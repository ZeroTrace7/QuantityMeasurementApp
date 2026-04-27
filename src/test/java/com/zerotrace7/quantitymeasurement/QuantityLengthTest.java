package com.zerotrace7.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityLengthTest {
    private static final double EPSILON = 1e-6;

    @Test
    void testConversionFeetToInches() {
        assertEquals(12.0, QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversionInchesToFeet() {
        assertEquals(2.0, QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversionYardsToInches() {
        assertEquals(36.0, QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversionInchesToYards() {
        assertEquals(2.0, QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversionCentimetersToInches() {
        assertEquals(1.0, QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversionFeetToYards() {
        assertEquals(2.0, QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversionRoundTripPreservesValue() {
        double converted = QuantityLength.convert(5.5, LengthUnit.YARDS, LengthUnit.CENTIMETERS);
        double roundTrip = QuantityLength.convert(converted, LengthUnit.CENTIMETERS, LengthUnit.YARDS);
        assertEquals(5.5, roundTrip, EPSILON);
    }

    @Test
    void testConversionZeroValue() {
        assertEquals(0.0, QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversionNegativeValue() {
        assertEquals(-12.0, QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testSameUnitConversionReturnsOriginalValue() {
        assertEquals(5.0, QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testInstanceConversionReturnsNewQuantity() {
        QuantityLength converted = new QuantityLength(3.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
        assertEquals(36.0, converted.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    void testEqualityAcrossUnits() {
        assertTrue(new QuantityLength(3.0, LengthUnit.FEET).equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }

    @Test
    void testConversionInvalidUnitThrows() {
        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }

    @Test
    void testConversionNaNOrInfiniteThrows() {
        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
        assertThrows(IllegalArgumentException.class, () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NEGATIVE_INFINITY, LengthUnit.FEET));
    }
}
