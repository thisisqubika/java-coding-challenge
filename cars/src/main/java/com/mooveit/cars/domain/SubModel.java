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
@Table(name = "SUBMODEL")
public class SubModel extends Car{

    @Column(name = "line")
    @NotNull
    private String line;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    public SubModel(@NotNull String name, @NotNull String from, @NotNull String to, @NotNull String line, Model model, @NotNull Wheel wheel, @NotNull Engine engine) {
        super(name, from, to, wheel, engine);
        this.line = line;
        this.model = model;
    }

}
