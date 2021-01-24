package com.example.demo.utils;

import com.example.demo.model.math.TestResult;
import org.springframework.stereotype.Component;

@Component
public class MathBuilder {


    public TestResult mathServiceBuilder(Double sum, Double sub, Double multi, Double div, Double sqrt, Double pow)
    {
        TestResult result = TestResult.builder()
                .sum(sum)
                .sub(sub)
                .multi(multi)
                .div(div)
                .sqrt(sqrt)
                .pow(pow)
                .build();
        return result;
    }
}
