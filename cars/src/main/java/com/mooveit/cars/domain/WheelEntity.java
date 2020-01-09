package com.mooveit.cars.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WHEEL")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class WheelEntity extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "WHEEL_SIZE")
    private String size;

    @Column(name = "WHEEL_TYPE")
    private String type;


    public WheelEntity(String size, String type) {
        this.size = size;
        this.type = type;
    }

    public WheelEntity() {
    }
}
