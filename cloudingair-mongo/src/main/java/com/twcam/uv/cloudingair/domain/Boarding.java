package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.twcam.uv.cloudingair.annotation.CascadeSave;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boarding {
  @Id
  private ObjectId id;

  private LocalDate date;

  private LocalTime time;

  private String gate;

  private int airportId;

  public Boarding(String gate, int airportId, Ticket ticket) {
    this.gate = gate;
    this.airportId = airportId;
  }
}