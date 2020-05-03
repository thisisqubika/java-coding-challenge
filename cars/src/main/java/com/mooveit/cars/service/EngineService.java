package com.mooveit.cars.service;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.dto.EngineDTO;
import com.mooveit.cars.repositories.EngineRepository;

@Service
public class EngineService {

	private Engine engine;
	private final EngineRepository engineRepo;

	public EngineService(EngineRepository engineRepo) {
		super();
		this.engineRepo = engineRepo;
	}
	
	/**
	 * Method to get data and check for update/insert
	 * @param dto {@link EngineDTO}
	 * @return {@link Engine} the found entity
	 */
	public Engine processEngineEntityFromDto (EngineDTO dto) {
		engine = new Engine(dto.getPower(), dto.getType());
		Optional<Engine> opEngine = engineRepo.findOne(Example.of(engine));
		if(!opEngine.isPresent()) engine = engineRepo.saveAndFlush(engine);
		else engine = opEngine.get();
		return engine;
	}
}
