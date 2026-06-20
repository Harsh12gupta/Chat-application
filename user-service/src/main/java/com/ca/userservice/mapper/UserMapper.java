package com.ca.userservice.mapper;

import com.ca.userservice.dto.MyProfileResponseDTO;
import com.ca.userservice.dto.UserProfileResponseDTO;
import com.ca.userservice.model.UserProfile;

public class UserMapper {
    public static MyProfileResponseDTO toMyProfileResDTO(UserProfile userProfile){

        MyProfileResponseDTO myProfileResponseDTO = new MyProfileResponseDTO();
        myProfileResponseDTO.setDisplayName(userProfile.getDisplayName());
        myProfileResponseDTO.setId(userProfile.getId());
        myProfileResponseDTO.setCreatedAt(userProfile.getCreatedAt());
        myProfileResponseDTO.setUpdatedAt(userProfile.getUpdatedAt());
        myProfileResponseDTO.setUserName(userProfile.getUsername());
        myProfileResponseDTO.setEmail(userProfile.getEmail());

        return myProfileResponseDTO;
    }

    public static UserProfileResponseDTO toUserProfileResDTO(UserProfile userProfile){

        UserProfileResponseDTO userProfileResponseDTO = new UserProfileResponseDTO();
        userProfileResponseDTO.setUserName(userProfile.getUsername());
        userProfileResponseDTO.setId(userProfile.getId());
        userProfileResponseDTO.setDisplayName(userProfile.getDisplayName());

        return userProfileResponseDTO;
    }
}
