package com.twcam.uv.cloudingair.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.twcam.uv.cloudingair.controllers.ReservationController;
import com.twcam.uv.cloudingair.domain.Reservation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ReservationResourceAssembler implements ResourceAssembler<Reservation, Resource<Reservation>> {

	@Override
	public Resource<Reservation> toResource(Reservation reservation) {
		Resource<Reservation> reservationResource = new Resource<> (reservation,
		linkTo(methodOn(ReservationController.class).findReservtion(reservation.getId())).withSelfRel(),
		linkTo(methodOn(ReservationController.class).findAllReservations()).withRel("mensaje"));
		
		return reservationResource;
	}

}
