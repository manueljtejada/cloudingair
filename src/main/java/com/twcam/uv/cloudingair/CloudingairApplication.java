package com.twcam.uv.cloudingair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Plane;
import com.twcam.uv.cloudingair.repository.AirportRepository;
import com.twcam.uv.cloudingair.repository.FlightRepository;
import com.twcam.uv.cloudingair.repository.PlaneRepository;
import com.twcam.uv.cloudingair.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.twcam.uv.cloudingair")
public class CloudingairApplication implements CommandLineRunner {
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirportRepository airportRepository;

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
		Airport origin = airportRepository.findById(3164).get();
		Airport destination = airportRepository.findById(4130).get();
		LocalDate date = LocalDate.of(2019, 10, 05);
		String outboundDate = date.toString();

		LocalDate date2 = LocalDate.of(2019, 10, 10);
		String returnDate = date2.toString();

		Map<String, List<Flight>> flights = flightService.findFlights(origin, destination, outboundDate, returnDate, true, 4);

		System.out.println("Found flights" + flights.size());
		System.out.println("Outbound flights" + flights.get("outbound").stream().map(flight -> flight.getId()).collect(Collectors.toList()));
		System.out.println("Return flights" + flights.get("return").stream().map(flight -> flight.getId()).collect(Collectors.toList()));
		// System.out.println("Flights found: " + flights.stream().map(flight -> flight.getId()).collect(Collectors.toList()));
	}

}
