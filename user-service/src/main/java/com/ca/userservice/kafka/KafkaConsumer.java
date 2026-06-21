package com.ca.userservice.kafka;

import com.ca.userservice.service.UserProfileService;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import user.events.UserRegisteredEvent;


@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final UserProfileService userProfileService;

    public KafkaConsumer(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @KafkaListener(topics = "user",groupId = "user-service")
    public void consumeUserRegisteredEvent(byte[] eventBytes){
        try{
            UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent.parseFrom(eventBytes);
            if("USER_REGISTERED".equals(userRegisteredEvent.getEventType())) {
                log.info("Received user registeration info : user_id = {}, user_email={}",
                        userRegisteredEvent.getId(),userRegisteredEvent.getEmail());
                userProfileService.createProfile(userRegisteredEvent.getEmail(), userRegisteredEvent.getId());
            }
        } catch (InvalidProtocolBufferException e) {
            log.error("error parsing user-registered event bytes in kafka",e);
        }
    }
}
