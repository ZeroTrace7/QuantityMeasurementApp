package com.zerotrace7.quantitymeasurement;

public class QuantityMeasurementApp {
    public static final class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Feet other = (Feet) obj;
            return Double.compare(value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static final class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Inches other = (Inches) obj;
            return Double.compare(value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean areFeetEqual(double firstValue, double secondValue) {
        return new Feet(firstValue).equals(new Feet(secondValue));
    }

    public static boolean areInchesEqual(double firstValue, double secondValue) {
        return new Inches(firstValue).equals(new Inches(secondValue));
    }

    public static void main(String[] args) {
        double firstInches = 1.0;
        double secondInches = 1.0;
        double firstFeet = 1.0;
        double secondFeet = 1.0;

        System.out.println("Input: " + firstInches + " inch and " + secondInches + " inch");
        System.out.println("Output: Equal (" + areInchesEqual(firstInches, secondInches) + ")");
        System.out.println("Input: " + firstFeet + " ft and " + secondFeet + " ft");
        System.out.println("Output: Equal (" + areFeetEqual(firstFeet, secondFeet) + ")");
    }
}
