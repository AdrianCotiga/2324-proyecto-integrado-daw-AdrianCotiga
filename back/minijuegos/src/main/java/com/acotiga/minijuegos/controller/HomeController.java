package com.acotiga.minijuegos.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@CrossOrigin
@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "index";
	}
}
