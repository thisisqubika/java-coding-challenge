package com.mooveit.cars;

import com.mooveit.cars.domain.CarBrand;
import com.mooveit.cars.domain.ford.EngineEntity;
import com.mooveit.cars.domain.ford.ModelEntity;
import com.mooveit.cars.domain.ford.WheelEntity;
import com.mooveit.cars.repositories.ford.ModelRepository;
import com.mooveit.cars.repositories.ford.service.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ModelServiceTests {

  @TestConfiguration
  static class ModelServiceTestConfiguration {

    @Bean
    public ModelService modelService() {
      return new ModelService();
    }
  }

  @Autowired
  private ModelService modelService;

  @MockBean
  private ModelRepository modelRepository;

  private ModelEntity mockedModel;

  public ModelServiceTests() {
    this.mockedModel = new ModelEntity(
      100,
      "Car",
      CarBrand.FORD,
      2010,
      0,
      "hatchback",
      Mockito.mock(EngineEntity.class),
      Mockito.mock(WheelEntity.class),
      null
    );
  }

  @Test
  public void findModelByIdTest_shouldReturnRightModelOrEmptyIfITDoesNotExist() {

    when(modelRepository.findById(any())).thenReturn(Optional.empty());
    when(modelRepository.findById(mockedModel.getId())).thenReturn(Optional.of(mockedModel));

    Optional<ModelEntity> model1 = modelService.findModelById(mockedModel.getId());
    Optional<ModelEntity> model2 = modelService.findModelById(mockedModel.getId()+1);

    assertThat(model1).isEqualTo(Optional.of(mockedModel));
    assertThat(model2).isEqualTo(Optional.empty());
  }

  @Test
  public void findModelByBrand_shouldReturnListWithMoodelsOrEmptyListIfDoesNotHaveCarOfThatBrand() {
    List<ModelEntity> models = new ArrayList<ModelEntity>(Arrays.asList(mockedModel));

    when(modelRepository.findAllByBrand(any())).thenReturn(new ArrayList<>());
    when(modelRepository.findAllByBrand(CarBrand.FORD))
      .thenReturn(models);

    List<ModelEntity> fordModels = modelService.findModelsByBrand(CarBrand.FORD.toString());
    List<ModelEntity> volvoModels = modelService.findModelsByBrand("volvo");

    assertThat(fordModels).isEqualTo(models);
    assertThat(volvoModels).isEqualTo(new ArrayList<>());
  }
}
