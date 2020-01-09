package com.mooveit.cars.tasks;

import com.mooveit.cars.configurations.ConfigurationProperties;
import com.mooveit.cars.enums.InputFileTypeEnum;
import com.mooveit.cars.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class FordIngesterTask implements Runnable {

    @Autowired
    private ConfigurationProperties configurationFileProperties;

    @Override
    public void run() {

        log.warn(" service started: FordIngesterTaskr");
        File fordFile = Utils.readFile(configurationFileProperties.getInputFordFile());
        final String fileName = fordFile.getName();

        for (InputFileTypeEnum inputFileType : InputFileTypeEnum.values()) {
            if (fileName.contains(inputFileType.getFileType())) {
                log.warn(" file of type " + inputFileType.getFileType() + " found");
                inputFileType.getIngestInterface().ingestFile(fordFile);
                log.warn(" file: " + fileName + " processed correctly");
            }
        }
    }

}
