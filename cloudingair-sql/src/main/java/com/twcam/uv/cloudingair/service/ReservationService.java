package com.twcam.uv.cloudingair.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.twcam.uv.cloudingair.domain.MonthlyProfit;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;
import com.twcam.uv.cloudingair.repository.PassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationPassengerRepository;
import com.twcam.uv.cloudingair.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
  ReservationPassengerRepository ticketRepository;

  @Autowired
  PassengerRepository passengerRepository;

  public void changeReservationPassenger(int reservationId, int ticketId, Passenger newPassenger) {
    Reservation reservationToUpdate = reservationRepository.findById(reservationId).get();
    ReservationPassenger ticketToUpdate = reservationToUpdate.getPassengers().stream().filter(p -> p.getId() == ticketId).findFirst().get();

    ticketToUpdate.setPassenger(newPassenger);
    ticketRepository.save(ticketToUpdate);
  }

  public List<MonthlyProfit> getMonthlyProfits(LocalDate date) {
    Date startDate = java.sql.Date.valueOf(date);
    Date endDate = java.sql.Date.valueOf(date.minusMonths(6));
		return reservationRepository.getMonthlyProfits(startDate, endDate);
	}

}