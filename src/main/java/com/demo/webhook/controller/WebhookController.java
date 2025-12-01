package com.demo.webhook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebhookController {

    // GET - browser test
    @GetMapping("/webhook")
    public String test() {
        System.out.println("✅ webhook triggered");
        return "Webhook working!";
    }

    // POST - real webhook receiver
    @PostMapping("/webhook")
    public String receiveWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Payload: " + payload);
        return "JSON processed!";
    }
}
