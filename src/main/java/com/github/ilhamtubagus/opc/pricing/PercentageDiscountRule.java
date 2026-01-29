package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PercentageDiscountRule implements PricingRule {

    private final BigDecimal percentage;
    private final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public PercentageDiscountRule(
            BigDecimal percentage
    ) {
        Objects.requireNonNull(percentage);

        if(percentage.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Discount can't be negative");
        }
        if(percentage.compareTo(BigDecimal.valueOf(100)) > 0){
            throw new IllegalArgumentException("Discount maximum 100");
        }
        this.percentage = percentage;
    }

    @Override
    public Money apply(Order order, Money currentTotal) {
        BigDecimal rate = this.percentage.divide(ONE_HUNDRED, 4, RoundingMode.HALF_UP);
        Money discountPrice = currentTotal.multiply(rate);

        return currentTotal.subtract(discountPrice);
    }
}

