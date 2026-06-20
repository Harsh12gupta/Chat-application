package com.ca.userservice.controller;

import com.ca.userservice.dto.MyProfileResponseDTO;
import com.ca.userservice.dto.UpdateUserProfileRequestDTO;
import com.ca.userservice.dto.UserProfileResponseDTO;
import com.ca.userservice.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me/{id}")
    public ResponseEntity<MyProfileResponseDTO> getMyProfile(@PathVariable UUID id){
        return ResponseEntity.ok().body(userProfileService.getMyProfile(id));
    }

    @PutMapping("/me/{id}")
    public ResponseEntity<MyProfileResponseDTO> updateMyProfile(@Valid @RequestBody UpdateUserProfileRequestDTO updateUserProfileRequestDTO,
                                                                @PathVariable UUID id){
        return ResponseEntity.ok().body(userProfileService.updateMyProfile(updateUserProfileRequestDTO,id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserProfileResponseDTO>> getUserProfile(@RequestParam String username){
        return ResponseEntity.ok().body(userProfileService.getUserProfiles(username));
    }
}
