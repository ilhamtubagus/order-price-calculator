package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;

public class FixedDiscountRule implements PricingRule {

    private final Money discount;

    public FixedDiscountRule(Money discount) {
        this.discount = discount;
    }

    @Override
    public Money apply(Order order, Money currentTotal) {

        return currentTotal.subtract(this.discount);
    }
}

