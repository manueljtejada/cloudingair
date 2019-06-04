package com.twcam.uv.cloudingair.controller;

import com.twcam.uv.cloudingair.domain.Purchase;
import com.twcam.uv.cloudingair.service.ShoppingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/shopping")
public class PurchaseController {
  @Autowired
  ShoppingService service;

  @PostMapping(value = "/{storeId}/{ticketId}")
  public Purchase registerSale(@PathVariable("storeId") String storeId, @PathVariable("ticketId") int ticketId, @RequestBody Purchase purchase) {
    return service.registerSale(storeId, ticketId, purchase);
  }
}