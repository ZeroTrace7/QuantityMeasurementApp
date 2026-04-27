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
        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthAddition(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET));
        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthAddition(
            new QuantityLength(36.0, LengthUnit.INCHES),
            new QuantityLength(1.0, LengthUnit.YARDS),
            LengthUnit.FEET
        );
        demonstrateBaseUnitConversion(LengthUnit.FEET, 12.0);
        demonstrateBaseUnitConversion(LengthUnit.INCHES, 12.0);
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

    /**
     * Demonstrates adding two raw length values and returning the result in the first unit.
     */
    public static void demonstrateLengthAddition(
        double firstValue,
        LengthUnit firstUnit,
        double secondValue,
        LengthUnit secondUnit
    ) {
        QuantityLength sum = QuantityLength.add(firstValue, firstUnit, secondValue, secondUnit, firstUnit);
        System.out.println(
            "add("
                + new QuantityLength(firstValue, firstUnit)
                + ", "
                + new QuantityLength(secondValue, secondUnit)
                + ") = "
                + sum
        );
    }

    /**
     * Demonstrates adding two quantity lengths and returning the result in the first operand unit.
     */
    public static void demonstrateLengthAddition(QuantityLength firstLength, QuantityLength secondLength) {
        QuantityLength sum = firstLength.add(secondLength);
        System.out.println("add(" + firstLength + ", " + secondLength + ") = " + sum);
    }

    /**
     * Demonstrates adding two raw length values and returning the result in an explicit target unit.
     */
    public static void demonstrateLengthAddition(
        double firstValue,
        LengthUnit firstUnit,
        double secondValue,
        LengthUnit secondUnit,
        LengthUnit targetUnit
    ) {
        QuantityLength sum = QuantityLength.add(firstValue, firstUnit, secondValue, secondUnit, targetUnit);
        System.out.println(
            "add("
                + new QuantityLength(firstValue, firstUnit)
                + ", "
                + new QuantityLength(secondValue, secondUnit)
                + ", "
                + targetUnit
                + ") = "
                + sum
        );
    }

    /**
     * Demonstrates adding two quantity lengths and returning the result in an explicit target unit.
     */
    public static void demonstrateLengthAddition(
        QuantityLength firstLength,
        QuantityLength secondLength,
        LengthUnit targetUnit
    ) {
        QuantityLength sum = firstLength.add(secondLength, targetUnit);
        System.out.println("add(" + firstLength + ", " + secondLength + ", " + targetUnit + ") = " + sum);
    }

    /**
     * Demonstrates direct access to the standalone unit conversion API.
     */
    public static void demonstrateBaseUnitConversion(LengthUnit unit, double value) {
        System.out.println(unit + ".convertToBaseUnit(" + value + ") = " + unit.convertToBaseUnit(value));
    }
}
