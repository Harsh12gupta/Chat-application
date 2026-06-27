package com.ca.chatservice.mapper;

import com.ca.chatservice.dto.ConversationResponseDTO;
import com.ca.chatservice.model.Conversation;

public class ConvMapper {
    public static ConversationResponseDTO toConvResDTO(Conversation conversation){
        ConversationResponseDTO conversationResponseDTO = new ConversationResponseDTO();
        conversationResponseDTO.setAdmins(conversation.getAdmins());
        conversationResponseDTO.setId(conversation.getId());
        conversationResponseDTO.setName(conversation.getName());
        conversationResponseDTO.setParticipants(conversation.getParticipants());
        return conversationResponseDTO;
    }
}
