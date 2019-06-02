package com.twcam.uv.cloudingair.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyProfit {
  private int month;
  private int year;
  private double profits;
}