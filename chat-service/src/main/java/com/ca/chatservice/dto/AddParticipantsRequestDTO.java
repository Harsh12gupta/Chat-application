package com.ca.chatservice.dto;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class AddParticipantsRequestDTO {
    @NotNull
    private List<UUID> participantsToAdd;

    public List<UUID> getParticipantsToAdd() {
        return participantsToAdd;
    }

    public void setParticipantsToAdd(List<UUID> participantsToAdd) {
        this.participantsToAdd = participantsToAdd;
    }
}
