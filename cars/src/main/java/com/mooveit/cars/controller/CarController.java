package com.mooveit.cars.controller;

import com.mooveit.cars.controller.response.CarBrandSpecificationsResponse;
import com.mooveit.cars.controller.response.CarSpecificationsResponse;
import com.mooveit.cars.domain.ford.ModelEntity;
import com.mooveit.cars.repositories.ford.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CarController {

  @Autowired
  private ModelService modelService;

  /**
   * Returns a model with its submodels for a given id,
   * or 404 not found if no model is found with that id.
   *
   * @param carId
   * @return
   */
  @RequestMapping(value = "/car/{id}", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<CarSpecificationsResponse> getCarById(@PathVariable("id") Integer carId) {

    Optional<ModelEntity> model = modelService.findModelById(carId);

    if (model.isPresent()) {
      CarSpecificationsResponse response  = new CarSpecificationsResponse(carId, model.get());

      response.add(linkTo(methodOn(CarController.class).getCarById(carId)).withSelfRel());
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Returns all cars of a given brand.
   *
   * @param brand
   * @return
   */
  @RequestMapping(value = "/{brand}/cars", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<CarBrandSpecificationsResponse> getCarsByBrand(@PathVariable("brand") String brand) {

    CarBrandSpecificationsResponse response =
      new CarBrandSpecificationsResponse(brand.toUpperCase(),  modelService.findModelsByBrand(brand));

    response.add(linkTo(methodOn(CarController.class).getCarsByBrand(brand)).withSelfRel());
    return ResponseEntity.ok(response);
  }

}
