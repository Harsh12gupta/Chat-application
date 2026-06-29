package com.ca.chatservice.controller;

import com.ca.chatservice.dto.AddAdminRequestDTO;
import com.ca.chatservice.dto.ConversationResponseDTO;
import com.ca.chatservice.dto.CreateConversationRequestDTO;
import com.ca.chatservice.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ChatController {
    private ChatService chatService;
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/conversation")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleCreateConversation(@Valid @RequestBody CreateConversationRequestDTO
                                                                                              createConversationRequestDTO,
                                                                                  @RequestHeader("X-User-Id") UUID id){
        return chatService.createConversation(createConversationRequestDTO,id).map((c)->{
            return ResponseEntity.ok().body(c);
        });
    }

    @PatchMapping("/conversation/{id}/admins")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleAddAdmins(@Valid @RequestBody AddAdminRequestDTO addAdminRequestDTO,
                                                                         @RequestHeader("X-User-Id") UUID adminId, @PathVariable String id){
        return chatService.addAdmins(addAdminRequestDTO,adminId,id).map((c)->{
            return ResponseEntity.ok().body(c);
        });
    }
}
