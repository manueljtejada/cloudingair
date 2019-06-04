package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.Store;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store, String> {

}
