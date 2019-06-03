package com.twcam.uv.cloudingair.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
  @Id
  private ObjectId id;

  private String name;

  public Store(String name) {
    this.name = name;
  }
}