package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twcam.uv.cloudingair.domain.Agency;
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

  @Autowired
  private ReservationPassengerRepository reservationPassengerRepository;

  @Autowired
  private AgencyRepository agencyRepository;

  public Reservation create(String reservationDate, float price, boolean paid, int outboundFlight,
		  int returnFlight, List<Integer> passengers, int agency) {

	  Date resDate = Date.valueOf(LocalDate.parse(reservationDate));
	  Flight outAir = flightRepository.findById(outboundFlight).orElse(null);
	  Flight retAir = flightRepository.findById(returnFlight).orElse(null);
	  Agency ag = agencyRepository.findById(agency).orElse(null);
	  List<ReservationPassenger> resPas = new ArrayList<>();
	  passengers.forEach(passenger -> {
		  ReservationPassenger rp = reservationPassengerRepository.findById(passenger).orElse(null);
		  resPas.add(rp);
	  });

	  Reservation reservation = new Reservation();
	  reservation.setReservationDate(resDate);
	  reservation.setOutboundFlight(outAir);
	  reservation.setPrice(price);
	  reservation.setPaid(paid);
	  reservation.setReturnFlight(retAir);
	  reservation.setPassengers(resPas);
	  reservation.setAgency(ag);

	  return reservationRepository.save(reservation);
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
    return reservationRepository.getBoardingTickets(reservationId, agencyId);
  }

  public List<ReservationPassenger> getFlightBoardingTickets(int flightId, int agencyId) {
    Agency agency = agencyRepository.findById(agencyId).get();
    return reservationRepository.getFlightBoardingTickets(flightId, agency);
  }

}