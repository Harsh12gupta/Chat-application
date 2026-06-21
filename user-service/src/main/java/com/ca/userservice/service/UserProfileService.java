package com.ca.userservice.service;

import com.ca.userservice.dto.MyProfileResponseDTO;
import com.ca.userservice.dto.UpdateUserProfileRequestDTO;
import com.ca.userservice.dto.UserProfileResponseDTO;
import com.ca.userservice.exception.InvalidUserIdException;
import com.ca.userservice.exception.NotUniqueUserNameException;
import com.ca.userservice.mapper.UserMapper;
import com.ca.userservice.model.UserProfile;
import com.ca.userservice.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    public MyProfileResponseDTO getMyProfile(UUID id){
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if(userProfile.isEmpty()){
            throw new InvalidUserIdException("user id is not valid.");
        }
        return UserMapper.toMyProfileResDTO(userProfile.get());
    }

    public MyProfileResponseDTO updateMyProfile(UpdateUserProfileRequestDTO updateUserProfileRequestDTO,UUID id){
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> {
            return new InvalidUserIdException("user id is invalid");
        });
        String newUsername = updateUserProfileRequestDTO.getUsername();
        if(userProfileRepository.existsByUsernameAndIdNot(newUsername,id)){
            throw new NotUniqueUserNameException("Username "+newUsername+" is already taken");
        }
        userProfile.setDisplayName(updateUserProfileRequestDTO.getDisplayName());
        userProfile.setUsername(newUsername);
        UserProfile updatedUser = userProfileRepository.save(userProfile);
        return UserMapper.toMyProfileResDTO(updatedUser);
    }

    public List<UserProfileResponseDTO> getUserProfiles(String username){
        List<UserProfile> userProfileList = userProfileRepository.findByUsernameContainingIgnoreCase(username);

        return userProfileList.stream().map((UserProfile userProfile) -> {
            return UserMapper.toUserProfileResDTO(userProfile);
        }).toList();
    }

    public void createProfile(String email,String id){
        UserProfile userProfile = new UserProfile();
        //if kafka send same event twice to the consumer
        if(userProfileRepository.existsById(UUID.fromString(id)) || userProfileRepository.existsByEmail(email)){
            return;
        }
        userProfile.setEmail(email);
        userProfile.setUsername("user_"+id);
        userProfile.setId(UUID.fromString(id));
        userProfileRepository.save(userProfile);
    }
}
