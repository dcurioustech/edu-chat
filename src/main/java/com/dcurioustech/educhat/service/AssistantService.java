package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.Assistant;
import com.dcurioustech.educhat.repository.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssistantService {

    @Autowired
    private AssistantRepository assistantRepository;

    public Assistant createAssistant(Assistant assistant) {
        // Generate a unique ID for the assistant
        assistant.setId(UUID.randomUUID().toString());

        // Save assistant metadata to H2 database
        Assistant savedAssistant = assistantRepository.save(assistant);

        return savedAssistant;
    }

    public List<Assistant> getAllAssistants() {
        return assistantRepository.findAll();
    }
}