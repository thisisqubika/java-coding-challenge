package com.mooveit.cars.services.interfaces;

import com.mooveit.cars.domain.Model;
import com.mooveit.cars.acl.xmlmodel.Catalogue;
import io.vavr.control.Try;

import java.util.List;

public interface IModelService {

    public Try<Model> save(Model model);

    public Try<List<Model>> saveAll(List<Model> models);

    public Try<Boolean> save(Catalogue catalogue);
}
