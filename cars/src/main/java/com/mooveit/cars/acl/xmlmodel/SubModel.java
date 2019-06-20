package com.mooveit.cars.acl.xmlmodel;

import java.util.List;

public class SubModel {

    private List<Model> submodels;

    public SubModel(List<Model> submodels) {
        this.submodels = submodels;
    }

    public SubModel() {
    }

    public List<Model> getSubmodels() {
        return submodels;
    }
}
