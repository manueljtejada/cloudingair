package com.twcam.uv.cloudingair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twcam.uv.cloudingair.assembler.ReservationResourceAssembler;

import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.ReservationPassengerRepository;
import com.twcam.uv.cloudingair.service.ReservationService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

	@Autowired
	private ReservationPassengerRepository repository;


	@GetMapping("/{ticketId}/destination")
	public Integer findTicketDestination(@PathVariable("reservationId") int reservationId) {
		return repository.findById(reservationId)
      .orElse(null)
      .getReservation()
      .getOutboundFlight()
      .getDestination()
      .getId();
	}
}
