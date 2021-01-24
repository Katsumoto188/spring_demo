// Aplicação de Regra de Negocio, chamado pela controller, acesso somente aos repository e outras Services

package com.example.demo.service.math;

import com.example.demo.model.math.TestResult;
import com.example.demo.utils.MathBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MathService {
    private final MathBuilder mathBuilder;

    public Double sumMate(Double one, Double two){
         return one + two;
     }
    public Double subMate(Double one, Double two){
        return one - two;
    }
    public Double multiMate(Double one, Double two){
        return one * two;
    }
    public Double divMate(Double one, Double two){
        return one / two;
    }
    public Double sqrtMate(Double one){
        return Math.sqrt(one);
    }
    public Double powMate(Double one, Double two){
        return Math.pow(one, two);
    }
    public TestResult testResultDois(Double one, Double two){
         return TestResult.builder()
                 .sum(sumMate(one,two))
                 .sub(subMate(one,two))
                 .multi(multiMate(one,two))
                 .div(divMate(one,two))
                 .sqrt(sqrtMate(one))
                 .pow(powMate(one,two))
                 .build();
    }
    public Double mathHandler(String operator, Double one, Double two){
         switch (operator){
             case "sum":
                return sumMate(one, two);
             case "sub":
                 return subMate(one, two);
             case "multi":
                 return multiMate(one, two);
             case "div":
                 return divMate(one,two);
             case "sqrt":
                 return sqrtMate(one);
             case "pow":
                 return powMate(one, two);
             default:
                 throw new RuntimeException("Deu erro");
         }

    }

    public TestResult testeResultDois(Double one, Double two)
    {
        TestResult result = mathBuilder.mathServiceBuilder(sumMate(one, two),
                subMate(one, two),
                multiMate(one, two),
                divMate(one, two),
                sqrtMate(one),
                powMate(one, two));
        return result;
    }
}
