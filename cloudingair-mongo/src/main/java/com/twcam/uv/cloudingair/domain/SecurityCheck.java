package com.twcam.uv.cloudingair.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class SecurityCheck {
  @Id
  private ObjectId id;
  private LocalDateTime timestamp;
}