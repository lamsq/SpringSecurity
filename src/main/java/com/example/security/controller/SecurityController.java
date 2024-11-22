package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String home() {
        return "Home page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin page";
    }

    @GetMapping("/user")
    public String user() {
        return "User page";
    }
}
