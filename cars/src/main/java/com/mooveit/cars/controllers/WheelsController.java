package com.mooveit.cars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.repositories.WheelRepository;

@RestController
@RequestMapping("api/v1/wheels")
public class WheelsController {

	@Autowired
	private WheelRepository wheelRepository;

	@GetMapping
	public List<Wheel> list() {
		return wheelRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Wheel get(@PathVariable Long id) {
		return wheelRepository.getOne(id);
	}

	@PostMapping
	public Wheel create(@RequestBody final Wheel wheel) {
		return wheelRepository.saveAndFlush(wheel);
	}
}
