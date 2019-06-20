package com.mooveit.cars.services;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.repositories.ModelRepository;
import com.mooveit.cars.services.interfaces.IModelService;
import com.mooveit.cars.acl.xmlmodel.Catalogue;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ModelService implements IModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public Try<Model> save(Model model) {
        return Try.of(() -> modelRepository.save(model));
    }

    @Override
    public Try<List<Model>> saveAll(List<Model> models) {
        return Try.of(() -> StreamSupport.stream(
                modelRepository.saveAll(models).spliterator(), false
        ).collect(Collectors.toList()));
    }

    @Override
    public Try<Boolean> save(Catalogue catalogue) {
        //Persists the root models
        return Try.sequence(catalogue.getModels().stream().map(model ->
                save(model.parse(null))
                        .andThen(savedModel ->
                                //Persists the submodels
                                model.getSubmodels().forEach(subModel ->
                                        save(subModel.parse(savedModel))
                                )
                        )
        ).collect(Collectors.toList()))
                .map(models -> true);
    }
}
