package com.mooveit.cars.repositories.ford.service;

import com.mooveit.cars.domain.ford.EngineEntity;
import com.mooveit.cars.repositories.ford.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles persistence or info related to {{EngineEntity}}.
 */
@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    /**
     * Saves an engine or updates it if it already exist.
     *
     * @param engine
     * @return
     */
    public EngineEntity saveEngine(EngineEntity engine) {
        engine.setId(
          engineRepository.findByPowerAndType(engine.getPower(), engine.getType())
            .map(e -> e.getId()).orElse(0)
        );

        return engineRepository.save(engine);
    }

}
