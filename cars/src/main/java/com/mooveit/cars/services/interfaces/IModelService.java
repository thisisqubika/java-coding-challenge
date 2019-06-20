package com.mooveit.cars.services.interfaces;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.acl.xmlmodel.Catalogue;
import io.vavr.control.Option;
import io.vavr.control.Try;

import java.util.List;

public interface IModelService {

    Try<Model> save(Model model);

    Try<List<Model>> saveAll(List<Model> models);

    Try<Boolean> save(Catalogue catalogue);

    Try<List<Model>> findByName(String name);

    Try<Option<Model>> findById(String id);

}
