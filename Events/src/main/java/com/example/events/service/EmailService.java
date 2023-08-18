package com.example.events.service;

import com.example.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @Order(1)
    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        LOGGER.info("Sending email to user for order with product {}", event.getProductId());
    }
}
