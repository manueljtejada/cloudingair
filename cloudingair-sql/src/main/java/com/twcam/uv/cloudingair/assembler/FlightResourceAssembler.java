package com.twcam.uv.cloudingair.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.twcam.uv.cloudingair.controllers.FlightController;
import com.twcam.uv.cloudingair.domain.Flight;

@Component
public class FlightResourceAssembler implements ResourceAssembler<Flight, Resource<Flight>> {

	@Override
	public Resource<Flight> toResource(Flight flight) {
		Resource<Flight> flightResource = new Resource<>(flight,
				linkTo(methodOn(FlightController.class).findFlightById(flight.getId())).withSelfRel(),
				linkTo(methodOn(FlightController.class).findAllFlights()).withRel("mensaje"));
		return flightResource;
	}
		
}
