package com.twcam.uv.cloudingair.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.repository.AirportRepository;

@Service
public class AirportService {
	
	@Autowired
	private AirportRepository airportRepository;
	
	public Optional<Airport> findAirpoirtById(int id) {
		return airportRepository.findById(id);
	}
}
