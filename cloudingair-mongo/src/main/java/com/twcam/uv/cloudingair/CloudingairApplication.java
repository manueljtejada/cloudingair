package com.twcam.uv.cloudingair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.twcam.uv.cloudingair.domain.Boarding;
import com.twcam.uv.cloudingair.domain.Store;
import com.twcam.uv.cloudingair.domain.Ticket;
import com.twcam.uv.cloudingair.repository.StoreRepository;
import com.twcam.uv.cloudingair.repository.TicketRepository;


@SpringBootApplication
public class CloudingairApplication implements CommandLineRunner{
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker();

		Store store1 = new Store("Sunglass Hut", 5);
		Store store2 = new Store("Taco Bell", 2);
		Store store3 = new Store("Zara", 8);
		Store store4 = new Store("Burger King", 10);
		Store store5 = new Store("Starbucks", 20);

		storeRepository.save(store1);
		storeRepository.save(store2);
		storeRepository.save(store3);
		storeRepository.save(store4);
		storeRepository.save(store5);

		for (int i = 51; i <= 301; i++) {
			Ticket ticket = new Ticket(i, faker.number().numberBetween(1, 100));
			ticketRepository.save(ticket);
		}

	}

}
