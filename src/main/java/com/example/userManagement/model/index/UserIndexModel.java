package com.example.userManagement.model.index;

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
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ssZZ")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "UserIndexModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

