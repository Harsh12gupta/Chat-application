package com.ca.chatservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class RemoveAdminRequestDTO {

    @NotNull
    private List<UUID> adminsToRemove;

    public List<UUID> getAdminsToRemove() {
        return adminsToRemove;
    }

    public void setAdminsToRemove(List<UUID> adminsToRemove) {
        this.adminsToRemove = adminsToRemove;
    }
}
