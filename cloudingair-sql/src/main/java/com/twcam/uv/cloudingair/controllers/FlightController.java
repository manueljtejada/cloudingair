package com.twcam.uv.cloudingair.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.service.AirportService;
import com.twcam.uv.cloudingair.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private AirportService airportService;

	@GetMapping
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
}
