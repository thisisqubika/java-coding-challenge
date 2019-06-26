package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.EngineData;
import com.mooveit.cars.models.SubModelData;
import com.mooveit.cars.models.WheelData;
import com.mooveit.cars.repositories.SubModelRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubModelServiceTest {

    @Autowired
    private SubModelService subModelService;

    @MockBean
    private SubModelRepository subModelRepository;


    @Before
    public void setUp(){
        Wheel wheel = new Wheel("18","STEEL");
        Engine engine = new Engine("1600", "GAS");
        Model model = new Model("Mustang","1990","2000","Cabriolet",new Wheel(), new Engine());
        SubModel subModel = new SubModel("Aspire 2", "1990", "1996", "Cabriolet", model, wheel, engine);
        given(subModelRepository.save(any())).willReturn(subModel);
    }

    @Test
    public void createCarTest(){
        SubModelData subModelData = new SubModelData("Aspire 2", "Cabriolet","1990", "1996", new EngineData(), new WheelData());
        assertThat(subModelService.createSubModel(new Model(), subModelData, new Wheel(),new Engine()).getName()).isEqualTo(subModelData.getName());
        assertThat(subModelService.createSubModel(new Model(), subModelData, new Wheel(),new Engine()).getLine()).isEqualTo(subModelData.getLine());
    }
}
