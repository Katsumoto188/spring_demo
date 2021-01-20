package com.example.demo.repository;

import com.example.demo.model.domain.complete.UserCompleteDomain;
import com.example.demo.model.domain.user.UserDomain;

import java.util.List;

public interface UserTemplateRepository {

    List<UserDomain> getUserByGeoRanges(Double minLat, Double maxLat, Double minLong, Double maxLong);

    List<UserCompleteDomain> getCompleteUsers();
}
