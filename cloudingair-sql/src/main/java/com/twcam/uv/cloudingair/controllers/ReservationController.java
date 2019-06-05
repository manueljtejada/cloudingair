package com.twcam.uv.cloudingair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping("{agencyId}/reservations/{reservationId}/tickets")
	public List<ReservationPassenger> getBoardingTickets(@PathVariable("agencyId") int agencyId, @PathVariable("reservationId") int reservationId) {
		// Solo permitir acceso para la agencia que realizo la reserva
		return reservationService.getBoardingTicketList(reservationId, agencyId);
	}

	@GetMapping("{agencyId}/flights/{flightId}/tickets")
	public List<ReservationPassenger> getFlightBoardingTickets(@PathVariable("agencyId") int agencyId, @PathVariable("flightId") int flightId) {
		return reservationService.getFlightBoardingTickets(flightId, agencyId);
	}

	@GetMapping("/topDestinations")
	public List<Airport> getTop10Destinations() {
		return reservationService.getTop10Destinations();
	}


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
