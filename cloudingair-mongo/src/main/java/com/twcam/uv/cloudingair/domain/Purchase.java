package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
  @Id
  private ObjectId id;

  private LocalDate date;

  private Ticket ticket;

  private Store store;

  private float amount;

  public Purchase(float amount) {
    this.amount = amount;
  }
}