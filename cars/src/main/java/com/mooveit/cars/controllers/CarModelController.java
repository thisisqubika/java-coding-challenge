package com.mooveit.cars.controllers;

import com.mooveit.cars.assemblers.CarModelAssembler;
import com.mooveit.cars.domain.CarModelEntity;
import com.mooveit.cars.models.CarModelModel;
import com.mooveit.cars.services.CarModelService;
import com.mooveit.cars.services.ConvertBetweenEntitiesAndModels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Validated
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarModelAssembler carModelAssembler;

    @Autowired
    private PagedResourcesAssembler<CarModelEntity> pagedResourcesAssembler;

    @Autowired
    private ConvertBetweenEntitiesAndModels convertBetweenEntitiesAndModels;


    @GetMapping(value = "/cars", produces = {"application/json"})
    public ResponseEntity<PagedModel<CarModelModel>> findAllCarModels(Pageable pageable) throws Exception {
        Page<CarModelEntity> carModelsList = carModelService.findAllCarModel(pageable);
        PagedModel<CarModelModel> carModelModelList;
        carModelModelList = pagedResourcesAssembler.toModel(carModelsList, carModelAssembler);
        return ResponseEntity.ok().body(carModelModelList);
    }


    @GetMapping(value = "/cars/{id}", produces = {"application/json"})
    public ResponseEntity<CarModelModel> findCarModelById(@Positive @PathVariable Long id) throws Exception {
        CarModelEntity carModelEntity = carModelService.findCarModelById(id);
        CarModelModel carModelModel;
        carModelModel = convertBetweenEntitiesAndModels.carModelEntityToModel(carModelEntity);
        return ResponseEntity.ok().body(carModelModel);
    }


    @GetMapping(value = "/cars/brand/{brand}", produces = {"application/json"})
    public ResponseEntity<PagedModel<CarModelModel>> findAllCarModelByBrand(@NotBlank @NotNull @PathVariable String brand
            , Pageable pageable) throws Exception {
        Page<CarModelEntity> carModelsList = carModelService.findAllCarModelByBrand(brand, pageable);
        PagedModel<CarModelModel> carModelModelList;
        carModelModelList = pagedResourcesAssembler.toModel(carModelsList, carModelAssembler);
        return ResponseEntity.ok().body(carModelModelList);
    }

}
