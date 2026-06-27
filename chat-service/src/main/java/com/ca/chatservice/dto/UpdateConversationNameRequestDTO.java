package com.ca.chatservice.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateConversationNameRequestDTO {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
