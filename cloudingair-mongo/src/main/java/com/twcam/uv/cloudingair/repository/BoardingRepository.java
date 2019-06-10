package com.twcam.uv.cloudingair.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.twcam.uv.cloudingair.domain.Boarding;


public interface BoardingRepository extends MongoRepository<Boarding, Integer>{

	public Boarding findById(int id);

}
