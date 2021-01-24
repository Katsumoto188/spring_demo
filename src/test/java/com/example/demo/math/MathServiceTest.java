package com.example.demo.math;

import com.example.demo.model.math.TestResult;
import com.example.demo.service.math.MathService;
import com.example.demo.utils.MathBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.math.MathUtilsTest.round;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathServiceTest {
    @InjectMocks
    private MathService mathService;

    @Mock
    private MathBuilder mathBuilder;

    private Double firstNumber;
    private Double secondNumber;
    private TestResult testResult;

    @BeforeEach
    private void init()
    {
        firstNumber = 4.0;
        secondNumber = 2.0;

        testResult = CompleteMathBuilder.validTestResult();
    }

    @Test
    public void shouldAdd()
    {
        Double result = mathService.sumMate(firstNumber, secondNumber);
        Assertions.assertEquals(6.0, round(result, 2));
    }

    @Test
    public void shouldSub()
    {
        Double result = mathService.subMate(firstNumber, secondNumber);
        Assertions.assertEquals(2.0, round(result));
    }

    @Test
    public void testMathResult()
    {
        when(mathBuilder.mathServiceBuilder(
                6.0,
                2.0,
                8.0,
                2.0,
                2.0,
                16.0
        )).thenReturn(testResult);
        TestResult result = mathService.testeResultDois(firstNumber, secondNumber);
        Assertions.assertEquals(testResult, result);

//        verify(mathBuilder, times(2)).mathServiceBuilder(6.0, 2.0, 8.0, 2.0, 2.0, 16.0); // Tem que ter rodado 2x antes daqui.
        verify(mathBuilder).mathServiceBuilder(6.0, 2.0, 8.0, 2.0, 2.0, 16.0);
//        verify(mathBuilder).mathServiceBuilder(6.0, 1.0, 8.0, 2.0, 2.0, 16.0);

    }
}
