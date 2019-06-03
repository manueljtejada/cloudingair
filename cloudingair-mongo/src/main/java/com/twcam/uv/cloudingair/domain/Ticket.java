package com.twcam.uv.cloudingair.domain;

import java.util.List;

import com.twcam.uv.cloudingair.annotation.CascadeSave;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Ticket {
  @Id
  private int id;
}