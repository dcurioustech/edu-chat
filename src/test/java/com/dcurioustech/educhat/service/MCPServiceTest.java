package com.dcurioustech.educhat.service;

import io.milvus.client.MilvusServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MCPServiceTest {

    @Mock
    private MilvusServiceClient milvusClient;

    @InjectMocks
    private MCPService mcpService;

    private final String testAssistantId = "test-assistant-123";
    private final String testContextData = "Test context data for MCP service";

    @Test
    void createContext_ShouldReturnContextId() {
        // Act
        String contextId = mcpService.createContext(testAssistantId, testContextData);

        // Assert
        assertNotNull(contextId);
        assertFalse(contextId.isEmpty());
        
        // Verify context can be retrieved
        String retrievedContext = mcpService.getContext(contextId);
        assertEquals(testContextData, retrievedContext);
    }

    @Test
    void getContext_WithNonExistentId_ShouldReturnNotFoundMessage() {
        // Arrange
        String nonExistentId = "non-existent-id";

        // Act
        String result = mcpService.getContext(nonExistentId);

        // Assert
        assertEquals("Context not found", result);
    }

    @Test
    void createContext_ShouldStoreContextWithDifferentIds() {
        // Arrange
        String contextData1 = "First context";
        String contextData2 = "Second context";

        // Act
        String id1 = mcpService.createContext(testAssistantId, contextData1);
        String id2 = mcpService.createContext(testAssistantId, contextData2);

        // Assert
        assertNotEquals(id1, id2);
        assertEquals(contextData1, mcpService.getContext(id1));
        assertEquals(contextData2, mcpService.getContext(id2));
    }

    // Note: generateEmbedding is private and should be tested through the public API
    // The method is called internally by createContext, which is already tested
}
