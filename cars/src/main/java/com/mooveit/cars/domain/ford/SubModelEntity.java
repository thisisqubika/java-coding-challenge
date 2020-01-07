package com.mooveit.cars.domain.ford;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity for SubModel. See {{CarEntity}}.
 */
@Getter
@Setter
@Entity
@Table(name = "submodel")
@NoArgsConstructor
public class SubModelEntity extends CarEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private ModelEntity model;

    @Column(nullable = false)
    private String line;

    /**
     * Class constructor.
     * @param id        Integer     The id.
     * @param name      String      The submodel's name.
     * @param model     ModelEntity The parent Model.
     * @param from      Integer     Year this car was first manufactured.
     * @param to        Integer     Year this car stopped being manufactured. Zero if it's still in production.
     * @param line      String      SubModel's line.
     * @param engine    {{EngineEntity}} SubModel's engine.
     * @param wheels    {{WheelEntity}}  SubModel's wheels.
     */
    public SubModelEntity(Integer id,
                          String name,
                          ModelEntity model,
                          int from,
                          int to,
                          String line,
                          EngineEntity engine,
                          WheelEntity wheels) {
        super(id, name, from, to, engine, wheels);
        this.line = line;
        this.model = model;
    }

}
