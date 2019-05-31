package com.twcam.uv.cloudingair.repository;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

  @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND f.departureDate = DATE(:date)")
  public List<Flight> findFlights(
    @Param("origin") Airport origin,
    @Param("destination") Airport destination,
    @Param("date") String date
  );

}
