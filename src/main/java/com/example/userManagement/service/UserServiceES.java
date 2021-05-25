package com.example.userManagement.service;

import com.datastax.oss.driver.shaded.guava.common.collect.Lists;
import com.example.userManagement.Repository.UserRepositoryES;
import com.example.userManagement.model.EventMessage;
import com.example.userManagement.model.Query;
import com.example.userManagement.model.index.UserIndexModel;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class UserServiceES {

    public static final String EVENT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    @Autowired
    private UserRepositoryES userRepositoryES;

    public UserIndexModel saveUser(UserIndexModel user) {
        return userRepositoryES.save(user);
    }

    public List<UserIndexModel> findUserByQuery(Query query) {

        List<QueryBuilder> filter = new ArrayList<>();
        if (Objects.nonNull(query.getMatch()))
            filter.add(QueryBuilders.matchQuery("user.firstName", query.getMatch().get(0)));
        if (Objects.nonNull(query.getMultiMatch()))
            filter.add(QueryBuilders.multiMatchQuery(query.getMultiMatch().get(0)).field("user.firstName").field("user.lastName").field("user.username"));
        if (Objects.nonNull(query.getTerm()))
            filter.add(QueryBuilders.termQuery("user.username", query.getTerm().get(0)));
        if (Objects.nonNull(query.getTerms()))
            filter.add(QueryBuilders.termsQuery("user.firstName", query.getTerm()));
        if (Objects.nonNull(query.getRegexp()))
            filter.add(QueryBuilders.regexpQuery("user.phone", query.getRegexp().get(0).toString()));

        BoolQueryBuilder filter1 = QueryBuilders.boolQuery();

        for (QueryBuilder qb : filter) {
            filter1.must(qb);
        }

        Iterable<UserIndexModel> result = userRepositoryES.search(filter1);

        return Lists.newArrayList(result);
    }


    public List<UserIndexModel> userHistory(UUID uuid, long fromDate, long toDate, Integer limit, Integer offset) {
        MatchPhraseQueryBuilder userIdFilter = QueryBuilders.matchPhraseQuery("user.id", uuid.toString());

        LocalDateTime fromDateTime = getLocalDateTime(fromDate);
        RangeQueryBuilder timeRange = QueryBuilders.rangeQuery("createdAt")
                .from(fromDateTime)
                .to(fromDateTime.plusDays(toDate))
                .format(EVENT_TIMESTAMP_FORMAT);

        BoolQueryBuilder query = QueryBuilders.boolQuery().must(userIdFilter).must(timeRange);

        Iterable<UserIndexModel> result = userRepositoryES.search(query);
        return Lists.newArrayList(result);
    }


    private LocalDateTime getLocalDateTime(long epoch) {
        return Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
