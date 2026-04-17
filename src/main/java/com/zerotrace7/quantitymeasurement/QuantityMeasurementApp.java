package com.zerotrace7.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(1.0, "feet"),
        INCH(1.0 / 12.0, "inch"),
        YARDS(3.0, "yards"),
        CENTIMETERS(0.393701 / 12.0, "centimeters");

        private final double feetFactor;
        private final String displayName;

        LengthUnit(double feetFactor, String displayName) {
            this.feetFactor = feetFactor;
            this.displayName = displayName;
        }

        public double toFeet(double value) {
            return value * feetFactor;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public static final class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            this.value = value;
            this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Quantity other = (Quantity) obj;
            return Double.compare(toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit.name() + ")";
        }
    }

    public static boolean areEqual(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        return new Quantity(firstValue, firstUnit).equals(new Quantity(secondValue, secondUnit));
    }

    public static void main(String[] args) {
        Quantity yardsToFeet = new Quantity(1.0, LengthUnit.YARDS);
        Quantity feet = new Quantity(3.0, LengthUnit.FEET);
        Quantity yardsToInches = new Quantity(1.0, LengthUnit.YARDS);
        Quantity inches = new Quantity(36.0, LengthUnit.INCH);
        Quantity yardsSame = new Quantity(2.0, LengthUnit.YARDS);
        Quantity yardsSameOther = new Quantity(2.0, LengthUnit.YARDS);
        Quantity centimetersSame = new Quantity(2.0, LengthUnit.CENTIMETERS);
        Quantity centimetersSameOther = new Quantity(2.0, LengthUnit.CENTIMETERS);
        Quantity centimetersToInches = new Quantity(1.0, LengthUnit.CENTIMETERS);
        Quantity inchesEquivalent = new Quantity(0.393701, LengthUnit.INCH);

        System.out.println("Input: " + yardsToFeet + " and " + feet);
        System.out.println("Output: Equal (" + yardsToFeet.equals(feet) + ")");
        System.out.println();
        System.out.println("Input: " + yardsToInches + " and " + inches);
        System.out.println("Output: Equal (" + yardsToInches.equals(inches) + ")");
        System.out.println();
        System.out.println("Input: " + yardsSame + " and " + yardsSameOther);
        System.out.println("Output: Equal (" + yardsSame.equals(yardsSameOther) + ")");
        System.out.println();
        System.out.println("Input: " + centimetersSame + " and " + centimetersSameOther);
        System.out.println("Output: Equal (" + centimetersSame.equals(centimetersSameOther) + ")");
        System.out.println();
        System.out.println("Input: " + centimetersToInches + " and " + inchesEquivalent);
        System.out.println("Output: Equal (" + centimetersToInches.equals(inchesEquivalent) + ")");
    }
}
