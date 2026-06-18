package com.ca.authservice.kafka;

import com.ca.authservice.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import user.events.UserRegisteredEvent;

@Component
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate kafkaTemplate;

    public KafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserRegisteredEvent(User user){
        UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent
                                                    .newBuilder()
                                                    .setEmail(user.getEmail())
                                                    .setId(user.getId().toString())
                                                    .setEventType("USER_REGISTERED")
                                                    .build();
        try {
            kafkaTemplate.send("user",userRegisteredEvent.toByteArray());
        }
        catch (Exception e){
            log.error("Error send UserRegistered event : {}",userRegisteredEvent,e);
        }
    }
}
