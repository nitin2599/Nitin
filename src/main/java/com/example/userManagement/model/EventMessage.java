package com.example.userManagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventMessage {
    private String time;
    private User user;
    private Event event;

    @Override
    public String toString() {
        return "EventMessage{" +
                "time='" + time + '\'' +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
