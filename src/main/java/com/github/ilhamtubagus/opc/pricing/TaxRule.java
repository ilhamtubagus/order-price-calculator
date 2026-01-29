package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TaxRule implements PricingRule {

    private final BigDecimal taxRate;
    private final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    public TaxRule(BigDecimal taxRate) {
        Objects.requireNonNull(taxRate);
        if (taxRate.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Tax can't be negative");
        }
        this.taxRate = taxRate;
    }

    @Override
    public Money apply(Order order, Money currentTotal) {
        BigDecimal rate = this.taxRate.divide(ONE_HUNDRED, 4, RoundingMode.UP);
        Money tax = currentTotal.multiply(rate);

        return currentTotal.add(tax);
    }
}

