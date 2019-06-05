package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.AgencyRepository;
import com.twcam.uv.cloudingair.repository.FlightRepository;
import com.twcam.uv.cloudingair.repository.PassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationPassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationRepository;

@Service
public class ReservationService {
  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  ReservationPassengerRepository ticketRepository;

  @Autowired
  PassengerRepository passengerRepository;

  @Autowired
  private FlightRepository flightRepository;


//  @Autowired
//  private ReservationPassengerRepository reservationPassengerRepository;
//
 @Autowired
 private AgencyRepository agencyRepository;
//
//  public Reservation create(String reservationDate, float price, boolean paid, int outboundFlight,
//		  int returnFlight, List<Integer> passengers, int agency) {
//
//	  Date resDate = Date.valueOf(LocalDate.parse(reservationDate));
//	  Flight outAir = flightRepository.findById(outboundFlight).orElse(null);
//	  Flight retAir = flightRepository.findById(returnFlight).orElse(null);
//	  Agency ag = agencyRepository.findById(agency).orElse(null);
//	  List<ReservationPassenger> resPas = new ArrayList<>();
//	  passengers.forEach(passenger -> {
//		  ReservationPassenger rp = reservationPassengerRepository.findById(passenger).orElse(null);
//		  resPas.add(rp);
//	  });
//
//	  Reservation reservation = new Reservation();
//	  reservation.setReservationDate(resDate);
//	  reservation.setOutboundFlight(outAir);
//	  reservation.setPrice(price);
//	  reservation.setPaid(paid);
//	  reservation.setReturnFlight(retAir);
//	  reservation.setPassengers(resPas);
//	  reservation.setAgency(ag);
//
//	  return reservationRepository.save(reservation);
//  }

  public List<Flight> findStatusReservation(int agencyId){

	  List<Integer> flightsId = reservationRepository.getPastReservations(agencyId);
	  List<Flight> flights = new ArrayList<>();
	  flightsId.forEach(id -> {
		 Flight flight = flightRepository.findById(id).orElse(null);
		 flights.add(flight);
	  });
	  return flights;
  }

  public void changeReservationPassenger(int reservationId, int ticketId, Passenger newPassenger) {
    Reservation reservationToUpdate = reservationRepository.findById(reservationId).get();
    ReservationPassenger ticketToUpdate = reservationToUpdate.getPassengers().stream().filter(p -> p.getId() == ticketId).findFirst().get();

    ticketToUpdate.setPassenger(newPassenger);
    ticketRepository.save(ticketToUpdate);
  }

  public List<MonthlyProfit> getMonthlyProfits(LocalDate date) {
    Date startDate = java.sql.Date.valueOf(date);
    Date endDate = java.sql.Date.valueOf(date.minusMonths(6));
	return reservationRepository.getMonthlyProfits(startDate, endDate);
  }

  public List<ReservationPassenger> getBoardingTicketList(int reservationId, int agencyId) {
    Agency agency = agencyRepository.findById(agencyId).orElse(null);
    return reservationRepository.getBoardingTickets(reservationId, agency);
  }

  public List<ReservationPassenger> getFlightBoardingTickets(int flightId, int agencyId) {
    Agency agency = agencyRepository.findById(agencyId).orElseGet(null);
    return reservationRepository.getFlightBoardingTickets(flightId, agency);
  }

  public List<Airport> getTop10Destinations() {
    LocalDate today = LocalDate.now();
    LocalDate oneMonthEarlier = today.minusMonths(1);

    Pageable top10 = PageRequest.of(0, 10);
    Date dToday = java.sql.Date.valueOf(today);
    Date dOneMonthEarlier = java.sql.Date.valueOf(oneMonthEarlier);

    return reservationRepository.findTop10Destinations(top10, dToday, dOneMonthEarlier);
  }

}