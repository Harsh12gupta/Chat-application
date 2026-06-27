package com.ca.chatservice.service;

import com.ca.chatservice.dto.ConversationResponseDTO;
import com.ca.chatservice.dto.CreateConversationRequestDTO;
import com.ca.chatservice.mapper.ConvMapper;
import com.ca.chatservice.model.Conversation;
import com.ca.chatservice.repository.ConversationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ConversationRepository conversationRepository;

    public ChatService(ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
    }

    public Mono<ConversationResponseDTO> createConversation(CreateConversationRequestDTO
                                                              createConversationRequestDTO, UUID id){
        Conversation conversation = new Conversation();
        conversation.setName(createConversationRequestDTO.getName());
        List<UUID> admins = new ArrayList<>();
        admins.add(id);
        conversation.setAdmins(admins);
        conversation.setType(createConversationRequestDTO.getType());
        List<UUID> participants = new ArrayList<>(createConversationRequestDTO.getParticipants());
        participants.add(id);
        participants = participants.stream().distinct().toList();
        conversation.setParticipants(participants);
        return conversationRepository.save(conversation).map(ConvMapper::toConvResDTO);
    }
}
