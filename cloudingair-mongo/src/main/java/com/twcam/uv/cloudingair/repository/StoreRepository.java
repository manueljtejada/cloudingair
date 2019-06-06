package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.Store;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StoreRepository extends ReactiveMongoRepository<Store, String> {

}
