package com.zerotrace7.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(1.0, "feet"),
        INCH(1.0 / 12.0, "inch");

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
            return "Quantity(" + value + ", \"" + unit.getDisplayName() + "\")";
        }
    }

    public static boolean areEqual(double firstValue, LengthUnit firstUnit, double secondValue, LengthUnit secondUnit) {
        return new Quantity(firstValue, firstUnit).equals(new Quantity(secondValue, secondUnit));
    }

    public static void main(String[] args) {
        Quantity feet = new Quantity(1.0, LengthUnit.FEET);
        Quantity inchesEquivalent = new Quantity(12.0, LengthUnit.INCH);
        Quantity inches = new Quantity(1.0, LengthUnit.INCH);
        Quantity inchesSame = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("Input: " + feet + " and " + inchesEquivalent);
        System.out.println("Output: Equal (" + feet.equals(inchesEquivalent) + ")");
        System.out.println("Input: " + inches + " and " + inchesSame);
        System.out.println("Output: Equal (" + inches.equals(inchesSame) + ")");
    }
}
