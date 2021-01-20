package com.example.demo.repository.impl;

import com.example.demo.model.domain.complete.UserCompleteDomain;
import com.example.demo.model.domain.user.UserDomain;
import com.example.demo.repository.UserTemplateRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.replaceRoot;
import static org.springframework.data.mongodb.core.query.Criteria.*;

import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor

public class UserRepositoryImpl implements UserTemplateRepository {

    private final MongoTemplate mongoTemplate;

    private static final String USER_COLLECTION = "user";
    private static final String ADDRESS_GEO_LONG_FIELD = "address.geo.lng";
    private static final String ADDRESS_GEO_LAT_FIELD = "address.geo.lat";


    private static final String LOCAL_USER_ID = "_id";
    private static final String FOREIGN_USER_ID = "userId";

    private static final String FROM_TODO = "todo";
    private static final String AS_TODO = "todos";

    private static final String FROM_ALBUM = "album";
    private static final String AS_ALBUM = "albums";

    private static final String FROM_PHOTO = "photo";
    private static final String AS_PHOTO = "albums.photos";
    private static final String LOCAL_ALBUM_ID = "albums._id";
    private static final String FOREIGN_ALBUM_ID = "albumId";

    private static final String FROM_POST = "post";
    private static final String AS_POST = "posts";

    private static final String FROM_COMMENT = "comment";
    private static final String AS_COMMENT = "posts.comments";
    private static final String LOCAL_POST_ID = "posts._id";
    private static final String FOREIGN_POST_ID = "postId";


    @Override
    public List<UserDomain> getUserByGeoRanges(Double minLat, Double maxLat, Double minLong, Double maxLong) {

        Query query = new Query();
        Criteria criteria = where(ADDRESS_GEO_LONG_FIELD).gte(minLong).lte(maxLong);
        criteria.and(ADDRESS_GEO_LAT_FIELD).gte(minLat).lte(maxLat);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, UserDomain.class);
    }

    @Override
    public List<UserCompleteDomain> getCompleteUsers() {
        Aggregation aggregation = Aggregation.newAggregation(
                lookupTodoOperation(),
                lookupAlbumOperation(),
                Aggregation.unwind("$albums"),
                lookupPhotoOperation(),

                groupUsersAlbumOperation(),
                Aggregation.addFields().addFieldWithValue("user.albums", "$albums").build(),
                replaceRootOperation(),

                lookupPostOperation(),
                Aggregation.unwind("$posts"),
                lookupCommentOperation(),

                groupUsersPostOperation(),
                Aggregation.addFields().addFieldWithValue("user.posts", "$posts").build(),
                replaceRootOperation()
        );
        return mongoTemplate
                .aggregate(
                        aggregation,
                        "user",
                        UserCompleteDomain.class
                )
                .getMappedResults();
    }

    private LookupOperation lookupTodoOperation()
    {
        return LookupOperation.newLookup()
                .from(FROM_TODO)
                .localField(LOCAL_USER_ID)
                .foreignField(FOREIGN_USER_ID)
                .as(AS_TODO);
    }

    private LookupOperation lookupAlbumOperation()
    {
        return LookupOperation.newLookup()
                .from(FROM_ALBUM)
                .localField(LOCAL_USER_ID)
                .foreignField(FOREIGN_USER_ID)
                .as(AS_ALBUM);
    }

    private LookupOperation lookupPhotoOperation()
    {
        return LookupOperation.newLookup()
                .from(FROM_PHOTO)
                .localField(LOCAL_ALBUM_ID)
                .foreignField(FOREIGN_ALBUM_ID)
                .as(AS_PHOTO);
    }

    private GroupOperation groupUsersAlbumOperation()
    {
        return Aggregation
                .group("$_id")
                .first("$$ROOT")
                .as("user")
                .addToSet("$albums")
                .as("albums");
    }

    private GroupOperation groupUsersPostOperation()
    {
        return Aggregation
                .group("$_id")
                .first("$$ROOT")
                .as("user")
                .addToSet("$posts")
                .as("posts");
    }

    private ReplaceRootOperation replaceRootOperation()
    {
        Map<String, String> fieldMapping = new HashMap<>();
        fieldMapping.put("content", "$content");
        return replaceRoot().withValueOf(ObjectOperators.valueOf("$user").merge());
    }


    private LookupOperation lookupPostOperation()
    {
        return LookupOperation.newLookup()
                .from(FROM_POST)
                .localField(LOCAL_USER_ID)
                .foreignField(FOREIGN_USER_ID)
                .as(AS_POST);
    }

    private LookupOperation lookupCommentOperation()
    {
        return LookupOperation.newLookup()
                .from(FROM_COMMENT)
                .localField(LOCAL_POST_ID)
                .foreignField(FOREIGN_POST_ID)
                .as(AS_COMMENT);
    }

}
