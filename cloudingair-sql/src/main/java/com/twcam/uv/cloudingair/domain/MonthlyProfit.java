package com.twcam.uv.cloudingair.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MonthlyProfit implements Serializable {
  private static final long serialVersionUID = 1L;

  private int month;
  private int year;
  private double profits;
}