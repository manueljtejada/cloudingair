package com.twcam.uv.cloudingair.controller;

import java.util.List;

import com.twcam.uv.cloudingair.domain.SecurityCheck;
import com.twcam.uv.cloudingair.repository.SecurityCheckRepository;
import com.twcam.uv.cloudingair.service.SecurityCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/security")
public class SecurityController {
  @Autowired
  SecurityCheckService service;

  @GetMapping()
  public List<SecurityCheck> getAllSecurityChecks() {
    return service.getAll();
  }

  @GetMapping(value ="{ticketId}")
  public SecurityCheck getById(@PathVariable("ticketId") int ticketId) {
    return service.getById(ticketId);
  }

  @PostMapping(value = "{ticketId}")
  public SecurityCheck registerSecurityCheck(@PathVariable("ticketId") int ticketId) {
    return service.registerSecurityCheck(ticketId);
  }
}