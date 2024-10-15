package com.example.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("health-check")
@RestController
public class HealthCheck {
    @GetMapping
    public boolean healthCheck() {
        return true;
    }
}
