package com.twcam.uv.cloudingair.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation_passengers")
public class ReservationPassenger implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "checked_in")
  private boolean checkedIn;

  @Column(name = "priority_boarding")
  private boolean priorityBoarding;

  @Column(name = "bag_number")
  private int bagNumber;

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinColumn(name = "seat")
  @JsonIgnore
  private Seat seat;

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JsonIgnore
  private Reservation reservation;

  @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
  @JsonIgnore
  private Passenger passenger;
}