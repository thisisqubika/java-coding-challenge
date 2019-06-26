package com.mooveit.cars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ENGINE")
public class Engine {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "power")
    @NotNull
    private String power;

    @Column(name = "type")
    @NotNull
    private String type;

    @JsonIgnore
    @OneToOne(mappedBy = "engine")
    private Model model;

    @JsonIgnore
    @OneToOne(mappedBy = "engine")
    private SubModel subModel;

    public Engine(@NotNull String power, @NotNull String type) {
        this.power = power;
        this.type = type;
    }

}
