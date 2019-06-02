package com.twcam.uv.cloudingair;

import java.time.LocalDate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Plane;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.domain.Seat;
import com.twcam.uv.cloudingair.repository.AirportRepository;
import com.twcam.uv.cloudingair.repository.FlightRepository;
import com.twcam.uv.cloudingair.repository.PlaneRepository;
import com.twcam.uv.cloudingair.repository.ReservationPassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationRepository;
import com.twcam.uv.cloudingair.service.FlightService;
import com.twcam.uv.cloudingair.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.repository.FlightRepository;
import com.twcam.uv.cloudingair.repository.PlaneRepository;
import com.twcam.uv.cloudingair.repository.ReservationRepository;

@SpringBootApplication
@EntityScan("com.twcam.uv.cloudingair")
public class CloudingairApplication implements CommandLineRunner {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationPassengerRepository reservationPassengerReposistory;

	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}


	public void TestConnection() {
		// List<Flight> flights = flightRepository.findFlights(3164, 4130);
		// flights.forEach(flight -> System.out.println(flight.getId()));
		// Plane plane = new Plane(1, "Test", 180);
		// planeRepository.save(plane);

		// List<Plane> planes = planeRepository.findAll();
		// planes.forEach(p -> System.out.println(p.getModel()));
	}

	@Override
	public void run(String... args) throws Exception {
		// Q1
		// Airport origin = airportRepository.findById(3164).get();
		// Airport destination = airportRepository.findById(4130).get();
		// LocalDate date = LocalDate.of(2019, 10, 05);
		// String outboundDate = date.toString();

		LocalDate date2 = LocalDate.of(2019, 10, 10);
		// String returnDate = date2.toString();

		// Map<String, List<Flight>> flights = flightService.findFlights(origin, destination, outboundDate, returnDate, true, 4);

		// System.out.println("Found flights" + flights.size());
		// System.out.println("Outbound flights" + flights.get("outbound").stream().map(flight -> flight.getId()).collect(Collectors.toList()));
		// System.out.println("Return flights" + flights.get("return").stream().map(flight -> flight.getId()).collect(Collectors.toList()));

		// // Q3
		// Passenger passenger1 = new Passenger(1, "Manuel", "Tejada", "123456", "RD12345");

		// List<Passenger> passengers = new ArrayList<Passenger>();
		// passengers.add(passenger1);

		// Flight of = flightRepository.findById(3).get();
		// Flight rf = flightRepository.findById(4).get();

		// Seat seat = new Seat(1, 2, "A");

		// ReservationPassenger ticket = new ReservationPassenger();
		// List<ReservationPassenger> tickets = new ArrayList<ReservationPassenger>();

		// ticket.setCheckedIn(false);
		// ticket.setPriorityBoarding(false);
		// ticket.setBagNumber(2);
		// ticket.setPassenger(passenger1);
		// ticket.setSeat(seat);

		// Agency agency = new Agency(1, "agency1", "123456", null);

		Date resDate = java.sql.Date.valueOf(date2);

		// Reservation reservation = new Reservation(1, resDate, 200f, false, of, rf, tickets, agency);
		// ticket.setReservation(reservation);

		// tickets.add(ticket);
		// reservationRepository.save(reservation);
		// reservationRepository.pay(reservation.getId());

		// List<Flight> futureFlights = reservationRepository.getFutureReservations(agency);

		// 0List<Flight> pastFlights = reservationRepository.getPastReservations(agency.getId());

		// List<ReservationPassenger> boardingTickets = reservationRepository.getBoardingTickets(1, agency);

		// flightRepository.cancelFlight(1);
		// System.out.println("Q3: ");
		// pastFlights.forEach(f -> System.out.println(f.getId() + " " + f.getFlightNumber() + " " + f.getDepartureDate()));

		// Q5.2
		// Passenger newPassenger = new Passenger(2, "Pasajero", "Nuevo", "o2143421", "218412");
		// reservationService.changeReservationPassenger(1, 1, newPassenger);

		// Q7
		Pageable top10 = PageRequest.of(0, 10);
		List<Airport> topDest = reservationRepository.findTop10Destinations(top10, resDate);
		topDest.forEach(a -> System.out.println(a.getAirportName()));
	}

}
