package com.zerotrace7.quantitymeasurement;

/**
 * Supported length units with conversion factors stored relative to feet.
 * The factors are immutable and used as the centralized source of truth
 * for all unit-to-unit conversions.
 */
public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value expressed in this unit to the shared base unit of feet.
     *
     * @param value value in this unit
     * @return equivalent value in feet
     * @throws IllegalArgumentException when the value is not finite
     */
    public double convertToBaseUnit(double value) {
        validateFinite(value);
        return value * conversionFactor;
    }

    /**
     * Converts a value expressed in the shared base unit of feet to this unit.
     *
     * @param baseValue value in feet
     * @return equivalent value in this unit
     * @throws IllegalArgumentException when the value is not finite
     */
    public double convertFromBaseUnit(double baseValue) {
        validateFinite(baseValue);
        return baseValue / conversionFactor;
    }

    private static void validateFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("value must be finite");
        }
    }
}
