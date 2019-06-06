package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
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
    return repository.findAll();
  }

  public Mono<Ticket> findById(int id) {
    return repository.findById(id);
  }

  public Mono<Ticket> registerSecurityCheck(int ticketId, SecurityCheck securityCheck) {
    Mono<Ticket> ticketMono = repository.findById(ticketId);

    return ticketMono
      .flatMap(ticket -> {
        securityCheck.setId(new ObjectId());
        securityCheck.setDate(LocalDate.now());
        securityCheck.setTime(LocalTime.now());

        ticket.setSecurityCheck(securityCheck);

        return Mono.just(ticket);
      }).flatMap(repository::save);
  }

  public Mono<Ticket> registerBoarding(int ticketId, Boarding boarding) {
    Mono<Ticket> ticketMono = repository.findById(ticketId);

    return ticketMono
      .flatMap(ticket -> {
        boarding.setId(new ObjectId());
        boarding.setDate(LocalDate.now());
        boarding.setTime(LocalTime.now());

        ticket.setBoarding(boarding);

        return Mono.just(ticket);
      }).flatMap(repository::save);
  }

  public Mono<Ticket> registerPurchase(int ticketId, String storeId, Purchase purchase) {
    Mono<Ticket> ticketMono = repository.findById(ticketId);
    Mono<Store> storeMono = storeRepository.findById(storeId);

    return ticketMono
      .flatMap(ticket -> {
        List<Purchase> existingPurchases = ticket.getPurchases();

        if (existingPurchases == null) {
          existingPurchases = new ArrayList<Purchase>();
        }

        purchase.setId(new ObjectId());
        purchase.setDate(LocalDate.now());
        purchase.setTime(LocalTime.now());

        storeMono.flatMap(store -> {
          purchase.setStore(store);
          return Mono.just(store);
        });

        existingPurchases.add(purchase);
        ticket.setPurchases(existingPurchases);

        return Mono.just(ticket);
      }).flatMap(repository::save);
  }
}