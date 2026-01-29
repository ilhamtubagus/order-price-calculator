package com.github.ilhamtubagus.opc.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> items;

    public Order(List<OrderItem> items) {
        // Defensive copy -> instead of directly assign items we create a new copy of it
        // Thus making items modification does not directly impact the instance of this class
        this.items = new ArrayList<>(items);
    }

    public List<OrderItem> items() {
        return items;
    }
}

