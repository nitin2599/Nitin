package com.example.userManagement.kafka;


import com.example.userManagement.model.Event;
import com.example.userManagement.model.EventMessage;
import com.example.userManagement.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.function.Function;


@Slf4j
@Component
public class EventChannelHandler {

    public static final String EVENT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Function<String, KafkaProducerLoggerCallback<String>> callbackProvider;
    private final Clock clock;

    @Value("${spring.kafka.topics.event-topic}")
    private String topic;


    @Autowired
    public EventChannelHandler(KafkaTemplate<String, String> kafkaTemplate,
                               Function<String, KafkaProducerLoggerCallback<String>> callbackProvider,
                               Clock clock) {
        this.kafkaTemplate = kafkaTemplate;
        this.callbackProvider = callbackProvider;
        this.clock = clock;
    }

    public void publishUserEvent(Event eventType, User user) {
        publish(createUserEvent(eventType, user));
    }

    public void publishUserEventWithUserId(Event eventType, UUID uuid) {
        User user = new User();
        user.setId(uuid);
        publish(createUserEvent(eventType, user));
    }


    private EventMessage createUserEvent(Event eventType, User user) {
        return EventMessage.builder()
                .time(getCurrentTimestamp())
                .event(eventType)
                .user(user)
                .build();
    }


    private void publish(EventMessage msg) {
        try {
            ObjectMapper obj = new ObjectMapper();
            String messageToPublish = obj.writeValueAsString(msg);
            kafkaTemplate.send(topic, messageToPublish).addCallback(callbackProvider.apply(messageToPublish));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(EVENT_TIMESTAMP_FORMAT);
        return LocalDateTime.now().format(df);
    }


}
