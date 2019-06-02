package com.twcam.uv.cloudingair.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.twcam.uv.cloudingair.domain.Passenger;
import com.twcam.uv.cloudingair.domain.ReservationPassenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ReservationPassengerRepository extends JpaRepository<ReservationPassenger, Integer> {
	@Query("SELECT r.passenger "
			+ "FROM ReservationPassenger r "
			+ "WHERE r.priorityBoarding = true "
			+ "GROUP BY r.passenger.id "
			+ "HAVING COUNT (r.passenger.id) > 2")
	public List<Passenger> getCountPriority();
}
