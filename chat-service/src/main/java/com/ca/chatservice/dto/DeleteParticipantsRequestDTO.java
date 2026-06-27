package com.ca.chatservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class DeleteParticipantsRequestDTO {

    @NotNull
    private List<UUID> participantsToDelete;

    public List<UUID> getParticipantsToDelete() {
        return participantsToDelete;
    }

    public void setParticipantsToDelete(List<UUID> participantsToDelete) {
        this.participantsToDelete = participantsToDelete;
    }
}
