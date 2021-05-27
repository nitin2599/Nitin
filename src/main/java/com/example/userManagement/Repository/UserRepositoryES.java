package com.example.userManagement.Repository;


import com.example.userManagement.model.index.UserIndexModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryES extends ElasticsearchRepository<UserIndexModel, UUID> {
}
