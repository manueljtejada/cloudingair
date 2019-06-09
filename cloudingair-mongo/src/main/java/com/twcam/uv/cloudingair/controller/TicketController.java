package com.twcam.uv.cloudingair.controller;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.domain.Purchase;
import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/register/tickets")
public class TicketController {
  @Autowired
  TicketService service;

  @Autowired
  RestTemplate restTemplate;

	@GetMapping()
  public Flux<Ticket> getAllTickets() {
    return service.findAll();
  }

  @GetMapping("/{ticketId}")
  public Mono<Ticket> getTicket(@PathVariable int ticketId) {
    return service.findById(ticketId);
  }

  @PutMapping("/{ticketId}/security")
  public Mono<Ticket> registerSecurityCheck(@PathVariable int ticketId, @RequestBody SecurityCheck securityCheck) {
    return service.registerSecurityCheck(ticketId, securityCheck);
  }

  @PutMapping("/{ticketId}/boarding")
  public Mono<Ticket> registerBoarding(@PathVariable int ticketId, @RequestBody Boarding boarding) {
    int airportId = restTemplate.getForObject("http://cloudingair-sql/api/tickets/" + ticketId + "/destination", Integer.class);
    boarding.setAirportId(airportId);
    return service.registerBoarding(ticketId, boarding);
  }

  @PutMapping("/{ticketId}/shopping/{storeId}")
  public Mono<Ticket> registerPurchase(@PathVariable int ticketId, @PathVariable String storeId, @RequestBody Purchase purchase) {
    return service.registerPurchase(ticketId, storeId, purchase);
  }
}
