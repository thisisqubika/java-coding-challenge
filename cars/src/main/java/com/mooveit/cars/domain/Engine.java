package com.mooveit.cars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data   //generate getters/setters automatically
@Entity
@Table(name = "engines")
public class Engine implements Serializable {
	private static final long serialVersionUID = 214035518018246381L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(name = "power")
	private String power;
	@NotNull
	@Column(name = "type")
	private String type;

	public Engine() {
		// Hibernate requires a no-arg constructor
	}

	public Engine(String power, String type) {
		this.power = power;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Engine{" + "power='" + power + '\'' + ", type='" + type + '\'' + '}';
	}
}
