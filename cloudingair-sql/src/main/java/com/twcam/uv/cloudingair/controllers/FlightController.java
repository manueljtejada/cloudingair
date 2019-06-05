package com.twcam.uv.cloudingair.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.service.AirportService;
import com.twcam.uv.cloudingair.service.FlightService;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private AirportService airportService;
	
	@Autowired 
	private ReservationService reservationService;
	
	// Q1
	@GetMapping("/flights")
	public Map<String, List<Flight>> findAllFlightsAvailable(
			@RequestParam int origin, @RequestParam int destination, 
			@RequestParam String outboundDate,
			@RequestParam String returnDate, 
			@RequestParam boolean roundTrip, @RequestParam int totalPassenger)
	{
		Airport originA = airportService.findAirpoirtById(origin).orElse(null);
		Airport destinationA = airportService.findAirpoirtById(destination).orElse(null);
		Map<String, List<Flight>>flights = flightService.findFlights(originA, destinationA, outboundDate, returnDate, roundTrip, totalPassenger);
		
		return flights;
	}
	
	// Q2
	@GetMapping("flights/alternatives")
	public List<Flight> findAlternativeFlights(@RequestParam("date") String date){
		Date comingDate = Date.valueOf(date);
		List<Flight> comingFlights = flightService.findComingFlights(comingDate);
		return comingFlights;
	}
	
	// Q3
	@GetMapping("/{agencyId}/status")
	public List<Flight> findStatusFlights(@PathVariable("agencyId") int agencyId){
		
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		
		List<Flight> flightStatus = reservationService.findStatusReservation(agencyId);
//		return new ResponseEntity<>(flightStatus, HttpStatus.CREATED);
		return flightStatus;
	}
	
	// Q5.1
	@PutMapping("{agencyId}/flights/{flightId}/cancel")
	public ResponseEntity<Flight> cancelFlight(@PathVariable("agencyId") int agencyId,
			@PathVariable("flightId") int flightId) {
		
		Flight cancelledFlight = flightService.updateReservation(flightId);
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(cancelledFlight, HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<>(cancelledFlight, HttpStatus.CREATED);
	}
	

}
