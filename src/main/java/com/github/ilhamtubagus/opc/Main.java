package com.github.ilhamtubagus.opc;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;
import com.github.ilhamtubagus.opc.domain.OrderItem;
import com.github.ilhamtubagus.opc.pricing.*;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PricingRule discount10Percent = new PercentageDiscountRule(BigDecimal.valueOf(10));
        PricingRule discount10k = new FixedDiscountRule(Money.of(BigDecimal.valueOf(1000)));
        PricingRule tax2Percent = new TaxRule(BigDecimal.valueOf(2));

        List<OrderItem> orderItems = List.of(
                new OrderItem("1", Money.of(BigDecimal.valueOf(10000)), 1),
                new OrderItem("2", Money.of(BigDecimal.valueOf(20000)), 2)
        );
        Order order = new Order(orderItems);
        List<PricingRule> pricingRules = List.of(
                discount10Percent,
                discount10k,
                tax2Percent
        );

        PricingEngine pe = new PricingEngine(pricingRules);
        Money finalPrice =  pe.calculateFinalPrice(order);
        System.out.println("finalPrice " + finalPrice);
    }
}
