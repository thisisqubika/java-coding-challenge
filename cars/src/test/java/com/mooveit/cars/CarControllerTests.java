package com.mooveit.cars;

import com.mooveit.cars.controller.CarController;
import com.mooveit.cars.controller.response.CarBrandSpecificationsResponse;
import com.mooveit.cars.controller.response.CarSpecificationsResponse;
import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.ford.ModelEntity;
import com.mooveit.cars.repositories.ford.service.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RunWith(SpringRunner.class)
public class CarControllerTests {

  private static final String VOLVO_BRAND = "VOLVO";

  private static final String FORD_BRAND = CarBrand.FORD.toString();

  @TestConfiguration
  static class CarControllerTestConfiguration {

    @Bean
    public CarController carController() {
      return new CarController();
    }
  }

  @Autowired
  private CarController carController;

  @MockBean
  private ModelService modelService;

  @Test
  public void getCarById_shouldReturnTheCarSpecificationsIfExistOr404ifItDoesNot() {
    ModelEntity model = Mockito.mock(ModelEntity.class);
    model.setId(100);

    when(modelService.findModelById(anyInt())).thenReturn(Optional.empty());
    when(modelService.findModelById(model.getId())).thenReturn(Optional.of(model));

    CarSpecificationsResponse carSpecificationsResponse =
      new CarSpecificationsResponse(model.getId(), model);

    carSpecificationsResponse.add(
      linkTo(methodOn(CarController.class).getCarById(model.getId())).withSelfRel()
    );

    ResponseEntity<CarSpecificationsResponse> responseModel =
      carController.getCarById(model.getId());
    ResponseEntity<CarSpecificationsResponse> responseModelDoesNotExist =
      carController.getCarById(model.getId() + 1);

    assertThat(responseModel.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseModel.getBody()).isEqualTo(carSpecificationsResponse);

    assertThat(responseModelDoesNotExist.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void getCarsByBrand() {
    ModelEntity model_1 = Mockito.mock(ModelEntity.class);
    model_1.setBrand(CarBrand.FORD);
    ModelEntity model_2 = Mockito.mock(ModelEntity.class);
    model_2.setBrand(CarBrand.FORD);
    ModelEntity model_3 = Mockito.mock(ModelEntity.class);
    model_3.setBrand(CarBrand.FORD);

    List<ModelEntity> fordModels =
      new ArrayList<>(Arrays.asList(model_1, model_2, model_3));

    when(modelService.findModelsByBrand(any())).thenReturn(new ArrayList<>());
    when(modelService.findModelsByBrand(FORD_BRAND)).thenReturn(fordModels);

    CarBrandSpecificationsResponse fordCars =
      new CarBrandSpecificationsResponse(FORD_BRAND, fordModels);

    fordCars.add(
      linkTo(methodOn(CarController.class).getCarsByBrand(FORD_BRAND)).withSelfRel()
    );

    CarBrandSpecificationsResponse volvoCars =
      new CarBrandSpecificationsResponse(VOLVO_BRAND, fordModels);

    volvoCars.add(
      linkTo(methodOn(CarController.class).getCarsByBrand(VOLVO_BRAND)).withSelfRel()
    );

    ResponseEntity<CarBrandSpecificationsResponse> fordResponse =
      carController.getCarsByBrand(FORD_BRAND);
    ResponseEntity<CarBrandSpecificationsResponse> volvoResponse =
      carController.getCarsByBrand(VOLVO_BRAND);

    assertThat(fordResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(fordResponse.getBody()).isEqualTo(fordCars);

    assertThat(volvoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(volvoResponse.getBody()).isEqualTo(volvoCars);
  }

}
