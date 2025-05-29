package com.dcurioustech.educhat.controller;

import com.dcurioustech.educhat.model.ChatMessage;
import com.dcurioustech.educhat.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private ChatController chatController;

    private ChatMessage testMessage;

    @BeforeEach
    void setUp() {
        testMessage = new ChatMessage();
        testMessage.setContent("Hello, test!");
        testMessage.setResponse("Test response");
    }

    @Test
    void sendMessage_ShouldProcessMessageAndReturnIt() {
        // Arrange
        when(chatService.processMessage(any(ChatMessage.class))).thenReturn(testMessage);

        // Act
        ChatMessage result = chatController.sendMessage(testMessage);

        // Assert
        assertNotNull(result);
        assertEquals(testMessage.getContent(), result.getContent());
        assertEquals(testMessage.getResponse(), result.getResponse());
        
        verify(chatService, times(1)).processMessage(any(ChatMessage.class));
    }

    @Test
    void sendMessage_ShouldHandleNullContent() {
        // Arrange
        ChatMessage message = new ChatMessage();
        message.setContent(null);
        when(chatService.processMessage(any(ChatMessage.class))).thenReturn(message);

        // Act
        ChatMessage result = chatController.sendMessage(message);

        // Assert
        assertNotNull(result);
        assertNull(result.getContent());
        verify(chatService, times(1)).processMessage(any(ChatMessage.class));
    }
}
