package com.twcam.uv.cloudingair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twcam.uv.cloudingair.domain.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer> {
  public Agency findByUserName(String username);
}
