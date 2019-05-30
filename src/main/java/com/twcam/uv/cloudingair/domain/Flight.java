package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "flight_number")
  @Size(max = 10)
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
	private LocalDate departureDate;

  @Column(name = "boarding_time")
	private LocalTime boardingTime;

  @Column(name = "departure_time")
	private LocalTime departureTime;

  @Column(name = "duration")
	private int duration;

  @Column(name = "company")
  @Size(max = 120)
	private String company; 
  
}