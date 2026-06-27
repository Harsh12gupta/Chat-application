package com.ca.chatservice.repository;

import com.ca.chatservice.model.Conversation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConversationRepository extends ReactiveMongoRepository<Conversation,String> {

}
