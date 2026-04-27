package com.zerotrace7.quantitymeasurement;

/**
 * Supported weight units with conversion factors stored relative to kilograms.
 * The enum owns all weight-specific conversion logic.
 */
public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value expressed in this unit to the shared base unit of kilograms.
     *
     * @param value value in this unit
     * @return equivalent value in kilograms
     */
    public double convertToBaseUnit(double value) {
        validateFinite(value);
        return value * conversionFactor;
    }

    /**
     * Converts a value expressed in kilograms to this unit.
     *
     * @param baseValue value in kilograms
     * @return equivalent value in this unit
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
