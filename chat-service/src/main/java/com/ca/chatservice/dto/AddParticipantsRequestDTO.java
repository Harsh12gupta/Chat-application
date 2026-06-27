package com.ca.chatservice.dto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class AddParticipantsRequestDTO {
    @NotNull
    private List<UUID> participants;

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }
}
