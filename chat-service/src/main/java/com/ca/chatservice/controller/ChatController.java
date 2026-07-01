package com.ca.chatservice.controller;

import com.ca.chatservice.dto.*;
import com.ca.chatservice.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class ChatController {
    private final ChatService chatService;
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

    @DeleteMapping("/conversation/{id}/admins")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleRemAdmins(@Valid @RequestBody RemoveAdminRequestDTO removeAdminRequestDTO,
                                                                         @RequestHeader("X-User-Id") UUID adminId,@PathVariable String id){
        return chatService.removeAdmins(removeAdminRequestDTO,adminId,id).map(c -> {
            return ResponseEntity.ok().body(c);
        });
    }

    @PatchMapping("/conversation/{id}/participants")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleAddParticipants(@Valid @RequestBody AddParticipantsRequestDTO addParticipantsRequestDTO,
                                                                               @RequestHeader("X-User-Id") UUID adminId,@PathVariable String id){
        return chatService.addParticipants(addParticipantsRequestDTO, adminId, id).map(c->{
            return ResponseEntity.ok().body(c);
        });
    }

    @DeleteMapping("/conversation/{id}/participants")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleRemParticipants(@Valid @RequestBody DeleteParticipantsRequestDTO deleteParticipantsRequestDTO,
                                                                               @RequestHeader("X-User-Id") UUID adminId,@PathVariable String id){
        return chatService.remParticipants(deleteParticipantsRequestDTO,adminId,id).map(c->{
            return ResponseEntity.ok().body(c);
        });
    }

    @PatchMapping("/conversation/{id}/name")
    public Mono<ResponseEntity<ConversationResponseDTO>> handleUpdateName(@Valid @RequestBody UpdateConversationNameRequestDTO updateConversationNameRequestDTO,
                                                                          @RequestHeader("X-User-Id") UUID adminId,@PathVariable String id){
        return chatService.updateName(updateConversationNameRequestDTO,adminId,id).map((c)->{
            return ResponseEntity.ok().body(c);
        });
    }
}
