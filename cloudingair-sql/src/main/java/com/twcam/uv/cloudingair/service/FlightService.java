package com.twcam.uv.cloudingair.service;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class FlightService {
	@Autowired
	FlightRepository flightRepository;
	
	public Optional<Flight>findFlightById(int id) {
		return flightRepository.findById(id);	
	}

	// Q1
	public Map<String, List<Flight>> findFlights(Airport origin, Airport destination, String outboundDate,
			String returnDate, boolean roundTrip, int totalPassengers) {
		Map<String, List<Flight>> flights = new HashMap<String, List<Flight>>();

		List<Flight> outboundFlights = flightRepository.findFlights(origin, destination, outboundDate);
		flights.put("outbound", outboundFlights);

		List<Flight> returnFlights = new ArrayList<Flight>();

		if (roundTrip) {
			returnFlights = flightRepository.findFlights(destination, origin, returnDate);
		}

		flights.put("return", returnFlights);
		return flights;
	}
	
	// Q2
	public List<Flight> findComingFlights(Date date1) {
		LocalDate rangeDate = date1.toLocalDate();
		Date previousThreeDays = Date.valueOf(rangeDate.plusDays(3));
		Date nextThreeDays = Date.valueOf(rangeDate.minusDays(3));

		List<Flight> flights = flightRepository.findByDepartureDateBetweenOrderByPrice(previousThreeDays,
				nextThreeDays);
		return flights;
	}

}