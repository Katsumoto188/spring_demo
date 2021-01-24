package com.example.demo.math;

public class MathUtilsTest {
    public static Double round(Double value)
    {
        return round(value, 2);
    }

    public static Double round(Double value, Integer decimalPlace)
    {
        return Math.round(value*Math.pow(10, decimalPlace))/Math.pow(10, decimalPlace);
    }
}
