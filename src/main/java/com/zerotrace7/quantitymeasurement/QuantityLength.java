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
}
