package com.mooveit.cars.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Engine {
    @Id
    @GeneratedValue
    private Long id;
    private String power;
    private String type;
}
