# QuantityMeasurementApp

A small Java app for comparing length measurements across units using value-based equality.

## Features

- Supports `FEET`, `INCH`, `YARDS`, and `CENTIMETERS`
- Converts values to a common base unit before comparison
- Includes simple unit tests for equality scenarios

## Project Structure

- `src/main/QuantityMeasurementApp.java` - application code
- `src/test/QuantityMeasurementAppTest.java` - test cases

## Run

```bash
javac -d out src/main/QuantityMeasurementApp.java
java -cp out QuantityMeasurementApp
```
