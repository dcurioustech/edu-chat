package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.ChatMessage;
import io.milvus.client.MilvusServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    private MilvusServiceClient milvusClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ai.api.model}")
    private String model;

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.endpoint}")
    private String apiEndpoint;

    public ChatMessage processMessage(ChatMessage message) {
        // Simulate embedding generation and storage in Milvus
        float[] embedding = generateEmbedding(message.getContent());
        storeInMilvus(embedding, message.getId());

        // Call Grok API for response
        String response = callAiApi(message.getContent());
        message.setResponse(response);
        return message;
    }

    private String callAiApi(String userMessage) {
        // Set up headers with API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Prepare request payload
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", userMessage);
                }}
        });

        // Make API call
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    apiEndpoint,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            // Process OpenAI API response
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                System.out.println(responseBody);
                List choices = (ArrayList) responseBody.get("choices");
                if (choices.size() > 0) {
                    Map<String, Object> choice = (Map<String, Object>) choices.get(0);
                    Map<String, String> message = (Map<String, String>) choice.get("message");
                    return message.get("content");
                }
            }
            return "Error: No response from AI API";
        } catch (Exception e) {
            return "Error calling AI API: " + e.getMessage();
        }
    }

    private float[] generateEmbedding(String content) {
        // Placeholder for embedding generation (e.g., SentenceTransformers)
        return new float[]{0.1f, 0.2f, 0.3f}; // Replace with real embedding
    }

    private void storeInMilvus(float[] embedding, String messageId) {
        // Implement Milvus insertion logic
        // Example: milvusClient.insert(collectionName, embedding, messageId);
    }
}