package com.ujjawal.project.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.ujjawal.project.entity.UserCredentials;

public interface UserRepository extends MongoRepository<UserCredentials, String> {

}
