package com.github.dashframe.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ServicesController {
  @GetMapping("/services")
  public Map<String, Object> list() {
    var services = new HashMap<String, Object>();
    var available = new ArrayList<>();
    var instances = new ArrayList<>();

    services.put("available", available);
    services.put("instances", instances);

    return services;
  }
}
