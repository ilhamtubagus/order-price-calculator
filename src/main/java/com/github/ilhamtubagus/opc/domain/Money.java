package com.github.ilhamtubagus.opc.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(BigDecimal amount) {
        BigDecimal newAmt = Objects.requireNonNull(amount);
        newAmt = newAmt.setScale(2, RoundingMode.HALF_UP);
        if (newAmt.signum() == -1){
            throw new IllegalArgumentException("Value cannot be negative");
        }

        return new Money(newAmt);
    }

    public Money add(Money other) {
        BigDecimal result = this.amount.add(other.amount);

        return new Money(result);
    }

    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.signum() == -1){
            throw new ArithmeticException("Result must be positive");
        }

        return new Money(result);
    }

    public Money multiply(BigDecimal multiplier) {
        BigDecimal result = this.amount.multiply(multiplier);
        result = result.setScale(2, RoundingMode.HALF_UP);

        return new Money(result);
    }

    public BigDecimal value() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;

        // if the value is the same, we return true
        return amount.equals(money.amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}

