package com.mooveit.cars.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer power;

    @NotNull
    private String type;

    protected Engine() {
    }

    public Engine(@NotNull Integer power, @NotNull String type) {
        this.power = power;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Integer getPower() {
        return power;
    }

    public String getType() {
        return type;
    }
}
