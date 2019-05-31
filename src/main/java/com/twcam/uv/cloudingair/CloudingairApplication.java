package com.twcam.uv.cloudingair;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	public void TestConnection() {}

	@Override
	public void run(String... args) throws Exception {
		LocalDate loc1 = LocalDate.of(2019, 10, 19);
		LocalDate loc2 = LocalDate.of(2019, 10, 25);
		Date date1 = Date.valueOf(loc1);
		Date date2 = Date.valueOf(loc2);
		
		List<Flight> flights = flightRepository.findByDepartureDateBetweenOrderByPrice(date1, date2);		
		flights.stream()
				.forEach(flight -> System.out.println(flight.getDepartureDate() + " " + flight.getPrice()));
				

	}

}
