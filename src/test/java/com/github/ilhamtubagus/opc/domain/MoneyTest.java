package com.github.ilhamtubagus.opc.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyTest {
    private Money money;

    @Test
    public void of_shouldThrowIllegalArgument_whenResultMinus(){
        BigDecimal val = BigDecimal.valueOf(-1.232);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Money.of(val);
        });
    }

    @Test
    public void of_shouldConvertBigDecimalIntoMoney(){
        BigDecimal val = BigDecimal.valueOf(1.1312333);
        BigDecimal expected = val.setScale(2, RoundingMode.HALF_UP);

        Money m = Money.of(val);

        Assertions.assertEquals(expected, m.value());
    }

    @Test
    public void subtract_shouldThrowArithmeticException_whenResultMinus(){
        Money initial = Money.of(BigDecimal.valueOf(1000));
        Money other = Money.of(BigDecimal.valueOf(2000));

        Assertions.assertThrows(ArithmeticException.class, () -> {
           initial.subtract(other);
        });
    }

    @Test
    public void subtract_shouldReturn1000_whenResultInitial2000(){
        Money initial = Money.of(BigDecimal.valueOf(2000.01));
        Money other = Money.of(BigDecimal.valueOf(1000));
        Money expected = Money.of(BigDecimal.valueOf(1000.01));

        Money result = initial.subtract(other);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void multiply_shouldReturnCorrectResult(){
        Money initial = Money.of(BigDecimal.valueOf(1000.12));
        BigDecimal percentage = BigDecimal.valueOf(0.01234);

        Money result = initial.multiply(percentage);

        Assertions.assertEquals(BigDecimal.valueOf(12.34), result.value());
    }

    @Test
    public void value_shouldReturn1_whenInitialMoneyValue1(){
        Money initial = Money.of(BigDecimal.valueOf(1.00));

        Assertions.assertEquals(BigDecimal.valueOf(1).setScale(2), initial.value());
    }

    @Test
    public void equals_shouldReturnTrue_whenMoneyComparedWithMoneyWithSameValue(){
        Money a = Money.of(BigDecimal.valueOf(1));
        Money b = Money.of(BigDecimal.valueOf(1.00));

        Assertions.assertEquals(a, b);
    }
}
