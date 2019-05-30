package com.twcam.uv.cloudingair.repository;

import com.twcam.uv.cloudingair.domain.Airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
