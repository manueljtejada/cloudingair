package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.netflix.discovery.converters.Auto;
import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.SecurityCheckRepository;
import com.twcam.uv.cloudingair.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityCheckService {
  @Autowired
  private SecurityCheckRepository repository;

  @Autowired
  private TicketRepository ticketRepository;

  public SecurityCheck registerSecurityCheck(int ticketId) {
    // Buscar el objeto ticket en la base de datos
    Ticket ticket = ticketRepository.findById(ticketId).orElse(null);

    // Si no existe lo creamos y guardamos en la base de datos
    if (ticket == null) {
      ticket = new Ticket(ticketId, null, null, null);
      ticketRepository.save(ticket);
    }

    // Creamos un nuevo objeto security check
    SecurityCheck securityCheck = new SecurityCheck(LocalDate.now(), LocalTime.now(), ticket);

    // Le añadimos el security check al objeto ticket y lo guardamos
    // ticket.setSecurityCheck(securityCheck);

    // Guardamos el security check
    return repository.save(securityCheck);
  }

  public SecurityCheck getById(int id) {
    return repository.findById(id).get();
  }

  public List<SecurityCheck> getAll() {
    return repository.findAll();
  }
}