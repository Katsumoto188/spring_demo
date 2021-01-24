package com.example.demo.math;


import com.example.demo.model.math.TestResult;

public class CompleteMathBuilder {

    public static TestResult validTestResult()
    {
        return TestResult
                .builder()
                .sum(MathUtilsTest.round(1.0))
                .sub(MathUtilsTest.round(2.0))
                .multi(MathUtilsTest.round(3.0))
                .div(MathUtilsTest.round(4.0))
                .sqrt(MathUtilsTest.round(5.0))
                .pow(MathUtilsTest.round(6.0))
                .build();
    }
}
