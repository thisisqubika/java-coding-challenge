package com.mooveit.cars.controller.response;

import com.mooveit.cars.domain.ford.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarBrandSpecificationsResponse extends ResourceSupport {

  private String brand;

  private List<ModelEntity> cars;
}
