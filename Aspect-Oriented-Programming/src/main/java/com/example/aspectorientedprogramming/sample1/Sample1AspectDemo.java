package com.example.aspectorientedprogramming.sample1;

import com.example.aspectorientedprogramming.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "sample1.enabled", havingValue = "true")
public class Sample1AspectDemo implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample1AspectDemo.class);
    private final Machine machine;

    @Autowired
    public Sample1AspectDemo(Machine machine) {
        this.machine = machine;
    }

    @Override
    public void run(String... args) throws Exception {
        machine.saySomething();
        machine.echo("AOP");

        try {
            machine.boom();
        } catch (Exception e) {
            LOGGER.info("Exception from boom called!");
        }

        LOGGER.info(machine.concat("Hello, ", "world!"));
    }
}
