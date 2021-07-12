package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "models")
public class Model implements Serializable {
	private static final long serialVersionUID = 4478693618673332244L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;
	@Column(name = "from_year")
	private String from;
	@Column(name = "to_year")
	private String to;
	@NotNull
	@Column(name = "type")
	private String type;
	@Column(name = "line")
	private String line;
	@OneToOne(targetEntity = Engine.class, cascade = CascadeType.ALL)
	private Engine engine;
	@OneToOne(targetEntity = Wheels.class, cascade = CascadeType.ALL)
	private Wheels wheels;
	@ManyToOne
	@JoinColumn(name = "sub_models_id", nullable = false)
	private SubModels subModels;

	public Model() {
		// Hibernate requires a no-arg constructor
	}

	public Model(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Model{" + "id=" + id + ", name='" + name + '\'' + ", from='" + from + '\'' + ", to='" + to + '\'' + ", type='" + type + '\'' + ", line='" + line + '\'' + ", engine=" + engine + ", wheel=" + wheels + ", subModels=" + subModels + '}';
	}
}
