package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final SessionInfo sessionInfo;

    public GreetingController(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    @GetMapping("/")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        if (sessionInfo.getName() == null) {
            sessionInfo.setName(name);
            sessionInfo.setCreatedAt(new Date());
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
