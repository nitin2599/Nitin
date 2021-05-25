package com.example.userManagement.service;

import com.datastax.oss.driver.shaded.guava.common.collect.Lists;
import com.example.userManagement.Repository.UserRepositoryES;
import com.example.userManagement.model.Query;
import com.example.userManagement.model.index.UserIndexModel;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceES {

    @Autowired
    private UserRepositoryES userRepositoryES;

    public UserIndexModel saveUser(UserIndexModel user) {
        return userRepositoryES.save(user);
    }

    public UserIndexModel findUserByName(Query query) {
        String name = query.toString();
        return userRepositoryES.findByName(name);
    }

    public List<UserIndexModel> findUserByQuery(Query query) {

        List<QueryBuilder> filter = new ArrayList<>();
        if (Objects.nonNull(query.getMatch()))
            filter.add(QueryBuilders.matchQuery("name", query.getMatch().get(0)));
        if (Objects.nonNull(query.getMultiMatch()))
            filter.add(QueryBuilders.multiMatchQuery(query.getMultiMatch().get(0)).field("name").field("username"));
        if (Objects.nonNull(query.getTerm()))
            filter.add(QueryBuilders.termQuery("username", query.getTerm().get(0)));
        if (Objects.nonNull(query.getTerms()))
            filter.add(QueryBuilders.termsQuery("name", query.getTerm()));
        if (Objects.nonNull(query.getRegexp()))
            filter.add(QueryBuilders.regexpQuery("phone", query.getRegexp().get(0).toString()));

        BoolQueryBuilder filter1 = QueryBuilders.boolQuery();

        for (QueryBuilder qb : filter) {
            filter1.must(qb);
        }

        Iterable<UserIndexModel> result = userRepositoryES.search(filter1);

        return Lists.newArrayList(result);
    }
}
