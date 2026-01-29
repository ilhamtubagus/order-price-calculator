package com.github.ilhamtubagus.opc.pricing;

import com.github.ilhamtubagus.opc.domain.Money;
import com.github.ilhamtubagus.opc.domain.Order;
import com.github.ilhamtubagus.opc.domain.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class PricingRuleTest {
    private PricingRule pricingRule;
    private Order order;

    @BeforeEach
    void setup(){
        List<OrderItem> orderItems = List.of(
                new OrderItem("1", Money.of(BigDecimal.valueOf(2000)), 1),
                new OrderItem("2", Money.of(BigDecimal.valueOf(1500)), 2)
        );
        order = new Order(orderItems);
    }

    @Test
    void apply_percentageDiscountRule_shouldReturn8000_whenInitialMoney10000AndDiscount20Percent(){
        BigDecimal discount = BigDecimal.valueOf(20);
        PricingRule pricingRule = new PercentageDiscountRule(discount);
        Money initialMoney = Money.of(BigDecimal.valueOf(10000));
        Money expectedResult = Money.of(BigDecimal.valueOf(8000));

        Assertions.assertEquals(expectedResult.value(), pricingRule.apply(order, initialMoney).value());
    }

    @Test
    void apply_fixedDiscountRule_shouldReturn5000_whenInitialMoney8000AndDiscount3000(){
        Money discount = Money.of(BigDecimal.valueOf(3000));
        Money initial = Money.of(BigDecimal.valueOf(8000));
        PricingRule pricingRule = new FixedDiscountRule(discount);
        Money expected = Money.of(BigDecimal.valueOf(5000));

        Assertions.assertEquals(expected, pricingRule.apply(order, initial));
    }

    @Test
    void apply_TaxRule_shouldReturn11000_whenInitialMoney10000AndTax10Percent(){
        Money initial = Money.of(BigDecimal.valueOf(10000));
        PricingRule pricingRule = new TaxRule(BigDecimal.valueOf(10));
        Money expected = Money.of(BigDecimal.valueOf(11000));


        Assertions.assertEquals(expected.value(), pricingRule.apply(order, initial).value());
    }
}
