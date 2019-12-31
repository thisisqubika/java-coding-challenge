package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Wheel;
import com.mooveit.cars.domain.enums.WheelTypeEnum;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "wheels", path = "wheels")
public interface WheelRepository extends PagingAndSortingRepository<Wheel, Long> {

    Wheel findBySizeAndType(String size, WheelTypeEnum type);
}
