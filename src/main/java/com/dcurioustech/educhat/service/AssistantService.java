package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.Assistant;
import com.dcurioustech.educhat.repository.AssistantRepository;
import io.milvus.client.MilvusServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssistantService {

    @Autowired
    private AssistantRepository assistantRepository;

    @Autowired
    private MilvusServiceClient milvusClient;

    public Assistant createAssistant(Assistant assistant) {
        // Generate a unique ID for the assistant
        assistant.setId(UUID.randomUUID().toString());

        // Save assistant metadata to H2 database
        Assistant savedAssistant = assistantRepository.save(assistant);

        // Optionally store assistant description embedding in Milvus
        storeAssistantEmbedding(savedAssistant);

        return savedAssistant;
    }

    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }

    private void storeAssistantEmbedding(Assistant assistant) {
        // Simulate embedding generation for assistant description
        float[] embedding = generateEmbedding(assistant.getDescription());

        // Store embedding in Milvus (placeholder logic)
        // Example: milvusClient.insert("educhat_embeddings", embedding, assistant.getId());
    }

    private float[] generateEmbedding(String description) {
        // Placeholder for embedding generation (e.g., using SentenceTransformers)
        return new float[]{0.1f, 0.2f, 0.3f}; // Replace with real embedding logic
    }
}