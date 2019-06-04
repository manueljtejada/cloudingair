package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;

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

  private LocalTime time;

  private Store store;

  private float amount;

  public Purchase(Store store, float amount) {
    this.amount = amount;
  }
}