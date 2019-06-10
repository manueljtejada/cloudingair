package com.twcam.uv.cloudingair.repository;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
