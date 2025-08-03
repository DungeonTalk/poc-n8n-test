package com.n8n.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    
    private final String N8N_WEBHOOK_URL = "https://moonjunwon.app.n8n.cloud/webhook/77753ce3-6250-4dbc-b760-b58b2d5c3ffb";
    
    @PostMapping("/webhook")
    public ResponseEntity<?> proxyToN8n(@RequestBody Map<String, Object> requestBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // n8n에서 {{ $json.chatInput }} 접근 가능하도록 최상위 레벨에 배치
            String userMessage = (String) requestBody.get("message");
            Map<String, Object> n8nBody = Map.of(
                "chatInput", userMessage,
                "message", userMessage,
                "sessionId", "e4920a5755e24f0da4b4ff26bf997d65",
                "action", "sendMessage",
                "timestamp", new java.util.Date().toString()
            );
            
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(n8nBody, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                N8N_WEBHOOK_URL,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            String responseBody = response.getBody();
            if (responseBody == null || responseBody.trim().isEmpty()) {
                responseBody = "응답을 받았지만 내용이 비어있습니다.";
            }
            
            return ResponseEntity.ok(Map.of("message", responseBody));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "n8n 웹훅 오류: " + e.getMessage()));
        }
    }
}