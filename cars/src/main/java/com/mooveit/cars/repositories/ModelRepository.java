package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.projections.ModelFullProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "models", path = "models", excerptProjection = ModelFullProjection.class)
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {
}
