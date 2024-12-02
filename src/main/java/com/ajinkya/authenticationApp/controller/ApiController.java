package com.ajinkya.authenticationApp.controller;

import com.ajinkya.authenticationApp.annotation.JwtSecured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @JwtSecured
    @GetMapping("/secure")
    public String securedEndpoint() {
        return "This is a secured endpoint";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }
}
