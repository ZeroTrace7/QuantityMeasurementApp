package com.zerotrace7.quantitymeasurement;

/**
 * Entry point and demonstration API for quantity length comparison and conversion use cases.
 */
public class QuantityMeasurementApp {
    public static void main(String[] args) {
        System.out.println("Quantity Measurement Application");

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(new QuantityLength(36.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS);
    }

    /**
     * Demonstrates converting a raw numeric value from one unit to another.
     */
    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        QuantityLength sourceLength = new QuantityLength(value, fromUnit);
        QuantityLength convertedLength = sourceLength.convertTo(toUnit);
        System.out.println(sourceLength + " -> " + convertedLength);
    }

    /**
     * Demonstrates converting an existing quantity length instance to a target unit.
     */
    public static void demonstrateLengthConversion(QuantityLength quantityLength, LengthUnit targetUnit) {
        QuantityLength convertedLength = quantityLength.convertTo(targetUnit);
        System.out.println(quantityLength + " -> " + convertedLength);
    }

    /**
     * Demonstrates equality comparison between two raw length values.
     */
    public static void demonstrateLengthComparison(
        double firstValue,
        LengthUnit firstUnit,
        double secondValue,
        LengthUnit secondUnit
    ) {
        QuantityLength firstLength = new QuantityLength(firstValue, firstUnit);
        QuantityLength secondLength = new QuantityLength(secondValue, secondUnit);
        demonstrateLengthEquality(firstLength, secondLength);
    }

    /**
     * Demonstrates equality comparison between two quantity length values.
     */
    public static void demonstrateLengthEquality(QuantityLength firstLength, QuantityLength secondLength) {
        System.out.println(firstLength + " equals " + secondLength + " = " + firstLength.equals(secondLength));
    }
}
