package com.zerotrace7.quantitymeasurement;

import java.util.Objects;

/**
 * Immutable value object representing a weight measurement and exposing
 * comparison, conversion, and arithmetic operations across supported units.
 */
public final class QuantityWeight {
    private static final double EPSILON = 1e-5;

    private final double value;
    private final WeightUnit unit;

    /**
     * Creates a validated quantity weight instance.
     *
     * @param value numeric measurement value
     * @param unit measurement unit
     */
    public QuantityWeight(double value, WeightUnit unit) {
        validateValue(value);
        this.unit = validateUnit(unit, "unit");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Converts a raw weight value between supported units.
     *
     * @param value numeric source value
     * @param source source unit
     * @param target target unit
     * @return converted numeric value in the target unit
     */
    public static double convert(double value, WeightUnit source, WeightUnit target) {
        validateValue(value);
        WeightUnit validatedSource = validateUnit(source, "sourceUnit");
        WeightUnit validatedTarget = validateUnit(target, "targetUnit");
        double baseValueInKilograms = validatedSource.convertToBaseUnit(value);
        return validatedTarget.convertFromBaseUnit(baseValueInKilograms);
    }

    /**
     * Converts this quantity into the target unit and returns a new immutable value.
     *
     * @param targetUnit target unit for conversion
     * @return new quantity represented in the target unit
     */
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        return new QuantityWeight(convert(value, unit, targetUnit), targetUnit);
    }

    /**
     * Adds two quantity weights and returns the result in the supplied target unit.
     *
     * @param first first quantity operand
     * @param second second quantity operand
     * @param targetUnit unit for the returned result
     * @return new quantity containing the sum in the target unit
     */
    public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
        QuantityWeight validatedFirst = validateQuantity(first, "firstWeight");
        QuantityWeight validatedSecond = validateQuantity(second, "secondWeight");
        WeightUnit validatedTargetUnit = validateUnit(targetUnit, "targetUnit");
        return addInTargetUnit(validatedFirst, validatedSecond, validatedTargetUnit);
    }

    /**
     * Adds two raw weight values and returns the result in the supplied target unit.
     *
     * @param firstValue first numeric value
     * @param firstUnit first unit
     * @param secondValue second numeric value
     * @param secondUnit second unit
     * @param targetUnit unit for the returned result
     * @return new quantity containing the sum in the target unit
     */
    public static QuantityWeight add(
        double firstValue,
        WeightUnit firstUnit,
        double secondValue,
        WeightUnit secondUnit,
        WeightUnit targetUnit
    ) {
        return add(
            new QuantityWeight(firstValue, firstUnit),
            new QuantityWeight(secondValue, secondUnit),
            targetUnit
        );
    }

    /**
     * Adds another quantity to this one and returns the sum in this instance's unit.
     *
     * @param other second quantity operand
     * @return new quantity containing the sum in this instance's unit
     */
    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, unit);
    }

    /**
     * Adds another quantity to this one and returns the sum in the specified target unit.
     *
     * @param other second quantity operand
     * @param targetUnit unit for the returned result
     * @return new quantity containing the sum in the target unit
     */
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        return add(this, other, targetUnit);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        QuantityWeight that = (QuantityWeight) other;
        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long roundedBaseValue = Math.round(toBaseUnit() / EPSILON);
        return Objects.hash(roundedBaseValue);
    }

    @Override
    public String toString() {
        return String.format("QuantityWeight{value=%.6f, unit=%s}", value, unit);
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private static void validateValue(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be finite");
        }
    }

    private static WeightUnit validateUnit(WeightUnit unit, String unitName) {
        if (unit == null) {
            throw new IllegalArgumentException(unitName + " must not be null");
        }
        return unit;
    }

    private static QuantityWeight validateQuantity(QuantityWeight quantityWeight, String name) {
        if (quantityWeight == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        return quantityWeight;
    }

    private static QuantityWeight addInTargetUnit(
        QuantityWeight firstWeight,
        QuantityWeight secondWeight,
        WeightUnit targetUnit
    ) {
        double sumInBaseUnit = firstWeight.toBaseUnit() + secondWeight.toBaseUnit();
        double valueInTargetUnit = targetUnit.convertFromBaseUnit(sumInBaseUnit);
        return new QuantityWeight(valueInTargetUnit, targetUnit);
    }
}
