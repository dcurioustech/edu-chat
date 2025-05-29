package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.Assistant;
import com.dcurioustech.educhat.repository.AssistantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssistantServiceTest {

    @Mock
    private AssistantRepository assistantRepository;

    @InjectMocks
    private AssistantService assistantService;

    private Assistant testAssistant;

    @BeforeEach
    void setUp() {
        testAssistant = new Assistant();
        testAssistant.setName("Test Assistant");
        testAssistant.setDescription("A test assistant");
    }

    @Test
    void createAssistant_ShouldReturnSavedAssistant() {
        // Arrange
        when(assistantRepository.save(any(Assistant.class))).thenAnswer(invocation -> {
            Assistant savedAssistant = invocation.getArgument(0);
            savedAssistant.setId(UUID.randomUUID().toString());
            return savedAssistant;
        });

        // Act
        Assistant result = assistantService.createAssistant(testAssistant);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(testAssistant.getName(), result.getName());
        assertEquals(testAssistant.getDescription(), result.getDescription());
        verify(assistantRepository, times(1)).save(any(Assistant.class));
    }

    @Test
    void getAllAssistants_ShouldReturnListOfAssistants() {
        // Arrange
        Assistant assistant1 = new Assistant();
        assistant1.setId("1");
        assistant1.setName("Assistant 1");
        assistant1.setDescription("First test assistant");
        
        Assistant assistant2 = new Assistant();
        assistant2.setId("2");
        assistant2.setName("Assistant 2");
        assistant2.setDescription("Second test assistant");
        
        List<Assistant> expectedAssistants = Arrays.asList(assistant1, assistant2);
        when(assistantRepository.findAll()).thenReturn(expectedAssistants);

        // Act
        List<Assistant> result = assistantService.getAllAssistants();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedAssistants, result);
        verify(assistantRepository, times(1)).findAll();
    }
}
