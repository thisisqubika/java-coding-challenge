package com.mooveit.cars.domain;

import com.mooveit.cars.domain.enums.ModelLineEnum;
import com.mooveit.cars.domain.enums.ModelTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "MODELS")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "xrom")
    private Date from;

    @Column(name = "to")
    private Date to;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ModelTypeEnum type;

    @Column(name = "line")
    @Enumerated(EnumType.STRING)
    private ModelLineEnum line;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "parent_id")
    private Model parent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "engine")
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "wheel")
    private Wheel wheel;

}
