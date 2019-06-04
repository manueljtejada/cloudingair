package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.twcam.uv.cloudingair.annotation.CascadeSave;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityCheck {
  @Id
  private ObjectId id;

  private LocalDate date;

  private LocalTime time;

  @DBRef
  @CascadeSave
  private Ticket ticket;

  public SecurityCheck(LocalDate date, LocalTime time, Ticket ticket) {
    this.date = date;
    this.time = time;
    this.ticket = ticket;
  }
}