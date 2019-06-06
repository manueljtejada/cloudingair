package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.domain.Ticket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TicketRepository extends ReactiveMongoRepository<Ticket, Integer> {

}
