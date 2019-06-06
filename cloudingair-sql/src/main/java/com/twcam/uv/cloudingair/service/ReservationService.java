package com.twcam.uv.cloudingair.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

  @Autowired
  private ReservationPassengerRepository reservationPassengerRepository;

	@Autowired
	private AgencyRepository agencyRepository;

	public List<Reservation> findAllReservations() {
		return reservationRepository.findAll();
	}

	public Reservation findReservationById(int id) {
		return reservationRepository.findById(id).orElse(null);
	}

	public Reservation create(String reservationDate, String price, String paid, String outboundFlight, String returnFlight,
			String [] passengers, String agency) {
	  
	  Date resDate = Date.valueOf(LocalDate.parse(reservationDate));
	  Flight outAir = flightRepository.findById(Integer.parseInt(outboundFlight)).orElse(null);
	  Flight retAir = flightRepository.findById(Integer.parseInt(returnFlight)).orElse(null);
	  Agency ag = agencyRepository.findById(Integer.parseInt(agency)).orElse(null);
	  float pr = Float.parseFloat(price);
	  boolean pd = Boolean.parseBoolean(paid);
	  
	  List<ReservationPassenger> resPas = new ArrayList<>();
	  

		for (String id : passengers) {
			ReservationPassenger rp= reservationPassengerRepository.findById(Integer.parseInt(id)).get();
			resPas.add(rp);
		}
	  
  
	  Reservation reservation = new Reservation();
	  reservation.setReservationDate(resDate);
	  reservation.setOutboundFlight(outAir);
	  reservation.setPrice(pr);
	  reservation.setPaid(pd);
	  reservation.setReturnFlight(retAir);
	  reservation.setPassengers(resPas);
	  reservation.setAgency(ag);
	  
	  return reservationRepository.save(reservation);
//
	}
	
	public Reservation deleteReservation(int id) {
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		reservationRepository.delete(reservation);
		return reservation;
	}

	public List<Flight> findStatusReservation(int agencyId) {

		List<Integer> flightsId = reservationRepository.getPastReservations(agencyId);
		List<Flight> flights = new ArrayList<>();
		flightsId.forEach(id -> {
			Flight flight = flightRepository.findById(id).orElse(null);
			flights.add(flight);
		});
		return flights;
	}

	public ReservationPassenger changeReservationPassenger(int reservationId, int ticketId, Passenger newPassenger) {
		Reservation reservationToUpdate = reservationRepository.findById(reservationId).get();
		ReservationPassenger ticketToUpdate = reservationToUpdate.getPassengers().stream()
				.filter(p -> p.getId() == ticketId).findFirst().get();

		ticketToUpdate.setPassenger(newPassenger);
		ticketRepository.save(ticketToUpdate);
		return ticketToUpdate;
	}

	public List<MonthlyProfit> getMonthlyProfits() {
		LocalDate today = LocalDate.now();

		Date startDate = java.sql.Date.valueOf(today);
		Date endDate = java.sql.Date.valueOf(today.minusMonths(6));

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