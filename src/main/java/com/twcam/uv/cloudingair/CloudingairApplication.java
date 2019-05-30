package com.twcam.uv.cloudingair;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Plane;
import com.twcam.uv.cloudingair.repository.PlaneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.twcam.uv.cloudingair")
public class CloudingairApplication implements CommandLineRunner {
	@Autowired
	private PlaneRepository planeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CloudingairApplication.class, args);
	}

	public void TestConnection() {
		// Plane plane = new Plane(1, "Test", 180);
		// planeRepository.save(plane);

		// List<Plane> planes = planeRepository.findAll();
		// planes.forEach(p -> System.out.println(p.getModel()));
	}

	@Override
	public void run(String... args) throws Exception {
		Plane plane = new Plane(1, "Test", 180);
		planeRepository.save(plane);

		List<Plane> planes = planeRepository.findAll();
		planes.forEach(p -> System.out.println(p.getModel()));
	}

}
