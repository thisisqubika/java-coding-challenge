package com.mooveit.cars.domain;

import com.mooveit.cars.domain.enums.EngineTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "ENGINES")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "power")
    private Long power;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum type;


}
