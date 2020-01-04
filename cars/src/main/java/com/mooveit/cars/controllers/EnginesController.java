package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.repositories.EngineRepository;


@RestController
@RequestMapping("api/v1/engines")
public class EnginesController {

	@Autowired
	private EngineRepository engineRepository;

	@GetMapping
	public List<Engine> list() {
		return engineRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Engine get(@PathVariable Long id) {
		return engineRepository.getOne(id);
	}

	@PostMapping
	public Engine create(@RequestBody final Engine engine) {
		return engineRepository.saveAndFlush(engine);
	}

}
