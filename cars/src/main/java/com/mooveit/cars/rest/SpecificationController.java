package com.mooveit.cars.rest;

import com.mooveit.cars.rest.dto.CarSpecification;
import com.mooveit.cars.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

@RestController
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    @GetMapping("/catalogue/{idCatalogue}/specification")
    public ResponseEntity<List<CarSpecification>> getSpecificationByCatalogueId(@PathVariable("idCatalogue") String catalogueId){
        List<CarSpecification> carSpecifications = specificationService.getSpecificationByCatalogueId(catalogueId);
        for(CarSpecification carSpecification:carSpecifications){
            carSpecification.add(linkTo(methodOn(SpecificationController.class).getSpecificationById(carSpecification.getIdCarSpec())).withRel("specification "+carSpecification.getName()));
        }
        return ResponseEntity.ok(carSpecifications);
    }

    @GetMapping("/specification/{id}")
    public ResponseEntity<CarSpecification> getSpecificationById(@PathVariable("id") Long id){
        CarSpecification carSpecification = specificationService.getSpecificationById(id);
        carSpecification.add(linkTo(methodOn(SpecificationController.class).getSpecificationById(id)).withSelfRel());
        return ResponseEntity.ok(carSpecification);
    }

}
