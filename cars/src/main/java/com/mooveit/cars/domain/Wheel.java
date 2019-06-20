package com.mooveit.cars.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wheel")
public class Wheel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String size;

    @NotNull
    private String type;


    protected Wheel() {
    }

    public Wheel(@NotNull String size, @NotNull String type) {
        this.size = size;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
