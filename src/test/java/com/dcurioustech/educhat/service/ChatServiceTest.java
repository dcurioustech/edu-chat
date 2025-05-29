package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ChatService chatService;

    private static final String TEST_API_KEY = "test-api-key";
    private static final String TEST_MODEL = "test-model";
    private static final String TEST_ENDPOINT = "https://test-api-endpoint.com";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(chatService, "apiKey", TEST_API_KEY);
        ReflectionTestUtils.setField(chatService, "model", TEST_MODEL);
        ReflectionTestUtils.setField(chatService, "apiEndpoint", TEST_ENDPOINT);
    }

    @Test
    void processMessage_ShouldReturnMessageWithResponse() {
        // Arrange
        ChatMessage message = new ChatMessage();
        message.setContent("Hello, test!");
        
        // Mock the API response
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Object> mockChoice = new HashMap<>();
        Map<String, String> mockMessage = new HashMap<>();
        mockMessage.put("content", "Test response from AI");
        mockChoice.put("message", mockMessage);
        
        // Create a proper List for choices instead of Object[]
        java.util.List<Map<String, Object>> choices = new java.util.ArrayList<>();
        choices.add(mockChoice);
        mockResponse.put("choices", choices);

        when(restTemplate.exchange(
                eq(TEST_ENDPOINT),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(Map.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        // Act
        ChatMessage result = chatService.processMessage(message);

        // Assert
        assertNotNull(result);
        assertEquals("Test response from AI", result.getResponse());
        verify(restTemplate, times(1))
                .exchange(eq(TEST_ENDPOINT), eq(HttpMethod.POST), any(), eq(Map.class));
    }

    @Test
    void callAiApi_ShouldHandleApiError() {
        // Arrange
        String testMessage = "Test message";
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(),
                eq(Map.class)))
                .thenThrow(new RuntimeException("API Error"));

        // Act
        String result = chatService.processMessage(new ChatMessage()).getResponse();

        // Assert
        assertTrue(result.startsWith("Error calling AI API"));
    }


    @Test
    void callAiApi_ShouldHandleEmptyResponse() {
        // Arrange
        Map<String, Object> emptyResponse = new HashMap<>();
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(),
                eq(Map.class)))
                .thenReturn(new ResponseEntity<>(emptyResponse, HttpStatus.OK));

        // Act
        String result = chatService.processMessage(new ChatMessage()).getResponse();

        // Assert
        assertEquals("Error: No response from AI API", result);
    }
}
