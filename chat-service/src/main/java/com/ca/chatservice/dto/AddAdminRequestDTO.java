package com.ca.chatservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class AddAdminRequestDTO {

    @NotNull
    private List<UUID> adminsToAdd;

    public List<UUID> getAdminsToAdd() {
        return adminsToAdd;
    }

    public void setAdminsToAdd(List<UUID> adminsToAdd) {
        this.adminsToAdd = adminsToAdd;
    }
}
