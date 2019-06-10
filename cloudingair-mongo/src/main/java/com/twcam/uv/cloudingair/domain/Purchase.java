package com.twcam.uv.cloudingair.domain;

import java.time.LocalDateTime;

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

  private LocalDateTime timestamp;

  private Store store;

  private float amount;

  public Purchase(Store store, float amount) {
    this.amount = amount;
  }
}