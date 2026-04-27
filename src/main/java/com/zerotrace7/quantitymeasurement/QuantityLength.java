package com.zerotrace7.quantitymeasurement;

import java.util.Objects;

/**
 * Immutable value object representing a length measurement and exposing
 * conversion and equality operations across supported units.
 */
public final class QuantityLength {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    /**
     * Creates a validated quantity length instance.
     *
     * @param value numeric measurement value
     * @param unit measurement unit
     */
    public QuantityLength(double value, LengthUnit unit) {
        validateValue(value);
        this.unit = validateUnit(unit, "unit");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Converts a raw value between supported units.
     *
     * @param value numeric source value
     * @param source source unit
     * @param target target unit
     * @return converted numeric value in the target unit
     * @throws IllegalArgumentException when the value is not finite or either unit is null
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validateValue(value);
        LengthUnit validatedSource = validateUnit(source, "sourceUnit");
        LengthUnit validatedTarget = validateUnit(target, "targetUnit");
        double baseValueInFeet = toBaseUnit(value, validatedSource);
        return fromBaseUnit(baseValueInFeet, validatedTarget);
    }

    /**
     * Converts this quantity into the target unit and returns a new immutable value.
     *
     * @param targetUnit target unit for conversion
     * @return new quantity represented in the target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        return new QuantityLength(convert(value, unit, targetUnit), targetUnit);
    }

    /**
     * Adds two quantity lengths and returns the result in the supplied target unit.
     *
     * @param first first quantity operand
     * @param second second quantity operand
     * @param targetUnit unit for the returned result
     * @return new quantity containing the sum in the target unit
     * @throws IllegalArgumentException when either operand is null or the target unit is null
     */
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        QuantityLength validatedFirst = validateQuantity(first, "firstLength");
        QuantityLength validatedSecond = validateQuantity(second, "secondLength");
        LengthUnit validatedTargetUnit = validateUnit(targetUnit, "targetUnit");

        double sumInBaseUnit = validatedFirst.toBaseUnit() + validatedSecond.toBaseUnit();
        double valueInTargetUnit = fromBaseUnit(sumInBaseUnit, validatedTargetUnit);
        return new QuantityLength(valueInTargetUnit, validatedTargetUnit);
    }

    /**
     * Adds two raw length values and returns the result in the supplied target unit.
     *
     * @param firstValue first numeric value
     * @param firstUnit first unit
     * @param secondValue second numeric value
     * @param secondUnit second unit
     * @param targetUnit unit for the returned result
     * @return new quantity containing the sum in the target unit
     */
    public static QuantityLength add(
        double firstValue,
        LengthUnit firstUnit,
        double secondValue,
        LengthUnit secondUnit,
        LengthUnit targetUnit
    ) {
        return add(
            new QuantityLength(firstValue, firstUnit),
            new QuantityLength(secondValue, secondUnit),
            targetUnit
        );
    }

    /**
     * Adds another quantity to this one and returns the sum in this instance's unit.
     *
     * @param other second quantity operand
     * @return new quantity containing the sum in this instance's unit
     */
    public QuantityLength add(QuantityLength other) {
        return add(this, other, unit);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QuantityLength)) {
            return false;
        }
        QuantityLength that = (QuantityLength) other;
        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        long roundedBaseValue = Math.round(toBaseUnit() / EPSILON);
        return Objects.hash(roundedBaseValue);
    }

    @Override
    public String toString() {
        return String.format("QuantityLength{value=%.6f, unit=%s}", value, unit);
    }

    private double toBaseUnit() {
        return toBaseUnit(value, unit);
    }

    private static double toBaseUnit(double value, LengthUnit unit) {
        return value * unit.getConversionFactor();
    }

    private static double fromBaseUnit(double baseValue, LengthUnit unit) {
        return baseValue / unit.getConversionFactor();
    }

    private static void validateValue(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be finite");
        }
    }

    private static LengthUnit validateUnit(LengthUnit unit, String unitName) {
        if (unit == null) {
            throw new IllegalArgumentException(unitName + " must not be null");
        }
        return unit;
    }

    private static QuantityLength validateQuantity(QuantityLength quantityLength, String name) {
        if (quantityLength == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        return quantityLength;
    }
}
