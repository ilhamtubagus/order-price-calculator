package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PricingEngine {

    private final List<PricingRule> pricingRules;

    public PricingEngine(List<PricingRule> pricingRules) {
        // Defensive copy
        this.pricingRules = new ArrayList<>(pricingRules);
    }

    /**
     * Entry point used by Main / Service layer
     */
    public Money calculateFinalPrice(Order order) {
        Objects.requireNonNull(order, "order must not be null");

        Money baseTotal = calculateBaseTotal(order);
        for (PricingRule rule : this.pricingRules ){
            baseTotal = rule.apply(order, baseTotal);
        }

        return baseTotal;
    }

    /**
     * Calculates total price BEFORE discounts/tax
     */
    private Money calculateBaseTotal(Order order) {
        return order.items().stream()
                .map(m -> m.unitPrice().multiply(BigDecimal.valueOf(m.quantity())))
                .reduce(Money.of(BigDecimal.ZERO), Money::add);
    }
}
