package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;
import com.github.ilhamtubagus.opc.domain.OrderItem;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscountRule implements PricingRule {

    private final BigDecimal percentage;
    private final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public PercentageDiscountRule(
            @Min(1)
            @Max(100)
            BigDecimal percentage
    ) {
        this.percentage = percentage;
    }

    @Override
    public Money apply(Order order, Money currentTotal) {
        BigDecimal rate = this.percentage.divide(ONE_HUNDRED, 4, RoundingMode.HALF_UP);
        Money discountPrice = currentTotal.multiply(rate);

        return currentTotal.subtract(discountPrice);
    }
}

