package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Engine;
import com.mooveit.cars.domain.enums.EngineTypeEnum;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "engines", path = "engines")
public interface EngineRepository extends PagingAndSortingRepository<Engine, Long> {

    Engine findByPowerAndType(Long power, EngineTypeEnum type);
}
