package com.twcam.uv.cloudingair.repository;


import java.sql.Date;
import java.util.List;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

  @Query("select f from Flight f where f.origin = :origin and f.destination = :destination")
  public List<Flight> findFlights(
    @Param("origin") Airport origin,
    @Param("destination") Airport destination
  );
  
  
  public List<Flight> findByDepartureDateBetweenOrderByPrice(Date date1, Date date2);

}
