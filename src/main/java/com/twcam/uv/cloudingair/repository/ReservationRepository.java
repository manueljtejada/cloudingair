package com.twcam.uv.cloudingair.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
  @Modifying
  @Query("UPDATE Reservation r SET r.paid = True WHERE r.id = :reservationId")
  public void pay(@Param("reservationId") int reservationId);

  /* Q3.1 */
  // @Query("SELECT r.outboundFlight, r.returnFlight FROM Reservation r WHERE r.agency = :agency AND r.outboundFlight.departureDate < NOW() OR r.returnFlight.departureDate < NOW()")
  // public List<Flight> getPastReservations(@Param("agency") Agency agency);

  @Query("SELECT r.outboundFlight, r.returnFlight FROM Reservation r CASE WHEN (r.outboundFlight.departureDate < NOW()) THEN \'Past\' WHEN (r.outboundFlight.departureDate > NOW()) THEN \'Future\' (WHEN r.returnFlight.departureDate < NOW()) THEN \'Past\' WHEN (r.returnFlight.departureDate > NOW()) THEN \'Future\' END status WHERE r.agency = :agency")
  public List<Flight> getPastReservations(@Param("agency") Agency agency);

  /* Q3.2 */
  // @Query("SELECT r.outboundFlight FROM Reservation r WHERE r.agency = :agency AND r.outboundFlight.departureDate > NOW()")
  // public List<Flight> getFutureReservations(@Param("agency") Agency agency);

  /* Q4 */
  @Query("SELECT r.passengers FROM Reservation r WHERE r.id = :reservationId AND r.agency = :agency AND DATEDIFF(NOW(), r.outboundFlight.departureDate) = 1")
  public List<ReservationPassenger> getBoardingTickets(@Param("reservationId") int reservationId, @Param("agency") Agency agency);
}
