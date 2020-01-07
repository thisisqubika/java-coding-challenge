package com.mooveit.cars.domain.ford;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mooveit.cars.domain.ford.types.EngineType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents an Engine entity in the db.
 */
@Getter
@Setter
@Entity
@Table(name = "engine")
@NoArgsConstructor
public class EngineEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer power;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType type;

    /**
     * Class constructor.
     *
     * @param id        The id.
     * @param power     Integer The engine's power.
     * @param type      [[EngineType]] The engine's type.
     */
    public EngineEntity(Integer id,
                        Integer power,
                        EngineType type) {
        this.id = id;
        this.power = power;
        this.type = type;
    }
}
