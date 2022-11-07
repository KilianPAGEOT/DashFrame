package com.github.dashframe.rest;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
}
