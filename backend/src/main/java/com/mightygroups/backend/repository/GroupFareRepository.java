package com.mightygroups.backend.repository;

import com.mightygroups.backend.model.GroupFare;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupFareRepository extends MongoRepository<GroupFare, String> {
    List<GroupFare> findByFromAndTo(String from, String to);
    List<GroupFare> findByUploadedBy(String uploadedBy);
}
