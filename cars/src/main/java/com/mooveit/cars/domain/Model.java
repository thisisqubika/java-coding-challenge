package com.mooveit.cars.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Column(name = "f")
    private Integer from;

    private Integer to;

    private String type;

    private String line;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Model parent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Engine engine;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wheel wheel;

    protected Model() {
    }

    public Model(@NotNull String name, @NotNull Integer from, Integer to, @NotNull String type, @NotNull String line, Model parent, Engine engine, Wheel wheel) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.type = type;
        this.line = line;
        this.parent = parent;
        this.engine = engine;
        this.wheel = wheel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public String getType() {
        return type;
    }

    public String getLine() {
        return line;
    }

    public Model getParent() {
        return parent;
    }

    public Engine getEngine() {
        return engine;
    }

    public Wheel getWheel() {
        return wheel;
    }
}
