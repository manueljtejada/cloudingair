package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.domain.Purchase;
import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.domain.Store;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.StoreRepository;
import com.twcam.uv.cloudingair.repository.TicketRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketService {
  @Autowired
  private TicketRepository repository;

  @Autowired
  private StoreRepository storeRepository;

  public Flux<Ticket> findAll() {
    return Flux.fromIterable(repository.findAll());
  }

  public Mono<Ticket> findById(int id) {
    return Mono.just(repository.findById(id).orElse(null));
  }

  public Mono<Ticket> registerSecurityCheck(int ticketId, SecurityCheck securityCheck) {
    Ticket ticket = repository.findById(ticketId).orElse(null);

    securityCheck.setId(new ObjectId());
    securityCheck.setTimestamp(LocalDateTime.now());
    ticket.setSecurityCheck(securityCheck);

    repository.save(ticket);

    return Mono.just(ticket);
  }

  public Mono<Ticket> registerBoarding(int ticketId, Boarding boarding) {
    Ticket ticket = repository.findById(ticketId).orElse(null);

    boarding.setId(new ObjectId());
    boarding.setTimestamp(LocalDateTime.now());
    ticket.setBoarding(boarding);

    repository.save(ticket);

    return Mono.just(ticket);
  }

  public Mono<Ticket> registerPurchase(int ticketId, String storeId, Purchase purchase) {
    Ticket ticket = repository.findById(ticketId).orElse(null);
    Store store = storeRepository.findById(storeId).orElse(null);

    List<Purchase> existingPurchases = ticket.getPurchases();

    if (existingPurchases == null) {
      existingPurchases = new ArrayList<Purchase>();
    }

    purchase.setId(new ObjectId());
    purchase.setTimestamp(LocalDateTime.now());
    purchase.setStore(store);

    existingPurchases.add(purchase);
    ticket.setPurchases(existingPurchases);

    repository.save(ticket);

    return Mono.just(ticket);
  }
}