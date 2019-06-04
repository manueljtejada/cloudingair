package com.twcam.uv.cloudingair.domain;

import java.io.Serializable;

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
public class Airport implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "ident")
  @Size(max = 100)
  private String ident;

  @Column(name = "airport_type")
  @Size(max = 200)
  private String typeAirport;

  @Column(name = "airport_name")
  @Size(max = 200)
  private String airportName;

  @Column(nullable = true, name = "elevation_ft")
  private Integer elevationFt;

  @Column(name = "continent")
  @Size(max = 20)
  private String continent;

  @Column(name = "iso_country")
  @Size(max = 20)
  private String isoCountry;

  @Column(name = "iso_region")
  @Size(max = 100)
  private String isoRegion;

  @Column(name = "municipality")
  @Size(max = 200)
  private String municipality;

  @Column(name = "gps_code")
  @Size(max = 40)
  private String gpsCode;

  @Column(name = "iata_code", nullable = true)
  @Size(max = 100)
  private String iataCode;

  @Column(name = "local_code")
  @Size(max = 100)
  private String localCode;

  // TODO buscar una mejor forma de almacenar las coordenadas
  @Column
  private String coordinates;

  // @Column(name = "latitude")
  // private Float latitude;

  // @Column(name = "longitude")
  // private Float longitude;
}