package com.twcam.uv.cloudingair.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.assembler.ReservationResourceAssembler;
import java.security.Principal;
import java.util.List;

import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Airport;
import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.AgencyRepository;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationResourceAssembler assembler;

	@Autowired
	private AgencyRepository agencyRepository;

	@GetMapping
	public ResponseEntity<?> findAllReservations(){
		List<Resource<Reservation>> reservations = reservationService.findAllReservations()
													.stream()
													.map(assembler::toResource)
													.collect(Collectors.toList());
		return new ResponseEntity<>(reservations, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createReservation(@RequestBody Map<String, String> reservation) {
		 System.out.println(reservation);

		String reservationDate = reservation.get("reservationDate");
		String price = reservation.get("price");
		String paid = reservation.get("paid");
		String outboundFlight = reservation.get("outboundFlight");
		String returnFlight = reservation.get("returnFlight");
		String passengers = reservation.get("passengers");
		String agency = reservation.get("agency");

	   String[]passengersString = passengers.split(" ");

		Reservation reservationCr = reservationService.create(reservationDate, price, paid, outboundFlight, returnFlight, passengersString, agency);
		Resource<Reservation> reservationResource = assembler.toResource(reservationCr);
		return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
		//return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(reservationResource);
	}

	@GetMapping("/{reservationId}")
	public Resource<Reservation> findReservtion(@PathVariable("reservationId") int reservationId) {
		Reservation reservation = reservationService.findReservationById(reservationId);
		return assembler.toResource(reservation);
	}


	@GetMapping("/{reservationId}/tickets")
	public List<ReservationPassenger> getBoardingTickets(Principal principal, @PathVariable("reservationId") int reservationId) {
		// Solo permitir acceso para la agencia que realizo la reserva
		Agency agency = agencyRepository.findByUsername(principal.getName());
		return reservationService.getBoardingTicketList(reservationId, agency);
	}


	@GetMapping("/flights/{flightId}/tickets")
	public List<ReservationPassenger> getFlightBoardingTickets(Principal principal, @PathVariable("flightId") int flightId) {
		Agency agency = agencyRepository.findByUsername(principal.getName());
		return reservationService.getFlightBoardingTickets(flightId, agency.getId());
	}

	// Q5.2
	@PutMapping("/reservations/{reservationId}/tickets/{ticketId}")
	public ResponseEntity<ReservationPassenger> changeTicket(Principal principal,
											@PathVariable("reservationId") int reservationId,
											@PathVariable("ticketId") int ticketId,
											@RequestBody Passenger newPassenger) {

		ReservationPassenger changedTicket = reservationService.changeReservationPassenger(reservationId, ticketId, newPassenger);
//		if(result.hasErrors()) {
//			return new ResponseEntity<>(cancelledFlight, HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<>(changedTicket, HttpStatus.OK);
	}

	@GetMapping("/topDestinations")
	public List<Airport> getTop10Destinations() {
		return reservationService.getTop10Destinations();
	}

	@GetMapping("/profits")
	public List<MonthlyProfit> getMonthlyProfits() {
		return reservationService.getMonthlyProfits();
	}

	@DeleteMapping("/{reservationId}")
	public ResponseEntity<?> deleteReservation(@PathVariable("reservationId") int reservationId) {
		Reservation reservation = reservationService.deleteReservation(reservationId);
		Resource<Reservation> reservationResource = assembler.toResource(reservation);
		return new ResponseEntity<>(reservationResource, HttpStatus.OK);
	}

}
