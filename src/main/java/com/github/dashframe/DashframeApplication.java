package com.github.dashframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
@ResponseBody
@EnableAutoConfiguration
public class DashframeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashframeApplication.class, args);
	}

	@GetMapping("/about.json")
	public ResponseEntity<Map<String, Object>> aboutJson() {
		Map<String, Object> body = new HashMap<>();
		body.put("client", new HashMap<>());
		body.put("server", new HashMap<>());
		return ResponseEntity.ok(body);
	}
}
