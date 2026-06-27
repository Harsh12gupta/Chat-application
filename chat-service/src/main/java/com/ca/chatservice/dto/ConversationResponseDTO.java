package com.ca.chatservice.dto;

import java.util.List;
import java.util.UUID;

public class ConversationResponseDTO {

    private String id;
    private List<UUID> participants;
    private List<UUID> admins;
    private String name;

    public List<UUID> getAdmins() {
        return admins;
    }

    public void setAdmins(List<UUID> admins) {
        this.admins = admins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
