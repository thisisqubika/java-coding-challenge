package com.mooveit.cars.domain.projections;

import com.mooveit.cars.domain.Brand;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "brandProjection", types = {Brand.class})
public interface BrandProjection {

    String getName();
}
