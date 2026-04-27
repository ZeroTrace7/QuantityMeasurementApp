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

    @Test
    void testAdditionSameUnitFeetPlusFeet() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(2.0, LengthUnit.FEET),
            LengthUnit.FEET
        );
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionSameUnitInchesPlusInches() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(6.0, LengthUnit.INCHES),
            new QuantityLength(6.0, LengthUnit.INCHES),
            LengthUnit.INCHES
        );
        assertEquals(12.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitFeetPlusInches() {
        QuantityLength sum = new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitInchesPlusFeet() {
        QuantityLength sum = new QuantityLength(12.0, LengthUnit.INCHES).add(new QuantityLength(1.0, LengthUnit.FEET));
        assertEquals(24.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitYardsPlusFeet() {
        QuantityLength sum = new QuantityLength(1.0, LengthUnit.YARDS).add(new QuantityLength(3.0, LengthUnit.FEET));
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitCentimetersPlusInches() {
        QuantityLength sum = new QuantityLength(2.54, LengthUnit.CENTIMETERS).add(new QuantityLength(1.0, LengthUnit.INCHES));
        assertEquals(5.08, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, sum.getUnit());
    }

    @Test
    void testAdditionCommutativity() {
        QuantityLength firstOrder = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(12.0, LengthUnit.INCHES),
            LengthUnit.FEET
        );
        QuantityLength secondOrder = QuantityLength.add(
            new QuantityLength(12.0, LengthUnit.INCHES),
            new QuantityLength(1.0, LengthUnit.FEET),
            LengthUnit.FEET
        );
        assertEquals(firstOrder.getValue(), secondOrder.getValue(), EPSILON);
        assertEquals(firstOrder.getUnit(), secondOrder.getUnit());
        assertTrue(firstOrder.equals(secondOrder));
    }

    @Test
    void testAdditionWithZero() {
        QuantityLength sum = new QuantityLength(5.0, LengthUnit.FEET).add(new QuantityLength(0.0, LengthUnit.INCHES));
        assertEquals(5.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionNegativeValues() {
        QuantityLength sum = new QuantityLength(5.0, LengthUnit.FEET).add(new QuantityLength(-2.0, LengthUnit.FEET));
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionNullSecondOperandThrows() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, LengthUnit.FEET).add(null));
        assertThrows(
            IllegalArgumentException.class,
            () -> QuantityLength.add(new QuantityLength(1.0, LengthUnit.FEET), null, LengthUnit.FEET)
        );
    }

    @Test
    void testAdditionLargeValues() {
        QuantityLength sum = new QuantityLength(1.0e6, LengthUnit.FEET).add(new QuantityLength(1.0e6, LengthUnit.FEET));
        assertEquals(2.0e6, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionSmallValues() {
        QuantityLength sum = new QuantityLength(0.001, LengthUnit.FEET).add(new QuantityLength(0.002, LengthUnit.FEET));
        assertEquals(0.003, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionInvalidTargetUnitThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> QuantityLength.add(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET), null)
        );
    }

    @Test
    void testAdditionExplicitTargetUnitFeet() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(12.0, LengthUnit.INCHES),
            LengthUnit.FEET
        );
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitInches() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(12.0, LengthUnit.INCHES),
            LengthUnit.INCHES
        );
        assertEquals(24.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitYards() {
        QuantityLength sum = new QuantityLength(1.0, LengthUnit.FEET)
            .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        assertEquals(2.0 / 3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitCentimeters() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.INCHES),
            new QuantityLength(1.0, LengthUnit.INCHES),
            LengthUnit.CENTIMETERS
        );
        assertEquals(5.08, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitSameAsFirstOperand() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(2.0, LengthUnit.YARDS),
            new QuantityLength(3.0, LengthUnit.FEET),
            LengthUnit.YARDS
        );
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitSameAsSecondOperand() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(2.0, LengthUnit.YARDS),
            new QuantityLength(3.0, LengthUnit.FEET),
            LengthUnit.FEET
        );
        assertEquals(9.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitCommutativity() {
        QuantityLength firstOrder = QuantityLength.add(
            new QuantityLength(1.0, LengthUnit.FEET),
            new QuantityLength(12.0, LengthUnit.INCHES),
            LengthUnit.YARDS
        );
        QuantityLength secondOrder = QuantityLength.add(
            new QuantityLength(12.0, LengthUnit.INCHES),
            new QuantityLength(1.0, LengthUnit.FEET),
            LengthUnit.YARDS
        );
        assertEquals(firstOrder.getValue(), secondOrder.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, firstOrder.getUnit());
        assertEquals(firstOrder.getUnit(), secondOrder.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitWithZero() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(5.0, LengthUnit.FEET),
            new QuantityLength(0.0, LengthUnit.INCHES),
            LengthUnit.YARDS
        );
        assertEquals(5.0 / 3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitNegativeValues() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(5.0, LengthUnit.FEET),
            new QuantityLength(-2.0, LengthUnit.FEET),
            LengthUnit.INCHES
        );
        assertEquals(36.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitNullTargetUnit() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new QuantityLength(1.0, LengthUnit.FEET).add(new QuantityLength(12.0, LengthUnit.INCHES), null)
        );
    }

    @Test
    void testAdditionExplicitTargetUnitLargeToSmallScale() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(1000.0, LengthUnit.FEET),
            new QuantityLength(500.0, LengthUnit.FEET),
            LengthUnit.INCHES
        );
        assertEquals(18000.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitSmallToLargeScale() {
        QuantityLength sum = QuantityLength.add(
            new QuantityLength(12.0, LengthUnit.INCHES),
            new QuantityLength(12.0, LengthUnit.INCHES),
            LengthUnit.YARDS
        );
        assertEquals(2.0 / 3.0, sum.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }
}
