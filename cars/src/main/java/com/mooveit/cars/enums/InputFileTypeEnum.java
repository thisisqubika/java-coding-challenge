package com.mooveit.cars.enums;

import com.mooveit.cars.interfaces.IngestInterface;
import com.mooveit.cars.services.FordJsonService;
import com.mooveit.cars.services.FordXmlService;
import lombok.Getter;

@Getter
public enum InputFileTypeEnum {

    XML(".xml", new FordXmlService()),
    JSON(".json", new FordJsonService());

    private String fileType;
    private IngestInterface ingestInterface;

    InputFileTypeEnum(String fileType, IngestInterface ingestInterface) {
        this.fileType = fileType;
        this.ingestInterface = ingestInterface;
    }
}
