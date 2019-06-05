package com.twcam.uv.cloudingair.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;

import org.springframework.data.domain.Pageable;
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



//	@Query("SELECT r.outboundFlight, r.returnFlight, "
//	+ "CASE "
//	+ "WHEN DATEDIFF(now(), r.outboundFlight.departureDate) <= 0 THEN 'Past' "
//	+ "WHEN DATEDIFF(now(), r.outboundFlight.departureDate) >= 0 THEN 'Future' "
//	+ "WHEN DATEDIFF(now(), r.returnFlight.departureDate) <= 0 THEN 'Past' "
//	+ "WHEN DATEDIFF(now(), r.returnFlight.departureDate) >= 0 THEN 'Future' "
//	+ "END "
//	+ "FROM Reservation r "
//	+ "WHERE r.agency = :agency")


   @Query(value ="SELECT of.id, of.boarding_time, of.company, of.departure_date, "
   		+ "of.departure_time, of.duration, of.flight_number, of.price, "
   		+ "of.reservation_start_date, of.destination, of.origin, of.plane "
   		+ "FROM reservations r1 "
   		+ "INNER JOIN flights as of "
   		+ "ON of.id = r1.outbound_flight "
   		+ "WHERE of.departure_date < now() "
   		+ "AND r1.agency_id = :agency "
   		+ "UNION "
   		+ "SELECT rf.id, rf.boarding_time, rf.company, rf.departure_date, "
   		  		+ "rf.departure_time, rf.duration, rf.flight_number, rf.price, "
   		  		+ "rf.reservation_start_date, rf.destination, rf.origin, rf.plane "
   		+ "FROM reservations r2 "
   		+ "INNER JOIN flights as rf "
   		+ "ON rf.id = r2.return_flight "
   		+ "WHERE rf.departure_date < now() "
   		+ "AND r2.agency_id = :agency"
   		, nativeQuery = true )
   public List<Integer> getPastReservations(@Param("agency") int agency);

  /* Q3.2 */
  // @Query("SELECT r.outboundFlight FROM Reservation r WHERE r.agency = :agency AND r.outboundFlight.departureDate > NOW()")
  // public List<Flight> getFutureReservations(@Param("agency") Agency agency);

  /* Q4 */
  @Query("SELECT r.passengers"
  		+ " FROM Reservation r "
  		+ "WHERE r.id = :reservationId"
  		+ " AND r.agency = :agency"
  		+ " AND DATEDIFF(NOW(), r.outboundFlight.departureDate) = 1")
  public List<ReservationPassenger> getBoardingTickets(@Param("reservationId") int reservationId, @Param("agency") Agency agency);

  /* Q7 */
  @Query("SELECT r.outboundFlight.destination FROM Reservation r WHERE r.outboundFlight.departureDate BETWEEN '2019-01-31' AND :date GROUP BY (r.outboundFlight.destination) ORDER BY SUM(r.price) DESC")
  public List<Airport> findTop10Destinations(Pageable pageable, @Param("date") Date date);

  /* Q8 */
	@Query("SELECT new com.twcam.uv.cloudingair.domain.MonthlyProfit(MONTH(r.reservationDate), YEAR(r.reservationDate), SUM(r.price)) " +
			"FROM Reservation r " +
      "WHERE r.reservationDate BETWEEN :endDate AND :startDate " +
			"GROUP BY YEAR(r.reservationDate), MONTH(r.reservationDate) " +
			"ORDER BY YEAR(r.reservationDate) DESC, MONTH(r.reservationDate) DESC")
	public List<MonthlyProfit> getMonthlyProfits(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
