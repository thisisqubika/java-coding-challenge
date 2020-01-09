package com.mooveit.cars.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ENGINE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class EngineEntity extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "POWER")
    private Integer power;

    @Column(name = "ENGINE_TYPE")
    private String type;

    public EngineEntity(Integer power, String type) {
        this.power = power;
        this.type = type;
    }

    public EngineEntity() {
    }

}
