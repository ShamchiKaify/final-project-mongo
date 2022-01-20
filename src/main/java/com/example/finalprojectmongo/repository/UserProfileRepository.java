package com.example.finalprojectmongo.repository;

import com.example.finalprojectmongo.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    UserProfile findAllByUserName(String userId);
    Optional<UserProfile> findByUserName(String userName);
    Optional<UserProfile> findByNid(String nid);
    List<UserProfile> findAllByFullNameStartingWith(String startingPrefix);
    List<UserProfile> findAllByRoleContaining(String role);
}
