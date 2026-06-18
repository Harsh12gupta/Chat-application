package com.ca.authservice.mapper;

import com.ca.authservice.dto.RegisterRequestDTO;
import com.ca.authservice.dto.RegisterResponseDTO;
import com.ca.authservice.model.User;

public class UserMapper {
    public static User toModel(RegisterRequestDTO registerRequestDTO){
        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setPasswordHash(registerRequestDTO.getPassword());
        return user;
    }

    public static RegisterResponseDTO toDTO(User user){
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setEmail(user.getEmail());
        registerResponseDTO.setId(user.getId());
        return registerResponseDTO;
    }
}
