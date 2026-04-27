package com.zerotrace7.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityWeightTest {
    private static final double EPSILON = 1e-5;

    @Test
    void testWeightUnitEnumKilogramConstant() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), EPSILON);
    }

    @Test
    void testWeightUnitEnumGramConstant() {
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), EPSILON);
    }

    @Test
    void testWeightUnitEnumPoundConstant() {
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnitGramToKilogram() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnitPoundToKilogram() {
        assertEquals(0.907184, WeightUnit.POUND.convertToBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnitKilogramToGram() {
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnitKilogramToPound() {
        assertEquals(2.2046244201837775, WeightUnit.POUND.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testEqualityKilogramToKilogramSameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEqualityKilogramToKilogramDifferentValue() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEqualityKilogramToGramEquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEqualityGramToKilogramEquivalentValue() {
        assertTrue(new QuantityWeight(1000.0, WeightUnit.GRAM).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEqualityKilogramToPoundEquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testEqualityGramToPoundEquivalentValue() {
        assertTrue(new QuantityWeight(453.592, WeightUnit.GRAM).equals(new QuantityWeight(1.0, WeightUnit.POUND)));
    }

    @Test
    void testEqualityWeightVsLengthIncompatible() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEqualityNullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    void testEqualitySameReference() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(weight.equals(weight));
    }

    @Test
    void testEqualityTransitiveProperty() {
        QuantityWeight first = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight second = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight third = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertTrue(first.equals(second));
        assertTrue(second.equals(third));
        assertTrue(first.equals(third));
    }

    @Test
    void testEqualityZeroValue() {
        assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    void testEqualityNegativeWeight() {
        assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEqualityLargeWeightValue() {
        assertTrue(new QuantityWeight(1000000.0, WeightUnit.GRAM).equals(new QuantityWeight(1000.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEqualitySmallWeightValue() {
        assertTrue(new QuantityWeight(0.001, WeightUnit.KILOGRAM).equals(new QuantityWeight(1.0, WeightUnit.GRAM)));
    }

    @Test
    void testEqualityNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
        assertThrows(IllegalArgumentException.class, () -> QuantityWeight.convert(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM, WeightUnit.GRAM));
    }

    @Test
    void testConversionPoundToKilogram() {
        QuantityWeight converted = new QuantityWeight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertEquals(0.999998, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, converted.getUnit());
    }

    @Test
    void testConversionKilogramToPound() {
        QuantityWeight converted = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);
        assertEquals(2.2046244201837775, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.POUND, converted.getUnit());
    }

    @Test
    void testConversionSameUnit() {
        QuantityWeight converted = new QuantityWeight(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, converted.getUnit());
    }

    @Test
    void testConversionZeroValue() {
        QuantityWeight converted = new QuantityWeight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(0.0, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
    }

    @Test
    void testConversionNegativeValue() {
        QuantityWeight converted = new QuantityWeight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0, converted.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, converted.getUnit());
    }

    @Test
    void testConversionRoundTrip() {
        QuantityWeight roundTrip = new QuantityWeight(1.5, WeightUnit.KILOGRAM)
            .convertTo(WeightUnit.GRAM)
            .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, roundTrip.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, roundTrip.getUnit());
    }

    @Test
    void testAdditionSameUnitKilogramPlusKilogram() {
        QuantityWeight sum = new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitKilogramPlusGram() {
        QuantityWeight sum = new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionCrossUnitPoundPlusKilogram() {
        QuantityWeight sum = new QuantityWeight(2.20462, WeightUnit.POUND).add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));
        assertEquals(4.40924, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.POUND, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitGram() {
        QuantityWeight sum = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
            .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitPound() {
        QuantityWeight sum = new QuantityWeight(1.0, WeightUnit.POUND)
            .add(new QuantityWeight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
        assertEquals(2.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.POUND, sum.getUnit());
    }

    @Test
    void testAdditionExplicitTargetUnitKilogram() {
        QuantityWeight sum = new QuantityWeight(2.0, WeightUnit.KILOGRAM)
            .add(new QuantityWeight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM);
        assertEquals(3.814368, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionCommutativity() {
        QuantityWeight firstOrder = QuantityWeight.add(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            new QuantityWeight(1000.0, WeightUnit.GRAM),
            WeightUnit.KILOGRAM
        );
        QuantityWeight secondOrder = QuantityWeight.add(
            new QuantityWeight(1000.0, WeightUnit.GRAM),
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            WeightUnit.KILOGRAM
        );
        assertEquals(firstOrder.getValue(), secondOrder.getValue(), EPSILON);
        assertEquals(firstOrder.getUnit(), secondOrder.getUnit());
    }

    @Test
    void testAdditionWithZero() {
        QuantityWeight sum = new QuantityWeight(5.0, WeightUnit.KILOGRAM).add(new QuantityWeight(0.0, WeightUnit.GRAM));
        assertEquals(5.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionNegativeValues() {
        QuantityWeight sum = new QuantityWeight(5.0, WeightUnit.KILOGRAM).add(new QuantityWeight(-2000.0, WeightUnit.GRAM));
        assertEquals(3.0, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionLargeValues() {
        QuantityWeight sum = new QuantityWeight(1.0e6, WeightUnit.KILOGRAM).add(new QuantityWeight(1.0e6, WeightUnit.KILOGRAM));
        assertEquals(2.0e6, sum.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    void testAdditionNullHandling() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(null));
        assertThrows(
            IllegalArgumentException.class,
            () -> QuantityWeight.add(new QuantityWeight(1.0, WeightUnit.KILOGRAM), null, WeightUnit.KILOGRAM)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1.0, WeightUnit.GRAM), null)
        );
    }
}
