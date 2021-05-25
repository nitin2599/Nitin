package com.example.userManagement.kafka;

import com.example.userManagement.model.EventMessage;
import com.example.userManagement.model.index.UserIndexModel;
import com.example.userManagement.service.UserServiceES;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@Slf4j
public class UserKafkaConsumer {
    public static final String EVENT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Autowired
    private UserServiceES userServiceES;

    @KafkaListener(groupId = "group_id", topics = "${spring.kafka.topics.event-topic}")
    public void receive(@Payload String messages) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        EventMessage eventMessage = objectMapper.readValue(messages, EventMessage.class);
        UserIndexModel uM = transformToUserIndexModel(eventMessage);
        log.info("sending it to elastic: {}:}", uM);
        userServiceES.saveUser(uM);
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat(EVENT_TIMESTAMP_FORMAT);
        objectMapper.setDateFormat(df);
        return objectMapper;
    }

    private UserIndexModel transformToUserIndexModel(EventMessage eventMessage) {
        return UserIndexModel.builder()
                .id(eventMessage.getUser().getId())
                .username(eventMessage.getUser().getUsername())
                .name(eventMessage.getUser().getFirstName())
                .phone(eventMessage.getUser().getPhone())
                .createdAt(getLocalDateTime(eventMessage))
                .build();
    }

    private LocalDateTime getLocalDateTime(EventMessage eventMessage) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(EVENT_TIMESTAMP_FORMAT);
        return LocalDateTime.parse(eventMessage.getTime(), df);
    }
}
