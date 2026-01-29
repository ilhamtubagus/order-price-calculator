package com.github.ilhamtubagus.opc.domain;

import jakarta.validation.constraints.Min;

public record OrderItem(
        String productId,
        Money unitPrice,
        @Min(1)
        int quantity
) {
}

