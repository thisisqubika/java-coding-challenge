package com.mooveit.cars.service;

import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.WheelData;
import com.mooveit.cars.repositories.WheelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WheelService {

    @Autowired
    private WheelRepository wheelRepository;

    public Wheel createWheel(WheelData wheelData){
        Wheel wheel = new Wheel(wheelData.getSize(),wheelData.getType());
        return wheelRepository.save(wheel);
    }

}
