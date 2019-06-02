package com.twcam.uv.cloudingair.repository;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

  public List<Plane> findAll();
  public Plane save(Plane plane);

}

