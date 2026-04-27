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
}
