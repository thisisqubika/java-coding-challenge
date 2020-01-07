package com.mooveit.cars.domain.ford;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mooveit.cars.domain.ford.types.RimSize;
import com.mooveit.cars.domain.ford.types.RimType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity for a wheel.
 */
@Getter
@Setter
@Entity
@Table(name = "wheel")
@NoArgsConstructor
public class WheelEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RimSize size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RimType type;

    /**
     * Class constructor.
     * @param id        Integer         The id.
     * @param size      {{RimSize}}     Rim's size.
     * @param type      {{RimType}}     Rim's type.
     */
    public WheelEntity(Integer id,
                       RimSize size,
                       RimType type) {
        this.id = id;
        this.size = size;
        this.type = type;
    }

}
