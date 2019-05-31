package com.twcam.uv.cloudingair.service;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.repository.FlightRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlightService {
  @Autowired
  FlightRepository flightRepository;

  public Map<String, List<Flight>> findFlights(Airport origin, Airport destination, String outboundDate, String returnDate, boolean roundTrip, int totalPassengers) {
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

}