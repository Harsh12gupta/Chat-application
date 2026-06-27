package com.ca.chatservice.model;

import com.ca.chatservice.enums.ConversationType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "conversations")
public class Conversation {

    @Id
    private String id;

    private ConversationType type;

    private String name;

    private List<UUID> participants;

    @CreatedDate
    private Instant createdAt;

    private List<UUID> admins;

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

    public List<UUID> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UUID> participants) {
        this.participants = participants;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
