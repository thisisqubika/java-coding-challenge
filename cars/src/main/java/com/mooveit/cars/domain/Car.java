package com.mooveit.cars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "from_date")
    @NotNull
    private String from;

    @Column(name = "to_date")
    @NotNull
    private String to;

    @OneToOne
    @JoinColumn(name = "wheel_id", referencedColumnName = "id")
    private Wheel wheel;

    @OneToOne
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    private Engine engine;

    public Car(@NotNull String name, @NotNull String from, @NotNull String to, Wheel wheel, Engine engine) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.wheel = wheel;
        this.engine = engine;
    }
}
