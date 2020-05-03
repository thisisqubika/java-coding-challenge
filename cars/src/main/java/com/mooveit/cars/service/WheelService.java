package com.mooveit.cars.service;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.dto.WheelDTO;
import com.mooveit.cars.repositories.WheelRepository;

@Service
public class WheelService {

	private Wheel wheel;
	private final WheelRepository wheelRepo;

	public WheelService(WheelRepository wheelRepo) {
		super();
		this.wheelRepo = wheelRepo;
	}
	
	/**
	 * Method to get data and check for update/insert
	 * @param dto {@link WheelDTO}
	 * @return {@link Wheel} the found entity
	 */
	public Wheel processWheelEntityFromDto(WheelDTO dto) {	
		wheel = new Wheel(dto.getSize(), dto.getType());
		Optional<Wheel> opWheel = wheelRepo.findOne(Example.of(wheel));		
		if(!opWheel.isPresent()) wheel = wheelRepo.saveAndFlush(wheel);
		else wheel = opWheel.get();
		return wheel;
	}
}
