package com.twcam.uv.cloudingair.controller;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets/")
public class TicketController {
  @Autowired
  TicketRepository repository;

	@GetMapping()
  public List<Ticket> getAll() {
    return repository.findAll();
  }

}
