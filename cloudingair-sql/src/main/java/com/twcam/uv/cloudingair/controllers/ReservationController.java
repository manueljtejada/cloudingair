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

import java.security.Principal;
import java.util.List;

import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.AgencyRepository;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/{agencyId}/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AgencyRepository agencyRepository;


	@GetMapping("/{reservationId}/tickets")
	public List<ReservationPassenger> getBoardingTickets(@PathVariable("agencyId") int agencyId, @PathVariable("reservationId") int reservationId, Principal principal) {
		// Solo permitir acceso para la agencia que realizo la reserva
		Agency agency = agencyRepository.findByUsername(principal.getName());
		return reservationService.getBoardingTicketList(reservationId, agency);
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

	@GetMapping("/topDestinations")
	public List<Airport> getTop10Destinations() {
		return reservationService.getTop10Destinations();
	}

	@GetMapping("/profits")
	public List<MonthlyProfit> getMonthlyProfits() {
		return reservationService.getMonthlyProfits();
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
