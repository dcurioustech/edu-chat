package com.dcurioustech.educhat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Assistant {

    @Id
    private String id;
    private String name;
    private String description;
    private String toolIds; // Comma-separated list of tool IDs (simplified for demo)

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToolIds() {
        return toolIds;
    }

    public void setToolIds(String toolIds) {
        this.toolIds = toolIds;
    }
}