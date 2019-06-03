package com.twcam.uv.cloudingair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.services.BoardingService;

@RestController
@RequestMapping("/boardings")
public class BoardingController {
	
	@Autowired
	private BoardingService boardingService;
	
	@GetMapping("/{ticketId}")
	public Boarding getBoarding(@PathVariable("ticketId") int ticketId) {
		Boarding boardingRequested;
		boardingRequested = boardingService.finBoardingById(ticketId);
		return boardingRequested;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) 
	public Boarding saveBoarding(@RequestBody Boarding boarding) {
		return boardingService.saveBoarding(boarding);
	}

}
