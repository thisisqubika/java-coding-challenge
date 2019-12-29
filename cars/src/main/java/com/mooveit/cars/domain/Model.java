package com.mooveit.cars.domain;

import com.mooveit.cars.domain.enums.ModelLineEnum;
import com.mooveit.cars.domain.enums.ModelTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Table(name = "models")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "from")
    private Date from;

    @Column(name = "to")
    private Date to;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ModelTypeEnum type;

    @Column(name = "line")
    @Enumerated(EnumType.STRING)
    private ModelLineEnum line;

    @ManyToOne
    private Model parent;

    @OneToMany(mappedBy="parent")
    private Collection<Model> children;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine")
    private Engine engine;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wheel")
    private Wheel wheel;

}
