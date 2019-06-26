package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.models.EngineData;
import com.mooveit.cars.repositories.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    @Autowired
    private EngineRepository engineRepository;

    public Engine createEngine(EngineData engineData){
        Engine engine = new Engine(engineData.getPower(), engineData.getType());
        return engineRepository.save(engine);
    }
}
