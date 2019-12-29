package com.mooveit.cars.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum WheelTypeEnum {
    STEEL("Gasoline"),
    ALLOY("Alloy");

    private String description;

    WheelTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", name());
        map.put("description", description);
        return map;
    }
}
