import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuantityMeasurementAppTest {

    @Test
    void givenSameYardValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.YARDS,
                        1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                "Expected identical yard measurements to be equal");
    }

    @Test
    void givenDifferentYardValueWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.YARDS,
                        2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                "Expected different yard measurements to not be equal");
    }

    @Test
    void givenYardAndEquivalentFeetWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.YARDS,
                        3.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected 1 yard and 3 feet to be equal");
    }

    @Test
    void givenFeetAndEquivalentYardWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(3.0, QuantityMeasurementApp.LengthUnit.FEET,
                        1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                "Expected 3 feet and 1 yard to be equal");
    }

    @Test
    void givenYardAndEquivalentInchesWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.YARDS,
                        36.0, QuantityMeasurementApp.LengthUnit.INCH),
                "Expected 1 yard and 36 inches to be equal");
    }

    @Test
    void givenInchesAndEquivalentYardWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(36.0, QuantityMeasurementApp.LengthUnit.INCH,
                        1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                "Expected 36 inches and 1 yard to be equal");
    }

    @Test
    void givenYardAndNonEquivalentFeetWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.YARDS,
                        2.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected 1 yard and 2 feet to not be equal");
    }

    @Test
    void givenSameCentimeterValueWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
                "Expected identical centimeter measurements to be equal");
    }

    @Test
    void givenCentimeterAndEquivalentInchesWhenComparedThenReturnsTrue() {
        assertTrue(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        0.393701, QuantityMeasurementApp.LengthUnit.INCH),
                "Expected 1 centimeter and 0.393701 inches to be equal");
    }

    @Test
    void givenCentimeterAndNonEquivalentFeetWhenComparedThenReturnsFalse() {
        assertFalse(QuantityMeasurementApp.areEqual(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        1.0, QuantityMeasurementApp.LengthUnit.FEET),
                "Expected 1 centimeter and 1 foot to not be equal");
    }

    @Test
    void givenTransitiveEquivalentUnitsWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Quantity yard =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet =
                new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity inches =
                new QuantityMeasurementApp.Quantity(36.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet), "Expected yard and feet quantities to be equal");
        assertTrue(feet.equals(inches), "Expected feet and inches quantities to be equal");
        assertTrue(yard.equals(inches), "Expected transitive equality across units");
    }

    @Test
    void givenNullUnitWhenConstructedThenThrowsException() {
        assertThrows(NullPointerException.class,
                () -> new QuantityMeasurementApp.Quantity(1.0, null),
                "Expected a null unit to be rejected");
    }

    @Test
    void givenSameReferenceWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertTrue(quantity.equals(quantity), "Expected quantity to be equal to itself");
    }

    @Test
    void givenNullWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertFalse(quantity.equals(null), "Expected quantity to not be equal to null");
    }

    @Test
    void givenDifferentTypeWhenComparedThenReturnsFalse() {
        QuantityMeasurementApp.Quantity quantity =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        assertFalse(quantity.equals("1 yard"), "Expected quantity to not match a different object type");
    }

    @Test
    void givenComplexAllUnitScenarioWhenComparedThenReturnsTrue() {
        QuantityMeasurementApp.Quantity yards =
                new QuantityMeasurementApp.Quantity(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Quantity feet =
                new QuantityMeasurementApp.Quantity(6.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity inches =
                new QuantityMeasurementApp.Quantity(72.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yards.equals(feet), "Expected 2 yards and 6 feet to be equal");
        assertTrue(feet.equals(inches), "Expected 6 feet and 72 inches to be equal");
        assertTrue(yards.equals(inches), "Expected 2 yards and 72 inches to be equal");
    }
}
