package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;

import com.twcam.uv.cloudingair.domain.Purchase;
import com.twcam.uv.cloudingair.domain.Store;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.PurchaseRepository;
import com.twcam.uv.cloudingair.repository.StoreRepository;
import com.twcam.uv.cloudingair.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
  @Autowired
  PurchaseRepository repository;

  @Autowired
  TicketRepository ticketRepository;

  @Autowired
  StoreRepository storeRepository;

  public Purchase registerSale(String storeId, int ticketId, Purchase purchase) {
    // Buscar el objeto ticket y store en la base de datos
    Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
    Store store = storeRepository.findById(storeId).orElse(null);

    // Si no existe lo creamos y guardamos en la base de datos
    if (ticket == null) {
      ticket = new Ticket(ticketId);
      ticketRepository.save(ticket);
    }

    if (store == null) {
      // TODO handle exception
    }

    purchase.setDate(LocalDate.now());
    purchase.setStore(store);
    purchase.setTicket(ticket);

    repository.save(purchase);

    return purchase;
  }
}