package com.twcam.uv.cloudingair.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	
//	@PostMapping()
//	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation, BindingResult result) {
//		if (result.hasErrors()) {
//			return new ResponseEntity<>(reservation, HttpStatus.BAD_REQUEST);
//		}
//		
//		Reservation savedReservation = reservationService.create(reservation);
//		return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
//		
//	}

}
