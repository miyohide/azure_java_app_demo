package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpSession;

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
            sessionInfo.setId(counter.incrementAndGet());
            sessionInfo.setName(name);
            sessionInfo.setCreatedAt(new Date());
        }
        return new Greeting(sessionInfo.getId(), String.format(template, sessionInfo.getName()),
                sessionInfo.getCreatedAt());
    }

    @GetMapping("/goodbye")
    public String goodbye(HttpSession session) {
        Optional<String> name = Optional.ofNullable(sessionInfo.getName());
        session.invalidate();
        return "Goodbye " + name.orElse("anonymous");
    }
}
