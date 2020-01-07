package com.mooveit.cars.domain.ford;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * SuperClass that represents a car.
 * See {{ModelEntity}} and {{SubModelEntity}}.
 */
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class CarEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(nullable = false, unique = true)
    protected String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Column(name = "produced_from")
    protected Integer from;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Column(name = "produced_to")
    protected Integer to;

    @ManyToOne(cascade =  { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "engine_id")
    protected EngineEntity engine;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "wheels_id")
    protected WheelEntity wheels;

    /**
     * Class constructor.
     *
     * @param id        Integer The id.
     * @param name      String  The car's name.
     * @param from      Integer Year this car was first manufactured.
     * @param to        Integer Year this car stopped being manufactured. Zero if it's still in production.
     * @param engine    {{EngineEntity}} The car's engine.
     * @param wheels    {{WheelEntity}}  The car's wheel.
     */
    public CarEntity(Integer id,
                     String name,
                     int from,
                     int to,
                     EngineEntity engine,
                     WheelEntity wheels) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.engine = engine;
        this.wheels = wheels;
    }
}
