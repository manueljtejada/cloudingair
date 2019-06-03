package com.twcam.uv.cloudingair.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boarding {
  @Id
  private int id;

  private LocalDateTime time;

  private String gate;

  private int airportId;
}