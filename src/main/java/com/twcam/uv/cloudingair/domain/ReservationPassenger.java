package com.twcam.uv.cloudingair.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_passengers")
public class ReservationPassenger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "checked_in")
  private boolean checkedIn;

  @Column(name = "priority_boarding")
  private boolean priorityBoarding;

  @Column(name = "bag_number")
  private int bagNumber;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat")
  private Seat seat;
}