package com.dcurioustech.educhat.service;

import com.dcurioustech.educhat.model.ToolFunction;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    public String executeTool(ToolFunction tool, String input) {
        if (tool == null || tool.getName() == null) {
            return "Tool not found";
        }

        if ("weather".equals(tool.getName())) {
            // Simulate weather API call
            return "Weather in " + input + ": Sunny, 25°C";
        }
        return "Tool not found";
    }
}