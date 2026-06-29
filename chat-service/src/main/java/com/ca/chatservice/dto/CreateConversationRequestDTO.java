package com.ca.chatservice.dto;

import com.ca.chatservice.enums.ConversationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class CreateConversationRequestDTO {

    @NotNull
    @Size(min = 2,message = "atleast 2 partcicpants are required")
    private List<UUID> participants;

    @NotNull(message = "Type of the conversation is required")
    private ConversationType type;

    @NotBlank(message = "name of the conversation is required")
    private String name;

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }

    public ConversationType getType() {
        return type;
    }

    public void setType(ConversationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
