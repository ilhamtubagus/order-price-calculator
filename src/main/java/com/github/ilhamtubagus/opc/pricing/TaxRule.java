package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxRule implements PricingRule {

    private final BigDecimal taxRate;
    private final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public TaxRule(@Min(0) BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public Money apply(Order order, Money currentTotal) {
        BigDecimal rate = this.taxRate.divide(ONE_HUNDRED, 4, RoundingMode.UP);
        Money tax = currentTotal.multiply(rate);

        return currentTotal.add(tax);
    }
}

