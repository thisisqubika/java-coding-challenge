package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.domain.Wheel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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


    @Before
    public void setUp(){
        Wheel wheel = new Wheel("18","Red");
        Engine engine = new Engine("1600", "GAS");
        Model model = new Model("Aspire", "1990", "1996", "Compact", wheel, engine);
        SubModel subModel = new SubModel("Aspire 2", "1990", "1995", "Wagon", model, wheel, engine);
        List<SubModel> subModelList = new ArrayList<>();
        subModelList.add(subModel);
        wheel.setId(1L);
        engine.setId(2L);
        subModel.setId(3L);
        model.setId(4L);
        model.setSubModels(subModelList);

        given(wheelService.getWheelsById(1L)).willReturn(wheel);
        given(engineService.getEngineById(2L)).willReturn(engine);
        given(modelService.getModelByName("Aspire")).willReturn(model);
        given(modelService.getModelById(4)).willReturn(model);
    }

    @Test
    public void getCarDataByNameTest(){
        String modelName = "Aspire";
        String subModelName = "Aspire 2";
        String wheelSize = "18";
        String enginePower = "1600";

        assertThat(carService.getCarData("Aspire").getName()).isEqualTo(modelName);
        assertThat(carService.getCarData("Aspire").getSubModels().get(0).getName()).isEqualTo(subModelName);
        assertThat(carService.getCarData("Aspire").getWheel().getSize()).isEqualTo(wheelSize);
        assertThat(carService.getCarData("Aspire").getEngine().getPower()).isEqualTo(enginePower);
    }

    @Test
    public void getCarDataByIdTest(){
        String modelName = "Aspire";
        String subModelName = "Aspire 2";
        String wheelSize = "18";
        String enginePower = "1600";

        assertThat(carService.getCarData(4).getName()).isEqualTo(modelName);
        assertThat(carService.getCarData(4).getSubModels().get(0).getName()).isEqualTo(subModelName);
        assertThat(carService.getCarData(4).getWheel().getSize()).isEqualTo(wheelSize);
        assertThat(carService.getCarData(4).getEngine().getPower()).isEqualTo(enginePower);
    }

    @Test
    public void createCarTest(){
        carService.createDomainsFromFile();

        verify(modelService, atLeastOnce()).createModel(any(),any(),any());
        verify(subModelService,atLeastOnce()).createSubModel(any(),any(),any(),any());
        verify(wheelService, atLeastOnce()).createWheel(any());
        verify(engineService, atLeastOnce()).createEngine(any());
    }


}
