package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.SecurityCheck;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecurityCheckRepository extends MongoRepository<SecurityCheck, Integer> {

}
