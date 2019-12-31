package com.mooveit.cars.repositories;

import com.mooveit.cars.domain.FileProcessed;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "filesprocessed", path = "filesprocessed")
public interface FileProcessedRepository extends PagingAndSortingRepository<FileProcessed, Long> {

    FileProcessed findByName(String name);
}
