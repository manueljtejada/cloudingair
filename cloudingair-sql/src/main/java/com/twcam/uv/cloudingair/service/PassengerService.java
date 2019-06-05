package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.AgencyRepository;
import com.twcam.uv.cloudingair.repository.FlightRepository;
import com.twcam.uv.cloudingair.repository.PassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationPassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationRepository;

@Service
public class PassengerService {
  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  ReservationPassengerRepository ticketRepository;

  @Autowired
  PassengerRepository passengerRepository;

  public List<Passenger> getPriorityPassengers() {
    return ticketRepository.getCountPriority();
  }
}