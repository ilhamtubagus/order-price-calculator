package com.github.ilhamtubagus.opc.domain;

public record OrderItem(
        String productId,
        Money unitPrice,
        int quantity
) {
        public OrderItem(String productId, Money unitPrice, int quantity){
                if (quantity <0){
                        throw new IllegalArgumentException("Quantity minimum 0");
                }
                this.productId =  productId;
                this.unitPrice = unitPrice;
                this.quantity = quantity;
        }
}

