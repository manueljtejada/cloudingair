package com.twcam.uv.cloudingair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/reservations/{agencyId}")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/reservations/{reservationId}/tickets")
	public List<ReservationPassenger> getBoardingTickets(@PathVariable("agencyId") int agencyId, @PathVariable("reservationId") int reservationId) {
		// Solo permitir acceso para la agencia que realizo la reserva
		return reservationService.getBoardingTicketList(reservationId, agencyId);
	}

	@GetMapping("/flights/{flightId}/tickets")
	public List<ReservationPassenger> getFlightBoardingTickets(@PathVariable("agencyId") int agencyId, @PathVariable("flightId") int flightId) {
		return reservationService.getFlightBoardingTickets(flightId, agencyId);
	}
	
	// Q5.2
	@PutMapping("/reservations/{reservationId}/tickets/{ticketId}")
	public ResponseEntity<ReservationPassenger> changeTicket(@PathVariable("agencyId") int agencyId,
											@PathVariable("reservationId") int reservationId,
											@PathVariable("ticketId") int ticketId,
											@RequestBody Passenger newPassenger) {
		
		ReservationPassenger changedTicket = reservationService.changeReservationPassenger(reservationId, ticketId, newPassenger);
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(cancelledFlight, HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<>(changedTicket, HttpStatus.CREATED);
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
