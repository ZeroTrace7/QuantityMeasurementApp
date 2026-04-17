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

    public static void main(String[] args) {
        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        System.out.println("Input: " + first.getValue() + " ft and " + second.getValue() + " ft");
        System.out.println("Output: Equal (" + first.equals(second) + ")");
    }
}
