package com.twcam.uv.cloudingair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.service.PassengerService;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

  @Autowired
  PassengerService passengerService;

  @GetMapping("/priority")
  public List<Passenger> getPriorityPassengers() {
    return passengerService.getPriorityPassengers();
  }
}