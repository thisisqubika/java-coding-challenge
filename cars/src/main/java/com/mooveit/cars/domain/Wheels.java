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

@Data    //generate getters/setters automatically
@Entity
@Table(name = "wheels")
public class Wheels implements Serializable {
	private static final long serialVersionUID = -5667475827211512777L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(name = "size")
	private String size;
	@NotNull
	@Column(name = "type")
	private String type;

	public Wheels() {
		// Hibernate requires a no-arg constructor
	}

	public Wheels(String size, String type) {
		this.size = size;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Wheel{" + "size='" + size + '\'' + ", type='" + type + '\'' + '}';
	}
}
