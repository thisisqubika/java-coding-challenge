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
@Table(name = "WHEEL")
public class Wheel {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "wheel")
    private Model model;

    @JsonIgnore
    @OneToOne(mappedBy = "wheel")
    private SubModel subModel;

    @Column(name = "size")
    @NotNull
    private String size;

    @Column(name = "type")
    @NotNull
    private String type;

    public Wheel(@NotNull String size, @NotNull String type) {
        this.size = size;
        this.type = type;
    }

}
