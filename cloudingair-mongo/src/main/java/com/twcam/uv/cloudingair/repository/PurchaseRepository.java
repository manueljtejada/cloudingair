package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.Purchase;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, Integer> {

}
