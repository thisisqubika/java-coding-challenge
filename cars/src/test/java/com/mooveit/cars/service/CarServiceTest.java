package com.mooveit.cars.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @MockBean
    private ModelService modelService;

    @MockBean
    private WheelService wheelService;

    @MockBean
    private EngineService engineService;

    @MockBean
    private SubModelService subModelService;

    @Autowired
    private CarService carService;

    @Test
    public void createCarTest(){
        carService.createDomainsFromFile();

        verify(modelService, atLeastOnce()).createModel(any(),any(),any());
        verify(subModelService,atLeastOnce()).createSubModel(any(),any(),any(),any());
        verify(wheelService, atLeastOnce()).createWheel(any());
        verify(engineService, atLeastOnce()).createEngine(any());
    }


}
