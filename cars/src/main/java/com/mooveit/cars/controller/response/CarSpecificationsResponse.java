package com.mooveit.cars.controller.response;

import com.mooveit.cars.domain.ford.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSpecificationsResponse extends ResourceSupport {

  private Integer carId;

  private ModelEntity model;
}
