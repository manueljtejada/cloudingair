package com.twcam.uv.cloudingair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.SecurityCheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudingairApplication implements CommandLineRunner {

	@Autowired
	SecurityCheckRepository securityRepository;

	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boarding boarding = new Boarding(1, LocalDateTime.now(), "50A", 5);
		Ticket ticket = new Ticket(1, null, null, null);
		securityRepository.save(new SecurityCheck(1, LocalDate.of(2019, 06, 03), LocalTime.now(), ticket));
	}

}
