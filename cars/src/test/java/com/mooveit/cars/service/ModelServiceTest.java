package com.mooveit.cars.service;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.models.EngineData;
import com.mooveit.cars.models.ModelData;
import com.mooveit.cars.models.SubModelContainer;
import com.mooveit.cars.models.WheelData;
import com.mooveit.cars.repositories.ModelRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelServiceTest {

    @Autowired
    private ModelService modelService;

    @MockBean
    private ModelRepository modelRepository;

    @Before
    public void setUp(){
        Model model = new Model("Mustang","1990","2000","Cabriolet",new Wheel(), new Engine());
        List<Model> modelList = new ArrayList<>();
        modelList.add(model);
        given(modelRepository.save(any())).willReturn(model);
    }

    @Test
    public void createModelTest(){
        ModelData modelData = new ModelData("Mustang", "1990","2000","Cabriolet","Sport", new WheelData(), new EngineData(), new SubModelContainer());
        assertThat(modelService.createModel(modelData,new Wheel(),new Engine()).getName()).isEqualTo(modelData.getName());
        assertThat(modelService.createModel(modelData,new Wheel(),new Engine()).getFrom()).isEqualTo(modelData.getFrom());
        assertThat(modelService.createModel(modelData,new Wheel(),new Engine()).getTo()).isEqualTo(modelData.getTo());
        assertThat(modelService.createModel(modelData,new Wheel(),new Engine()).getType()).isEqualTo(modelData.getType());
    }
}
