package com.twcam.uv.cloudingair.domain;

import java.util.List;

import com.twcam.uv.cloudingair.annotation.CascadeSave;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
  @Id
  private int ticketId;

  @DBRef
  @CascadeSave
  private SecurityCheck securityCheck;

  @DBRef
  @CascadeSave
  private Boarding boarding;

  @DBRef
  @CascadeSave
  private List<Purchase> purchases;

  public Ticket(SecurityCheck securityCheck, Boarding boarding, List<Purchase> purchases) {
    this.securityCheck = securityCheck;
    this.boarding = boarding;
    this.purchases = purchases;
  }
}