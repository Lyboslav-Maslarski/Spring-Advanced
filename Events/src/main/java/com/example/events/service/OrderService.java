package com.example.events.service;

import com.example.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createOrder(int productId, int quantity) {
        LOGGER.info("Creating order for product {} with quantity {}.", productId, quantity);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(OrderService.class.getSimpleName(), productId);

        applicationEventPublisher.publishEvent(orderCreatedEvent);
    }
}
