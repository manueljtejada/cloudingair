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

@Service
public class TicketService {
  @Autowired
  private TicketRepository repository;

  @Autowired
  private StoreRepository storeRepository;

  public List<Ticket> findAll() {
    return repository.findAll();
  }

  public Ticket findById(int id) {
    return repository.findById(id).orElse(null);
  }

  public Ticket registerSecurityCheck(int ticketId, SecurityCheck securityCheck) {
    Ticket ticket = repository.findById(ticketId).orElse(null);

    securityCheck.setId(new ObjectId());
    securityCheck.setDate(LocalDate.now());
    securityCheck.setTime(LocalTime.now());

    ticket.setSecurityCheck(securityCheck);

    return repository.save(ticket);
  }

  public Ticket registerBoarding(int ticketId, Boarding boarding) {
    Ticket ticket = repository.findById(ticketId).orElse(null);

    boarding.setId(new ObjectId());
    boarding.setDate(LocalDate.now());
    boarding.setTime(LocalTime.now());

    ticket.setBoarding(boarding);

    return repository.save(ticket);
  }

  public Ticket registerPurchase(int ticketId, String storeId, Purchase purchase) {
    Ticket ticket = repository.findById(ticketId).orElse(null);
    Store store = storeRepository.findById(storeId).orElse(null);
    List<Purchase> existingPurchases = ticket.getPurchases();

    if (existingPurchases == null) {
      existingPurchases = new ArrayList<Purchase>();
    }

    purchase.setId(new ObjectId());
    purchase.setDate(LocalDate.now());
    purchase.setTime(LocalTime.now());
    purchase.setStore(store);

    existingPurchases.add(purchase);
    ticket.setPurchases(existingPurchases);

    return repository.save(ticket);
  }
}