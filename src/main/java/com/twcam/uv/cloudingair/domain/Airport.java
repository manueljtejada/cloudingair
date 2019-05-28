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
@Table(name = "airports")
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "ident")
  @Size(max = 4)
  private String ident;

  @Column(name = "type_airport")
  @Size(max = 30)
  private String typeAirport;

  @Column(name = "airport_name")
  @Size(max = 30)
  private String airportName;

  @Column(name = "elevation_ft")
  private int elevationFt;

  @Column(name = "continent")
  @Size(max = 2)
  private String continent;

  @Column(name = "iso_country")
  @Size(max = 2)
  private String isoCountry;

  @Column(name = "iso_region")
  @Size(max = 5)
  private String isoRegion;

  @Column(name = "municipality")
  @Size(max = 30)
  private String municipality;

  @Column(name = "gps_code")
  @Size(max = 4)
  private String gpsCode;

  @Column(name = "iata_code")
  @Size(max = 10)
  private String iataCode;

  @Column(name = "local_code")
  @Size(max = 4)
  private String localCode;

  @Column(name = "latitude")
  private Float latitude;

  @Column(name = "longitude")
  private Float longitude;
}