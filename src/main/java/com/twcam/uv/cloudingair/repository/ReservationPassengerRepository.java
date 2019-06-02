package com.twcam.uv.cloudingair.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.domain.Flight;
import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.Reservation;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ReservationPassengerRepository extends JpaRepository<ReservationPassenger, Integer> {
}
