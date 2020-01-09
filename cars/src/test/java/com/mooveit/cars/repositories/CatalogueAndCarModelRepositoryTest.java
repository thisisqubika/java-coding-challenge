package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.domain.CatalogueEntity;
import com.mooveit.cars.domain.EngineEntity;
import com.mooveit.cars.domain.WheelEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogueAndCarModelRepositoryTest {

    private static CarModelEntity carModelEntityParent;
    private static CarModelEntity carModelEntityC;
    private static CarModelEntity carModelEntityD;
    private static WheelEntity wheelEntity;
    private static EngineEntity engineEntity;
    private static CatalogueEntity catalogueEntity;


    @BeforeAll
    static void init() {
        engineEntity = new EngineEntity(1600, "GAS");
        wheelEntity = new WheelEntity("R16", "ALLOY");

        carModelEntityParent = createAnCarModel("Aspire", engineEntity, wheelEntity);
        CarModelEntity subModelA = createAnCarModel("Aspire A sub level 1", engineEntity, wheelEntity);
        CarModelEntity subModelA1 = createAnCarModel("Aspire A1 sub level 2", engineEntity, wheelEntity);
        CarModelEntity subModelA2 = createAnCarModel("Aspire A2 sub level 2", engineEntity, wheelEntity);
        CarModelEntity subModelB = createAnCarModel("Aspire B sub level 1", engineEntity, wheelEntity);
        subModelA1.setParentModel(subModelA);
        subModelA2.setParentModel(subModelA);
        subModelA.setParentModel(carModelEntityParent);
        subModelB.setParentModel(carModelEntityParent);

        Set<CarModelEntity> subModelAList = new HashSet<>();
        subModelAList.add(subModelA1);
        subModelAList.add(subModelA2);
        subModelA.setSubModels(subModelAList);

        Set<CarModelEntity> subModelsParentList = new HashSet<>();
        subModelsParentList.add(subModelA);
        subModelsParentList.add(subModelB);
        carModelEntityParent.setSubModels(subModelsParentList);

        carModelEntityC = createAnCarModel("Aspire C ", engineEntity, wheelEntity);
        carModelEntityD = createAnCarModel("Aspire D ", engineEntity, wheelEntity);

        catalogueEntity = new CatalogueEntity();
        carModelEntityParent.setCatalogueEntity(catalogueEntity);
        carModelEntityC.setCatalogueEntity(catalogueEntity);
        carModelEntityD.setCatalogueEntity(catalogueEntity);

        catalogueEntity.setName("Brand Name");

        //List<CarModelEntity> carModelsEntityListForCatalogue = new ArrayList<>();
        //carModelsEntityListForCatalogue.add(carModelEntityParent);
        //carModelsEntityListForCatalogue.add(carModelEntityC);
        //carModelsEntityListForCatalogue.add(carModelEntityD);
       // catalogueEntity.setCarModelEntities(carModelsEntityListForCatalogue);
    }


    @DisplayName("a Catalogue are created")
    @Test
    void aCatalogueCanBeCreated() {
        // Catalogue
        CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
        when(catalogueRepository.findById(catalogueEntity.getId())).thenReturn(Optional.of(catalogueEntity));
        Optional<CatalogueEntity> catalogueOptional = catalogueRepository.findById(catalogueEntity.getId());
        assertThat(catalogueOptional.isPresent()).isTrue();
    }


    @DisplayName("a CarModels are created")
    @Test
    void aCatalogueAndCarModelsCanBeCreated() {
        // CarModelParent
        CarModelRepository carModelRepository = mock(CarModelRepository.class);
        when(carModelRepository.findById(carModelEntityParent.getId())).thenReturn(Optional.of(carModelEntityParent));
        Optional<CarModelEntity> carModelOptional = carModelRepository.findById(carModelEntityParent.getId());
        assertThat(carModelOptional.isPresent()).isTrue();
    }


    @DisplayName("when search for all carModels then return a carModel's list")
    @Test
    void whenSearchAllCarModel_thenReturnACarModelList() {
        List<CarModelEntity> carModelList = new ArrayList<>();
        carModelList.add(carModelEntityParent);
        carModelList.add(carModelEntityC);
        carModelList.add(carModelEntityD);

        CarModelRepository carModelRepository = mock(CarModelRepository.class);
        when(carModelRepository.findAll()).thenReturn(carModelList);
        List<CarModelEntity> carModelFoundList = carModelRepository.findAll();
        assertThat(carModelFoundList).isNotNull();
        assertThat(carModelFoundList.size()).isGreaterThanOrEqualTo(0);
    }


    @DisplayName("when search for all carModels by brand then return a carModel's list")
    @Test
    void whenSearchAllCarModelByBrand_thenReturnACarModelList() {
        List<CarModelEntity> carModelList = new ArrayList<>();
        carModelList.add(carModelEntityParent);
        carModelList.add(carModelEntityC);
        carModelList.add(carModelEntityD);

        CarModelRepository carModelRepository = mock(CarModelRepository.class);
        when(carModelRepository.findByBrand("Ford")).thenReturn(carModelList);
        List<CarModelEntity> carModelFoundList = carModelRepository.findByBrand("Ford");
        assertThat(carModelFoundList).isNotNull();
        assertThat(carModelFoundList.size()).isGreaterThanOrEqualTo(0);
        assertThat(carModelFoundList.get(0).getBrand()).isEqualTo("Ford");
    }


    private static CarModelEntity createAnCarModel(String name, EngineEntity engineEntity, WheelEntity wheelEntity) {
        CarModelEntity carModelEntity = new CarModelEntity(engineEntity, wheelEntity);
        carModelEntity.setName(name);
        carModelEntity.setBrand("Ford");
        carModelEntity.setFrom(1994);
        carModelEntity.setTo(1997);
        carModelEntity.setLine("sport-utility");
        carModelEntity.setType("compact minivan");
        carModelEntity.setSubModels(null);
        carModelEntity.setParentModel(null);
        return carModelEntity;
    }
}
