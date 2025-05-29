package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.ToolFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ToolServiceTest {

    @InjectMocks
    private ToolService toolService;

    private ToolFunction weatherTool;
    private final String testLocation = "New York";
    private final String expectedWeatherResponse = "Weather in " + testLocation + ": Sunny, 25°C";

    @BeforeEach
    void setUp() {
        weatherTool = new ToolFunction();
        weatherTool.setName("weather");
    }

    @Test
    void executeTool_WithWeatherTool_ShouldReturnWeatherResponse() {
        // Act
        String result = toolService.executeTool(weatherTool, testLocation);

        // Assert
        assertNotNull(result);
        assertEquals(expectedWeatherResponse, result);
    }

    @Test
    void executeTool_WithUnknownTool_ShouldReturnToolNotFound() {
        // Arrange
        ToolFunction unknownTool = new ToolFunction();
        unknownTool.setName("unknown_tool");

        // Act
        String result = toolService.executeTool(unknownTool, "input");

        // Assert
        assertEquals("Tool not found", result);
    }

    @Test
    void executeTool_WithNullTool_ShouldReturnToolNotFound() {
        // Act
        String result = toolService.executeTool(null, "input");

        // Assert
        assertEquals("Tool not found", result);
    }

    @Test
    void executeTool_WithNullInput_ShouldHandleGracefully() {
        // Act
        String result = toolService.executeTool(weatherTool, null);

        // Assert
        assertEquals("Weather in unknown location: Sunny, 25°C", result);
    }
}
