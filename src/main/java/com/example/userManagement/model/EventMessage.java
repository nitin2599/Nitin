package com.example.userManagement.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
