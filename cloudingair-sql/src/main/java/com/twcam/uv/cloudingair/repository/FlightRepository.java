package com.twcam.uv.cloudingair.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

  /* Q1 */
  @Query("SELECT f "
  		+ "FROM Flight f "
  		+ "WHERE f.origin = :origin "
  		+ "AND f.destination = :destination "
  		+ "AND f.departureDate = DATE(:date)")
  public List<Flight> findFlights(
    @Param("origin") Airport origin,
    @Param("destination") Airport destination,
    @Param("date") String date
  );

  /* Q2 */
  public List<Flight> findByDepartureDateBetweenOrderByPrice(Date date1, Date date2);

  /* Q5.1 */
  @Modifying
  @Query("UPDATE Flight f SET f.cancelled = True WHERE f.id = :flightId AND DATEDIFF(f.departureDate, NOW()) <= 7")
  public void cancelFlight(@Param("flightId") int flightId);

  /* Q5.2 */

}
