package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final String instanceName;
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    public GreetingController(SessionInfo sessionInfo, @Value("${instance.name}") String instanceName) {
        this.sessionInfo = sessionInfo;
        this.instanceName = instanceName;
    }

    @GetMapping("/")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("GET to '/'. name = [" + name + "], sessionInfo = [" + sessionInfo.toString() + "]");
        if (sessionInfo.getName() == null) {
            sessionInfo.setId(counter.incrementAndGet());
            sessionInfo.setName(name);
            sessionInfo.setCreatedAt(new Date());
            sessionInfo.setInstanceName(instanceName);
        }
        return new Greeting(sessionInfo.getId(), String.format(template, sessionInfo.getName()),
                sessionInfo.getCreatedAt(), sessionInfo.getInstanceName());
    }

    @GetMapping("/goodbye")
    public String goodbye(HttpSession session) {
        Optional<String> name = Optional.ofNullable(sessionInfo.getName());
        session.invalidate();
        return "Goodbye " + name.orElse("anonymous");
    }
}
