package com.mooveit.cars.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseModel {

    @Id
    @GeneratedValue
    private Long id;
    private String  name;
    private Integer yearFrom;
    private Integer yearTo;
    @ManyToOne
    private Engine engine;
    @ManyToOne
    private Wheels wheels;

}
