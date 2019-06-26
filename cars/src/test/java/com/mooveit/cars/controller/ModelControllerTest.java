package com.mooveit.cars.controller;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.SubModel;
import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.service.CarService;
import com.mooveit.cars.service.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class ModelControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private ModelService modelService;

    @Autowired
    private CarController carController;

    @Before
    public void setTestMocks(){
        Wheel wheel = new Wheel("18","Red");
        Engine engine = new Engine("1600", "GAS");
        Model model = new Model("Aspire", "1990", "1996", "Compact", wheel, engine);
        SubModel subModel = new SubModel("Aspire 2", "1990", "1995", "Wagon", model, wheel, engine);
        wheel.setId(1L);
        engine.setId(2L);
        subModel.setId(3L);
        model.setId(4L);

        ArrayList<SubModel> subModelArrayList = new ArrayList<>();
        subModelArrayList.add(subModel);
        model.setSubModels(subModelArrayList);

        given(carService.getCarData("Aspire")).willReturn(model);
        given(carService.getCarData(4)).willReturn(model);
    }

    @Test
    public void searchByExistingModelNameTest() throws Exception {
        this.mvc.perform(get("/car/model/name/Aspire"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":4,\"name\":\"Aspire\",\"from\":\"1990\",\"to\":\"1996\",\"wheel\":{\"id\":1,\"size\":\"18\",\"type\":\"Red\"},\"engine\":{\"id\":2,\"power\":\"1600\",\"type\":\"GAS\"},\"type\":\"Compact\",\"subModels\":[{\"id\":3,\"name\":\"Aspire 2\",\"from\":\"1990\",\"to\":\"1995\",\"wheel\":{\"id\":1,\"size\":\"18\",\"type\":\"Red\"},\"engine\":{\"id\":2,\"power\":\"1600\",\"type\":\"GAS\"},\"line\":\"Wagon\"}]}"));
    }

    @Test
    public void searchByExistingModelIdTest() throws Exception {
        this.mvc.perform(get("/car/model/id/4"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":4,\"name\":\"Aspire\",\"from\":\"1990\",\"to\":\"1996\",\"wheel\":{\"id\":1,\"size\":\"18\",\"type\":\"Red\"},\"engine\":{\"id\":2,\"power\":\"1600\",\"type\":\"GAS\"},\"type\":\"Compact\",\"subModels\":[{\"id\":3,\"name\":\"Aspire 2\",\"from\":\"1990\",\"to\":\"1995\",\"wheel\":{\"id\":1,\"size\":\"18\",\"type\":\"Red\"},\"engine\":{\"id\":2,\"power\":\"1600\",\"type\":\"GAS\"},\"line\":\"Wagon\"}]}"));
    }

    @Test
    public void heartBeat() throws Exception {
        this.mvc.perform(get("/car/ping"))
                .andExpect(status().isOk());
    }

}
