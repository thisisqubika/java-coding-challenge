package com.mooveit.cars.domain.ford;

import com.mooveit.cars.domain.CarBrand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity for Model. See {{CarEntity}}
 */
@Getter
@Setter
@Entity
@Table(name = "model")
@NoArgsConstructor
public class ModelEntity extends CarEntity {

    @Column
    private String type;

    @Enumerated(EnumType.STRING)
    @Column
    private CarBrand brand;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<SubModelEntity> submodels;


    /**
     * Class constructor.
     * @param name          String  Name of the model.
     * @param from          int     Year when this model was first produced.
     * @param to            int     Year when this model was last produced. If null it's sill on production.
     * @param type          String  Type of car.
     * @param engine        EngineEntity    The Engine.
     * @param wheels        WheelEntity     The wheels.
     * @param submodels     List{{SubModelEntity}} The submodels.
     */
    public ModelEntity(Integer id,
                       String name,
                       CarBrand brand,
                       int from,
                       int to,
                       String type,
                       EngineEntity engine,
                       WheelEntity wheels,
                       Set<SubModelEntity> submodels) {
        super(id, name, from, to, engine, wheels);
        this.brand = brand;
        this.type = type;
        this.submodels = submodels;
    }

}
