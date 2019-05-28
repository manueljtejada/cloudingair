package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "reservation_date")
  private LocalDate reservationDate;

  @Column(name = "price")
  private Float price;

  @Column(name = "paid")
  private Boolean paid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "outbound_flight")
  private Flight outboundFlight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "return_flight")
  private Flight returnFlight;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id")
  private List<ReservationPassenger> passengers;
}