package com.mooveit.cars.utils;

import io.vavr.control.Try;

public class Converter {

    public static Try<Long> string2Long(String number) {
        return Try.of(() -> new Long(number));
    }

}
