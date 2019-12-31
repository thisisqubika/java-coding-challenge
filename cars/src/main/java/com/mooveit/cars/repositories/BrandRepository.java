package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Brand;
import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.enums.EngineTypeEnum;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "brands", path = "brands")
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

    Brand findByName(String name);
}
