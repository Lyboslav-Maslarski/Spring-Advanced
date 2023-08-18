package com.example.events.event;

import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {
    private final Integer productId;
    public OrderCreatedEvent(Object source, Integer productId) {
        super(source);
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }
}
