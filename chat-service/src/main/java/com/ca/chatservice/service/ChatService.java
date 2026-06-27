package com.ca.chatservice.service;

import com.ca.chatservice.dto.ConversationResponseDTO;
import com.ca.chatservice.dto.CreateConversationRequestDTO;
import com.ca.chatservice.mapper.ConvMapper;
import com.ca.chatservice.model.Conversation;
import com.ca.chatservice.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ConversationRepository conversationRepository;

    public ChatService(ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
    }

    public ConversationResponseDTO createConversation(CreateConversationRequestDTO
                                                              createConversationRequestDTO, UUID id){
        Conversation conversation = new Conversation();
        conversation.setName(createConversationRequestDTO.getName());
        List<UUID> admins = new ArrayList<>();
        admins.add(id);
        conversation.setAdmins(admins);
        conversation.setType(createConversationRequestDTO.getType());
        if(!createConversationRequestDTO.getParticipants().contains(id)){
            createConversationRequestDTO.getParticipants().add(id);
        }
        conversation.setParticipants(createConversationRequestDTO.getParticipants());
        conversationRepository.save(conversation);
        return ConvMapper.toConvResDTO(conversation);
    }
}
