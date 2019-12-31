package com.mooveit.cars.utils;

import com.mooveit.cars.domain.Brand;

import java.io.File;

public interface XmlProcessor {

    void processFile(File xmlFile, Brand brand);
}
