package com.twcam.uv.cloudingair.controllers;

import java.util.List;

import com.twcam.uv.cloudingair.domain.Agency;
import com.twcam.uv.cloudingair.repository.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  // @Autowired
  private AgencyRepository agencyRepository;

  // @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserController(AgencyRepository agencyRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.agencyRepository = agencyRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

  @PostMapping
  public void saveUser(@RequestBody Agency user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    agencyRepository.save(user);
  }

  @GetMapping
  public List<Agency> getAllUsuarios() {
    return agencyRepository.findAll();
  }

  @GetMapping("/{username}")
  public Agency getUser(@PathVariable String username) {
    return agencyRepository.findByUserName(username);
  }
}