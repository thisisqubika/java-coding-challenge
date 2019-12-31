package com.mooveit.cars.domain.projections;

import com.mooveit.cars.domain.enums.WheelTypeEnum;

public interface WheelProjection {

    String getSize();

    WheelTypeEnum getType();
}
