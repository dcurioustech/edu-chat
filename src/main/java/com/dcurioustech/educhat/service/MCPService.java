package com.dcurioustech.educhat.service;

import io.milvus.client.MilvusServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MCPService {

    @Autowired
    private MilvusServiceClient milvusClient;

    private final Map<String, String> contextStore = new HashMap<>();

    public String createContext(String assistantId, String contextData) {
        // Generate a unique context ID
        String contextId = UUID.randomUUID().toString();

        // Store context in in-memory store (replace with persistent storage in production)
        contextStore.put(contextId, contextData);

        // Store context embedding in Milvus for similarity search
        storeContextEmbedding(contextId, contextData);

        return contextId;
    }

    public String getContext(String contextId) {
        // Retrieve context from in-memory store
        return contextStore.getOrDefault(contextId, "Context not found");
    }

    private void storeContextEmbedding(String contextId, String contextData) {
        // Simulate embedding generation for context data
        float[] embedding = generateEmbedding(contextData);

        // Placeholder for Milvus insertion
        // Example: milvusClient.insert("educhat_embeddings", embedding, contextId);
    }

    private float[] generateEmbedding(String contextData) {
        // Placeholder for embedding generation (e.g., using SentenceTransformers)
        return new float[]{0.1f, 0.2f, 0.3f}; // Replace with real embedding logic
    }
}