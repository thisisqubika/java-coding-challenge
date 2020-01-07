package com.mooveit.cars.repositories.ford.service;

import com.mooveit.cars.domain.ford.WheelEntity;
import com.mooveit.cars.repositories.ford.WheelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Handles persistence or info related to {{WheelEntity}}.
 */
@Service
public class WheelService {

    @Autowired
    private WheelRepository wheelRepository;

    /**
     * Saves a WheelEntity to the db, or updates it if a ModelEntity with the same id already exist.
     *
     * @param wheel
     */
   public WheelEntity saveWheel(WheelEntity wheel) {
     Optional<WheelEntity> existingWheel = wheelRepository.findBySizeAndType(wheel.getSize(), wheel.getType());
     wheel.setId(existingWheel.map(w -> w.getId()).orElse(0));

     return wheelRepository.save(wheel);
   }
}
