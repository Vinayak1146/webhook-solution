package com.demo.webhook;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class StartupRunner {

    @Autowired
    private WebhookClient client;

    @Autowired
    private QueryBuilder queryBuilder;

    @PostConstruct
    public void execute() {

        System.out.println("🚀 Generating webhook...");

        Map<String, Object> response = client.generateWebhook();

        String webhook = response.get("webhook").toString();
        String token = response.get("accessToken").toString();

        String finalQuery = queryBuilder.getFinalQuery();

        client.sendAnswer(webhook, token, finalQuery);

        System.out.println("✅ FINAL SQL SENT SUCCESSFULLY!");
    }
}
