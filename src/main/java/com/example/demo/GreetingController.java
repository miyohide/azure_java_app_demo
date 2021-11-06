package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("----- start GreetingController#greeting");
        for (int i = 0; i < 100_000; i++) {
            Greeting g = new Greeting(1, "hogehoge", new Date());
            logger.info(g.toString());
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name), new Date());
    }
}
