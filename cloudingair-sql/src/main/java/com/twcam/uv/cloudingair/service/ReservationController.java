package com.twcam.uv.cloudingair.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.domain.Reservation;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@AutoWired
	private ReservationService reservationService;
	
	
	@PostMapping
	public Reservation createReservation() {
		
	}

}
