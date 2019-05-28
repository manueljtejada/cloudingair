package com.twcam.uv.cloudingair.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passengers")
public class Passenger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name")
  @Size(max = 15)
  private String firstName;

  @Column(name = "last_name")
  @Size(max = 30)
	private String lastName;

  @Column(name = "dni")
  @Size(max = 10)
	private String dni;

  @Column(name = "passport")
  @Size(max = 30)
	private String passport;
}