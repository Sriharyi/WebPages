package com.application.starter.service;

import java.time.Duration;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.Subscription;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.application.starter.model.User;
import com.application.starter.repositories.elastic.UserElasticRepository;
import com.mongodb.client.model.changestream.ChangeStreamDocument;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeStreamService {

    private final MessageListenerContainer messageListenerContainer;

    private final UserElasticRepository userElasticRepository;
    

    public void watchCollection() throws InterruptedException {

        messageListenerContainer.start();

        MessageListener<ChangeStreamDocument<Document>, User> messageListener = message -> {
            log.info("Received message: {}", message.getBody());
            userElasticRepository.save(message.getBody());
        };

        AggregationOperation match = Aggregation.match(Criteria.where("operationType").is("insert"));

        ChangeStreamRequest<User> request = ChangeStreamRequest.builder()
                .collection("users")
                .filter(Aggregation.newAggregation(match))
                .publishTo(messageListener)
                .build();

        Subscription subscription = messageListenerContainer.register(request, User.class);
           
        subscription.await(Duration.ofMillis(2000));
    }
  

    



}
