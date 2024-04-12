package com.application.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;

@Configuration
public class ApplicationConfig {

    @Bean
    MessageListenerContainer messageListenerContainer(MongoTemplate mongoTemplate) {
        return new DefaultMessageListenerContainer(mongoTemplate);
    }

}
