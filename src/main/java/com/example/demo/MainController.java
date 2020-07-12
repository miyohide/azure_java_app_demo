package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class MainController {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        logger.warn("***** addNewUser start ******");
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        userRepository.save(u);
        logger.warn("***** addNewUser end ******");
        return "Saved User data. name = [" + name + "], email = [" + email + "]";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        logger.warn("***** getAllUsers start ******");
        logger.warn("***** getAllUsers end ******");
        return userRepository.findAll();
    }
}
