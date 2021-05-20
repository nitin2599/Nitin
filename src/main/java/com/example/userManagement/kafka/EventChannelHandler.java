package com.example.userManagement.kafka;



import com.example.userManagement.model.Event;
import com.example.userManagement.model.EventMessage;
import com.example.userManagement.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    public void publishUserEvent(Event eventType, User user){
        publish(createUserEvent(eventType,user));
    }



    private  EventMessage createUserEvent(Event eventType, User user) {
        return EventMessage.builder()
                .time(getCurrentTimestamp())
                .event(eventType)
                .user(user)
                .build();
    }


    private void publish(EventMessage msg) {
        kafkaTemplate.send(topic, msg.toString()).addCallback(callbackProvider.apply(msg.toString()));
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ofPattern(EVENT_TIMESTAMP_FORMAT).format(LocalDateTime.now(clock));
    }

    private String getFormattedDate(Date date) {
        return String.valueOf(Instant.ofEpochMilli(date.getTime()).getEpochSecond());
    }
}
