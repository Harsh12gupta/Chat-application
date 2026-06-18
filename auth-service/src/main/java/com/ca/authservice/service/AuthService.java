package com.ca.authservice.service;

import com.ca.authservice.dto.LoginRequestDTO;
import com.ca.authservice.dto.LoginResponseDTO;
import com.ca.authservice.dto.RegisterRequestDTO;
import com.ca.authservice.dto.RegisterResponseDTO;
import com.ca.authservice.exception.EmailAlreadyExistsException;
import com.ca.authservice.exception.InvalidPasswordException;
import com.ca.authservice.exception.UserNotFoundException;
import com.ca.authservice.kafka.KafkaProducer;
import com.ca.authservice.mapper.UserMapper;
import com.ca.authservice.model.User;
import com.ca.authservice.repository.UserRepository;
import com.ca.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final KafkaProducer kafkaProducer;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil,KafkaProducer kafkaProducer){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.kafkaProducer = kafkaProducer;
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO){
        String email = registerRequestDTO.getEmail();
        if(userRepository.existsByEmail(email))
        {
            throw new EmailAlreadyExistsException("user with this email already exists "+ registerRequestDTO.getEmail());
        }
        String password = registerRequestDTO.getPassword();
        String hashPassword = passwordEncoder.encode(password);
        registerRequestDTO.setPassword(hashPassword);
        User user = userRepository.save(UserMapper.toModel(registerRequestDTO));
        kafkaProducer.sendUserRegisteredEvent(user);
        return UserMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UserNotFoundException("user with "+  loginRequestDTO.getEmail()+ " email is not registered");
        }
        if(passwordEncoder.matches(password,user.get().getPasswordHash())){
            String token = jwtUtil.generateToken(user.get().getId());
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setAccessToken(token);
            return loginResponseDTO;
        }
        else
        {
            throw new InvalidPasswordException("password is invalid");
        }
    }
}
