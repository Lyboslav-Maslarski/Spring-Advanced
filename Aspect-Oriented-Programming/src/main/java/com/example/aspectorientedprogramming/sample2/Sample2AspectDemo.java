package com.example.aspectorientedprogramming.sample2;

import com.example.aspectorientedprogramming.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "sample2.enabled", havingValue = "true")
public class Sample2AspectDemo implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample2AspectDemo.class);
    private final Machine machine;

    @Autowired
    public Sample2AspectDemo(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(machine.concat("Hello, ", "world!"));
    }
}
