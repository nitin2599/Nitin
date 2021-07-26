package com.example.userManagement.config;

import com.example.userManagement.kafka.KafkaProducerLoggerCallback;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.*;
import java.util.function.Function;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topics.event-topic}")
    private String eventTopic;

    @Bean
    public NewTopic createTopicEvent() {
        return new NewTopic(eventTopic, 2, (short) 1);
    }

    @Bean
    public <T> Function<T, KafkaProducerLoggerCallback<T>> kafkaCallback() {
        return KafkaProducerLoggerCallback::new;
    }


    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
