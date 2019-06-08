package com.twcam.uv.cloudingair.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.assembler.FlightResourceAssembler;
import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.AgencyRepository;
import com.twcam.uv.cloudingair.service.AirportService;
import com.twcam.uv.cloudingair.service.FlightService;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportService airportService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AgencyRepository agencyRepository;

	@Autowired
	private FlightResourceAssembler flightAssembler;

	@GetMapping
	public ResponseEntity<?> findAllFlights() {
		List<Resource<Flight>> flights = flightService.findAllFlights()
														.stream()
														.map(flightAssembler::toResource)
														.collect(Collectors.toList());
		return new ResponseEntity<>(flights, HttpStatus.OK);
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<?> findFlightById(@PathVariable("flightId") int flightId) {
		Flight flight = flightService.findFlightById(flightId);
		Resource<Flight> flightResource = flightAssembler.toResource(flight);
		return new ResponseEntity<>(flightResource, HttpStatus.OK);

	}
	// Q1
	@GetMapping("/search")
	public Map<String, List<Flight>> findAllFlightsAvailable(
			@RequestParam int origin, @RequestParam int destination,
			@RequestParam String outboundDate,
			@RequestParam String returnDate,
			@RequestParam boolean roundTrip,
			@RequestParam int totalPassenger)
	{
		Airport originA = airportService.findAirpoirtById(origin).orElse(null);
		Airport destinationA = airportService.findAirpoirtById(destination).orElse(null);
		Map<String, List<Flight>>flights = flightService.findFlights(originA, destinationA, outboundDate, returnDate, roundTrip, totalPassenger);

		return flights;
	}
//
//	@GetMapping("/test")
//	public Agency user(Principal principal)
//	{
//		Agency agency = agencyRepository.findByUsername(principal.getName());
//		// System.out.println("Agencia en el controlador: " + agency);
//		return agency;
//	}

	// Q2
	@GetMapping("/alternatives")
	public List<Flight> findAlternativeFlights(@RequestParam("date") String date){
		Date comingDate = Date.valueOf(date);
		List<Flight> comingFlights = flightService.findComingFlights(comingDate);
		return comingFlights;
	}

	// Q3
	@GetMapping("/status")
	public List<Flight> findStatusFlights(Principal principal) {

//		if(result.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		Agency agency = agencyRepository.findByUsername(principal.getName());

		List<Flight> flightStatus = reservationService.findStatusReservation(agency.getId());
//		return new ResponseEntity<>(flightStatus, HttpStatus.CREATED);
		return flightStatus;
	}

	@GetMapping("/{flightId}/tickets")
	public List<ReservationPassenger> getFlightBoardingTickets(Principal principal,
			@PathVariable("flightId") int flightId) {

		Agency agency = agencyRepository.findByUsername(principal.getName());

		return reservationService.getFlightBoardingTickets(flightId, agency.getId());
	}

	// Q5.1
	@PutMapping("/{flightId}/cancel")
	public ResponseEntity<Flight> cancelFlight(Principal principal,
			@PathVariable("flightId") int flightId) {

		Flight cancelledFlight = flightService.updateReservation(flightId);
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(cancelledFlight, HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<>(cancelledFlight, HttpStatus.CREATED);
	}

}
