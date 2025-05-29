package com.dcurioustech.educhat.controller;

import com.dcurioustech.educhat.model.Assistant;
import com.dcurioustech.educhat.service.AssistantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AssistantControllerTest {

    @Mock
    private AssistantService assistantService;

    @InjectMocks
    private AssistantController assistantController;

    private MockMvc mockMvc;

    private Assistant testAssistant;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(assistantController).build();
        
        testAssistant = new Assistant();
        testAssistant.setId("test-id-123");
        testAssistant.setName("Test Assistant");
        testAssistant.setDescription("A test assistant");
    }

    @Test
    void createAssistant_ShouldReturnCreatedAssistant() throws Exception {
        when(assistantService.createAssistant(any(Assistant.class))).thenReturn(testAssistant);

        String assistantJson = """
            {
                "name": "Test Assistant",
                "description": "A test assistant"
            }
            """;

        mockMvc.perform(post("/api/assistants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(assistantJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testAssistant.getId())))
                .andExpect(jsonPath("$.name", is(testAssistant.getName())))
                .andExpect(jsonPath("$.description", is(testAssistant.getDescription())));

        verify(assistantService, times(1)).createAssistant(any(Assistant.class));
    }

    @Test
    void getAllAssistants_ShouldReturnListOfAssistants() throws Exception {
        // Create test data
        Assistant assistant1 = new Assistant();
        assistant1.setId("1");
        assistant1.setName("Assistant 1");
        assistant1.setDescription("First test assistant");
        
        Assistant assistant2 = new Assistant();
        assistant2.setId("2");
        assistant2.setName("Assistant 2");
        assistant2.setDescription("Second test assistant");
        
        List<Assistant> assistants = Arrays.asList(assistant1, assistant2);
        
        when(assistantService.getAllAssistants()).thenReturn(assistants);

        mockMvc.perform(get("/api/assistants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(assistant1.getId())))
                .andExpect(jsonPath("$[0].name", is(assistant1.getName())))
                .andExpect(jsonPath("$[1].id", is(assistant2.getId())))
                .andExpect(jsonPath("$[1].name", is(assistant2.getName())));

        verify(assistantService, times(1)).getAllAssistants();
    }
}
