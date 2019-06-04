package com.twcam.uv.cloudingair.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SecurityCheck {
  @Id
  private ObjectId id;

  private LocalDate date;

  private LocalTime time;
}