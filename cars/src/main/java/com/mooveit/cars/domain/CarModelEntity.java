package com.mooveit.cars.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CAR_MODEL")
@Getter
@Setter
public class CarModelEntity extends ModelBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR_FROM")
    private Integer from;

    @Column(name = "YEAR_TO")
    private Integer to;

    @Column(name = "MODEL_TYPE")
    private String type;

    @Column(name = "LINE")
    private String line;

    @Column(name = "BRAND")
    private String brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "wheel_id")
    private WheelEntity wheelEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "engine_id")
    private EngineEntity engineEntity;

    @ManyToOne()
    @JoinColumn(name = "parentModel_id")
    private CarModelEntity parentModel;

    @OneToMany(mappedBy = "parentModel", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<CarModelEntity> subModels = new HashSet<CarModelEntity>();

    @ManyToOne()
    @JoinColumn(name = "catalogue_id")
    private CatalogueEntity catalogueEntity;

    public CarModelEntity(EngineEntity engineEntity, WheelEntity wheelEntity) {
        this.engineEntity = engineEntity;
        this.wheelEntity = wheelEntity;
    }

    public CarModelEntity() {
    }
}
