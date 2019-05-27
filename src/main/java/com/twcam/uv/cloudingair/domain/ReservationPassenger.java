package com.twcam.uv.cloudingair.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_passenger")
public class ReservationPassenger {
  @Column(name = "checked_in")
  private boolean checkedIn;

  @Column(name = "priority_boarding")
  private boolean priorityBoarding;

  @Column(name = "bag_number")
  private int bagNumber;
}