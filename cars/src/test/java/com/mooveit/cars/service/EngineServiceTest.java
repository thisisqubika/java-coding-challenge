package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.models.EngineData;
import com.mooveit.cars.repositories.EngineRepository;
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
public class EngineServiceTest {

    @Autowired
    private EngineService engineService;

    @MockBean
    private EngineRepository engineRepository;

    @Before
    public void setUp(){
        Engine engine = new Engine("1000","GAS");
        given(engineRepository.save(any())).willReturn(engine);
        given(engineRepository.findById(any())).willReturn(Optional.of(engine));
    }

    @Test
    public void createEngineTest(){
        EngineData engineData = new EngineData("1000", "GAS");
        assertThat(engineService.createEngine(engineData).getPower()).isEqualTo(engineData.getPower());
        assertThat(engineService.createEngine(engineData).getType()).isEqualTo(engineData.getType());
    }

    @Test
    public void getEngineByIdTest(){
        EngineData engineData = new EngineData("1000", "GAS");
        assertThat(engineService.getEngineById(any()).getPower()).isEqualTo(engineData.getPower());
        assertThat(engineService.getEngineById(any()).getType()).isEqualTo(engineData.getType());
    }
}
