package com.mooveit.cars.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class Utils {

    public static File readFile(String inputFilePath) {
        File file = null;
        try {
            synchronized (inputFilePath) {
                file = ResourceUtils.getFile("classpath:" + inputFilePath);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File:" + inputFilePath + " no Found");
        }
        return file;
    }

}
