package com.twcam.uv.cloudingair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twcam.uv.cloudingair.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
}
