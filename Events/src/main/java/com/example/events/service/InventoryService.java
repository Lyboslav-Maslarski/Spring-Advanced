package com.example.events.service;

import com.example.events.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

    @Order(2)
    @EventListener(OrderCreatedEvent.class)
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        LOGGER.info("Decreasing inventory for product {}", event.getProductId());
    }
}
