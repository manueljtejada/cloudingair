package com.twcam.uv.cloudingair.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
  @Id
  private int ticketId;

  private Boarding boarding;

  private SecurityCheck securityCheck;

  private List<Purchase> purchases;
}