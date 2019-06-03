package com.twcam.uv.cloudingair.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twcam.uv.cloudingair.repository.BoardingRepository;

import com.twcam.uv.cloudingair.domain.Boarding;

@Service
public class BoardingService {
	
	@Autowired
	private BoardingRepository boardingRepository;
	
	public List<Boarding> findAllBoardings() {
		return boardingRepository.findAll();
	}

	public Boarding saveBoarding(Boarding boarding) {
		Boarding boardingSaved = boarding;
		boardingRepository.save(boardingSaved);
		return boardingSaved;
	}
	
	public Boarding finBoardingById (int id) {
		return boardingRepository.findById(id);
	}
	
}
