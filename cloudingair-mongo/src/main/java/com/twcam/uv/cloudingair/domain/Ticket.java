package com.twcam.uv.cloudingair.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket {
  @Id
  private int id;

  private int airportId;

  private SecurityCheck securityCheck;

  private Boarding boarding;

  private List<Purchase> purchases;

  public Ticket(int id, int airportId) {
    this.id = id;
    this.airportId = airportId;
  }
}