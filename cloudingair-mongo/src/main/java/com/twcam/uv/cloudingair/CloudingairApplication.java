package com.twcam.uv.cloudingair;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.repository.BoardingRepository;


@SpringBootApplication
public class CloudingairApplication implements CommandLineRunner{
	
	@Autowired
	private BoardingRepository boardingRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Boarding boarding = new Boarding (1, LocalDateTime.now(), "22A", 56);
		boardingRepository.save(boarding);
		
		List<Boarding> boardingList = boardingRepository.findById(boarding.getId());
		boardingList.forEach(l -> System.out.println(l));
		

	}

}
