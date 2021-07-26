package com.example.userManagement.model.index;

import com.example.userManagement.model.Event;
import com.example.userManagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "users", type = "users")
@Data
public class UserIndexModel {

    @Id
    private UUID id ;

    @Field(type = FieldType.Nested, includeInParent = true)
    private User user;
    @Field(type = FieldType.Text)
    private Event event;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ssZZ")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "UserIndexModel{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", createdAt=" + createdAt +
                '}';
    }
}

