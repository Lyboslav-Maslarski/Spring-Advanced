package com.example.aspectorientedprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(Machine.class);

    public void saySomething() {
        LOGGER.info("I'm saying something!");
    }

    public void boom() {
        throw new NullPointerException("Ups, I did something wrong!");
    }

    public void echo(String echo) {
        LOGGER.info("I'm echoing this {}!", echo);
    }

    public String concat(String a, String b) {
        return a + b;
    }
}
