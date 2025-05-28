package com.dcurioustech.educhat.controller;

import com.dcurioustech.educhat.model.Assistant;
import com.dcurioustech.educhat.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assistants")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;

    @PostMapping
    public Assistant createAssistant(@RequestBody Assistant assistant) {
        return assistantService.createAssistant(assistant);
    }

    @GetMapping
    public List<Assistant> getAllAssistants() {
        return assistantService.getAllAssistants();
    }
}