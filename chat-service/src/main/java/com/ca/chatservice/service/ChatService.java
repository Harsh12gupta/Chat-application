package com.ca.chatservice.service;

import com.ca.chatservice.dto.AddAdminRequestDTO;
import com.ca.chatservice.dto.ConversationResponseDTO;
import com.ca.chatservice.dto.CreateConversationRequestDTO;
import com.ca.chatservice.enums.ConversationType;
import com.ca.chatservice.exception.ConversationNotFoundException;
import com.ca.chatservice.exception.BadRequestException;
import com.ca.chatservice.exception.UnAuthorizedUserException;
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
                                                              createConversationRequestDTO, UUID creatorId){
        Conversation conversation = new Conversation();
        conversation.setName(createConversationRequestDTO.getName());
        List<UUID> admins = new ArrayList<>();
        admins.add(creatorId);
        conversation.setAdmins(admins);
        conversation.setType(createConversationRequestDTO.getType());
        List<UUID> participants = new ArrayList<>(createConversationRequestDTO.getParticipants());
        participants.add(creatorId);
        participants = participants.stream().distinct().toList();
        if(createConversationRequestDTO.getType() == ConversationType.DIRECT){
            if(participants.size() != 2){
                throw new BadRequestException("DIRECT groups cannot have more than 2 participants");
            }
            conversation.setAdmins(new ArrayList<>());
        }
        conversation.setParticipants(participants);
        return conversationRepository.save(conversation).map(ConvMapper::toConvResDTO);
    }

    public Mono<ConversationResponseDTO> addAdmins(AddAdminRequestDTO addAdminRequestDTO,UUID adminId,
                                                   String conversationId){
        return conversationRepository.
                findById(conversationId).
                switchIfEmpty(Mono.error(new ConversationNotFoundException("Conversation:"+conversationId+" does not exist"))).
                flatMap(conversation -> {
                    if(conversation.getType() == ConversationType.DIRECT){
                        throw new BadRequestException("add admin functionality is not available for direct groups");
                    }
                    List<UUID> adminList = conversation.getAdmins();
                    if(!adminList.contains(adminId)){
                        throw new UnAuthorizedUserException("User:"+adminId+" is not an admin");
                    }

                    for(UUID admin:addAdminRequestDTO.getAdminsToAdd()){
                        if(!conversation.getParticipants().contains(admin)){
                            throw new BadRequestException("user:"+admin+" is not part of the participants in group:"+conversationId);
                        }
                    }

                    List<UUID> newAdmins = new ArrayList<>(conversation.getAdmins());
                    newAdmins.addAll(addAdminRequestDTO.getAdminsToAdd());
                    newAdmins = newAdmins.stream().distinct().toList();
                    conversation.setAdmins(newAdmins);
                    return conversationRepository.save(conversation);
                }).map(ConvMapper::toConvResDTO);
    }
}
