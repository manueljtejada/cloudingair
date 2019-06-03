package com.twcam.uv.cloudingair.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @NamedNativeQuery(
// name = "Reservation.getMonthlyProjects",
// query = "SELECT MONTH(reservation_date), YEAR(reservation_date), SUM(price)"
//   + "FROM reservations "
//   + "WHERE reservation_date BETWEEN DATE_SUB(NOW(), INTERVAL 6 MONTH) AND NOW() "
//   + "GROUP BY YEAR(reservation_date), MONTH(reservation_date) "
//   + "ORDER BY YEAR(reservation_date), MONTH(reservation_date) DESC "
// )

// @SqlResultSetMapping(
// name = "getMonthlyProjects",
// classes = {
//   @ConstructorResult(
//     targetClass = MonthlyProfit.class,
//     columns = {
//       @ColumnResult(name="month", type = Integer.class),
//       @ColumnResult(name="year", type = Integer.class),
//       @ColumnResult(name="profits", type = Double.class)
//     }
//   )
// })
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "reservation_date")
  @Temporal(TemporalType.DATE)
  private Date reservationDate;

  @Column(name = "price")
  private Float price;

  @Column(name = "paid", nullable = false)
  private boolean paid = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "outbound_flight")
  private Flight outboundFlight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "return_flight")
  private Flight returnFlight;

  @OneToMany(mappedBy = "reservation", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
  private List<ReservationPassenger> passengers;

  @ManyToOne
  private Agency agency;
}