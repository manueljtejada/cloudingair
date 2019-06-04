package com.twcam.uv.cloudingair;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.domain.Store;
import com.twcam.uv.cloudingair.repository.BoardingRepository;
import com.twcam.uv.cloudingair.repository.PurchaseRepository;
import com.twcam.uv.cloudingair.repository.StoreRepository;


@SpringBootApplication
public class CloudingairApplication implements CommandLineRunner{

	@Autowired
	private BoardingRepository boardingRepository;

	@Autowired
	private StoreRepository storeRepository;


	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Store store1 = new Store("Sunglass Hut");
		Store store2 = new Store("Taco Bell");
		Store store3 = new Store("Zara");
		Store store4 = new Store("Burger King");
		Store store5 = new Store("Starbucks");

		storeRepository.save(store1);
		storeRepository.save(store2);
		storeRepository.save(store3);
		storeRepository.save(store4);
		storeRepository.save(store5);

//		Boarding boarding = new Boarding (1, LocalDateTime.now(), "22A", 56);
//		boardingRepository.save(boarding);
//
//		List<Boarding> boardingList = boardingRepository.findById(boarding.getId());
//		boardingList.forEach(l -> System.out.println(l));


	}

}
