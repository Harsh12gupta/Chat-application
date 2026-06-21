package com.ca.userservice.repository;

import com.ca.userservice.model.UserProfile;
import org.apache.catalina.User;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    public boolean existsByUsernameAndIdNot(String username,UUID id);
    public Optional<UserProfile> findByUsername(String username);
    public List<UserProfile> findByUsernameContainingIgnoreCase(String username);
    boolean existsByEmail(String email);
}
