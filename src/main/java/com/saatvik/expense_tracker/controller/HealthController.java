package com.saatvik.expense_tracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/API")
public class HealthController {
    @GetMapping("/health")
    public Map<String,Object> health(){
        return Map.of("status","up","app","ExpenseTracker"
        ,"Time", LocalDateTime.now().toString());
    }
}
