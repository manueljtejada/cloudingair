package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityCheck {
  @Id
  private int id;

  private LocalDate date;

  private LocalTime time;

  private Ticket ticket;
}