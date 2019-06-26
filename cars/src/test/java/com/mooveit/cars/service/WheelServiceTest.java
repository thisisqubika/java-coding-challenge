package com.mooveit.cars.service;


import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.WheelData;
import com.mooveit.cars.repositories.WheelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WheelServiceTest {

    @Autowired
    private WheelService wheelService;

    @MockBean
    private WheelRepository wheelRepository;

    @Before
    public void setUp(){
        Wheel wheel = new Wheel("18","STEEL");
        given(wheelRepository.save(any())).willReturn(wheel);
        given(wheelRepository.findById(any())).willReturn(Optional.of(wheel));
    }

    @Test
    public void createEngineTest(){
        WheelData wheelData = new WheelData("18", "STEEL");
        assertThat(wheelService.createWheel(wheelData).getSize()).isEqualTo(wheelData.getSize());
        assertThat(wheelService.createWheel(wheelData).getType()).isEqualTo(wheelData.getType());
    }

    @Test
    public void getEngineByIdTest(){
        WheelData wheelData = new WheelData("18", "STEEL");
        assertThat(wheelService.getWheelsById(any()).getSize()).isEqualTo(wheelData.getSize());
        assertThat(wheelService.getWheelsById(any()).getType()).isEqualTo(wheelData.getType());
    }

}
