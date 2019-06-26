package com.mooveit.cars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MODEL")
public class Model extends Car {

    @Column(name = "type")
    @NotNull
    private String type;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private List<SubModel> subModels = new ArrayList<>();

    public Model(@NotNull String name, @NotNull String from, @NotNull String to, @NotNull String type, @NotNull Wheel wheel, @NotNull Engine engine) {
        super(name, from, to, wheel, engine);
        this.type = type;
    }
}
