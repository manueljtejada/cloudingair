package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "flight_number")
  @Size(max = 100)
	private String flightNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin")
	private Airport origin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination")
	private Airport destination;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plane")
	private Plane plane;

  @Column(name = "reservation_start_date")
	private LocalDate reservationStartDate;

  @Column(name = "departure_date")
  @Temporal(TemporalType.DATE)
	private Date departureDate;

  @Column(name = "boarding_time")
	private LocalTime boardingTime;

  @Column(name = "departure_time")
	private LocalTime departureTime;

  @Column(name = "duration")
	private int duration;

  @Column(name = "price")
  private float price;

  @Column(name = "company")
  @Size(max = 120)
	private String company;

  @Column(name = "cancelled", nullable = true)
  private boolean cancelled = false;

}
