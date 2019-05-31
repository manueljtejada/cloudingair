package com.twcam.uv.cloudingair.repository;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
  @Modifying
  @Query("UPDATE Reservation r SET r.paid = True WHERE r.id = :reservationId")
  public void pay(@Param("reservationId") int reservationId);

  // @Query()
  // public List<Reservation> get
}
