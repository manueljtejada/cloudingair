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
public class Boarding {
  @Id
  private ObjectId id;

  private LocalDateTime timestamp;

  private String gate;

  private int airportId;

  public Boarding(String gate, int airportId) {
    this.gate = gate;
    this.airportId = airportId;
  }
}