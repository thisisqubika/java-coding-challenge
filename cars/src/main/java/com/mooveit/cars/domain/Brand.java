package com.mooveit.cars.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "BRANDS")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}
