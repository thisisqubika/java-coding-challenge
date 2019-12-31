package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.domain.projections.ModelFullProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "models", path = "models", excerptProjection = ModelFullProjection.class)
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {

    @Query("" +
            " SELECT m " +
            " FROM  Model m, " +
            "       Brand b  " +
            " WHERE m.brand.id = b.id" +
            " AND   b.id = :brandId")
    List<Model> fndModelsByBrandId(@Param("brandId") Long brandId);

    @Query("" +
            " SELECT m " +
            " FROM  Model m, " +
            "       Brand b  " +
            " WHERE m.brand.id = b.id" +
            " AND   b.name = :brandName")
    List<Model> fndModelsByBrandName(@Param("brandName") String brandName);
}
