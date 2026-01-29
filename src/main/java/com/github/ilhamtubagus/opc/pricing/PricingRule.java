package com.github.ilhamtubagus.opc.pricing;


import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;

public interface PricingRule {
    Money apply(Order order, Money currentTotal);
}

